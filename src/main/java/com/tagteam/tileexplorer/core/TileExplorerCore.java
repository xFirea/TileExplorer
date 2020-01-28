package com.tagteam.tileexplorer.core;

import com.gestankbratwurst.le_engine.EngineCore;
import com.gestankbratwurst.le_engine.audio.GameAudioController;
import com.gestankbratwurst.le_engine.graphics.GameGraphicController;
import com.gestankbratwurst.le_engine.graphics.GraphicPriority;
import com.gestankbratwurst.le_engine.logic.GameLogicController;
import com.gestankbratwurst.le_engine.logic.GameScheduler;
import com.gestankbratwurst.le_engine.logic.LogicPrecision;
import com.gestankbratwurst.le_engine.startmenu.GameResolution;
import com.google.common.base.Preconditions;
import com.tagteam.tileexplorer.game.events.GameEventManager;
import com.tagteam.tileexplorer.game.events.Listener;
import com.tagteam.tileexplorer.game.gameflow.GameBoard;
import com.tagteam.tileexplorer.game.gameflow.generators.opensimplex.TripleLayerOpenSimplexNoiseGenerator;
import com.tagteam.tileexplorer.game.keylistener.EscapeKeyListener;
import com.tagteam.tileexplorer.game.keylistener.MapDebugKeyListener;
import com.tagteam.tileexplorer.game.user.GameUser;
import com.tagteam.tileexplorer.game.windows.WindowManager;
import com.tagteam.tileexplorer.graphics.Background;
import com.tagteam.tileexplorer.startscreen.MenuScreen;
import com.tagteam.tileexplorer.util.GameLogger;
import com.tagteam.tileexplorer.util.UtilResource;
import com.tagteam.tileexplorer.util.swingutil.MouseClickEventAdapter;
import com.tagteam.tileexplorer.util.swingutil.MouseMoveEventAdapter;
import java.awt.Color;
import java.awt.Font;
import org.apache.commons.io.IOUtils;

/*******************************************************
 * Copyright (C) Gestankbratwurstle suotokka@gmail.com
 *
 * This file is not part of TileExplorer and was not created at the 23.01.2020
 *
 * Any parts of TileExplorer can not be copied and/or distributed without the express
 * permission of the owner or their cat.
 *
 */

public class TileExplorerCore {

  private static TileExplorerCore instance = null;
  private static final int GAME_TPS = 20;
  private static final LogicPrecision GAME_LOGIC_PRECISION = LogicPrecision.MICROS;
  private static final String GAME_NAME = "Tile Adventure";

  private static int getSystemThreadCount() {
    return 4;
  }

  public static void execute(Runnable runnable, long delay) {
    instance.gameScheduler.runTaskLater(runnable, delay);
  }

  public static void main(String[] args) {
    Preconditions.checkState(instance == null);
    EngineCore engine = EngineCore.init(GAME_NAME, getSystemThreadCount(), GAME_LOGIC_PRECISION, GAME_TPS);
    engine.openStartMenu(TileExplorerCore::new);
  }

  private TileExplorerCore(EngineCore engine) {
    instance = this;
    GameLogger.init(engine);
    this.engineCore = engine;
    this.gameScheduler = engine.getGameScheduler();
    this.eventManager = new GameEventManager();
    this.logicController = engine.getGameLogicController();
    this.graphicController = engine.getGameGraphicController();
    this.audioController = engine.getGameAudioController();
    this.gameResolution = engine.getGameResolution();
    this.windowManager = new WindowManager(this);
    setup();
  }

  private final EngineCore engineCore;
  private final GameEventManager eventManager;
  private final GameResolution gameResolution;
  private final GameLogicController logicController;
  private final GameGraphicController graphicController;
  private final GameAudioController audioController;
  private final GameScheduler gameScheduler;
  private final WindowManager windowManager;

  public void registerEvents(Listener listener) {
    this.eventManager.registerListener(listener);
  }

  private void setup() {
    setupSwing();
    setupGameOptions();
    setupGraphics();
    setupLogic();
    setupAudio();
    openStartScreen();
    //startGame();
  }

  private void setupGameOptions() {
    GameOptions.MAP_SIZE = 100;
    GameOptions.BASE_VISIBLE_RADIUS = 50;
    GameOptions.GAME_RESOLUTION = gameResolution;
    GameOptions.FONT_BASE_SIZE = (int) (12D / 640D * gameResolution.getHeight());
    GameOptions.GAME_FONT = new Font("Arial", Font.PLAIN, GameOptions.FONT_BASE_SIZE);
  }

  private void setupSwing() {
    GameLogger.log("Swing setup...");
    engineCore.addMouseListener(new MouseClickEventAdapter(eventManager, windowManager));
    engineCore.addMouseMotionListener(new MouseMoveEventAdapter(windowManager, GameUser.get().getGameCursor()));
    engineCore.addKeyListener(new EscapeKeyListener(engineCore));
  }

  private void setupGraphics() {
    GameLogger.log("Graphics setup...");
    Background background = new Background(gameResolution, Color.LIGHT_GRAY);
    graphicController.putGraphicTask("Background", background);
    graphicController.putGraphicTask(GraphicPriority.HIGH, "WindowTask", windowManager);
    graphicController.putGraphicTask(GraphicPriority.TOP, "GameCursor", GameUser.get().getGameCursor());
    graphicController.setFpsLimitEnabled(true);
    graphicController.setFpsLimit(60);
    graphicController.setFpsColor(Color.GREEN);
    graphicController.setInternalFpsDrawerEnabled(true);
  }

  private void setupLogic() {
    GameLogger.log("Logic setup...");
    TaskController.init(gameScheduler);
    LogicController.init(logicController);
  }

  private void setupAudio() {
    GameLogger.log("Audio setup...");
    audioController.createClip(IOUtils.buffer(UtilResource.getBufferedResource("sounds/click.wav")), "UI_CLICK_1");
    audioController.createClip(IOUtils.buffer(UtilResource.getBufferedResource("sounds/click2.wav")), "UI_CLICK_2");
    audioController.createClip(IOUtils.buffer(UtilResource.getBufferedResource("sounds/click3.wav")), "UI_CLICK_3");
    audioController.createClip(IOUtils.buffer(UtilResource.getBufferedResource("sounds/drag.wav")), "DRAG");
    audioController.createClip(IOUtils.buffer(UtilResource.getBufferedResource("sounds/main_theme.wav")), "MainTheme");
    AudioController.init(audioController);
  }

  private void openStartScreen() {
    windowManager.addWindow(new MenuScreen(this.gameResolution, windowManager, this));
  }

  public void startGame() {
    // TODO debug
    // windowManager.addWindow(new TestWindow(new IntBoundingBox(0, 0, 200, 200)));

    TripleLayerOpenSimplexNoiseGenerator generator = new TripleLayerOpenSimplexNoiseGenerator(1580116667489L, 12.5, 10, 10, 10, 0);

    GameBoard board = new GameBoard(10, 40, 20, generator, GameOptions.MAP_SIZE, GameOptions.BASE_VISIBLE_RADIUS, 320);

    // GameBoard board = new GameBoard(5, 100, 100, new OpenSimplexNoiseGenerator(0, 10D));

    windowManager.addWindow(board);

    this.engineCore.addKeyListener(new MapDebugKeyListener(board));
  }

}