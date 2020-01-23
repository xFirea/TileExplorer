package com.tagteam.tileexplorer;

import com.gestankbratwurst.le_engine.EngineCore;
import com.gestankbratwurst.le_engine.audio.GameAudioController;
import com.gestankbratwurst.le_engine.graphics.GameGraphicController;
import com.gestankbratwurst.le_engine.graphics.GraphicPriority;
import com.gestankbratwurst.le_engine.logic.GameLogicController;
import com.gestankbratwurst.le_engine.logic.LogicPrecision;
import com.gestankbratwurst.le_engine.startmenu.GameResolution;
import com.tagteam.tileexplorer.game.events.GameEventManager;
import com.tagteam.tileexplorer.game.events.Listener;
import com.tagteam.tileexplorer.game.windows.WindowManager;
import com.tagteam.tileexplorer.graphics.Background;
import com.tagteam.tileexplorer.util.GameLogger;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.swingutil.MouseClickEventAdapter;
import java.awt.Color;

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

  private static final int GAME_TPS = 20;
  private static final LogicPrecision GAME_LOGIC_PRECISION = LogicPrecision.MICROS;
  private static final String GAME_NAME = "Tile Adventure";

  private static int getSystemThreadCount() {
    return 4;
  }

  public static void main(String[] args) {
    EngineCore engine = EngineCore.init(GAME_NAME, getSystemThreadCount(), GAME_LOGIC_PRECISION, GAME_TPS);
    engine.openStartMenu(TileExplorerCore::new);
  }

  private TileExplorerCore(EngineCore engine) {
    GameLogger.init(engine);
    this.engineCore = engine;
    this.eventManager = new GameEventManager();
    this.logicController = engine.getGameLogicController();
    this.graphicController = engine.getGameGraphicController();
    this.audioController = engine.getGameAudioController();
    this.gameResolution = engine.getGameResolution();
    this.windowManager = new WindowManager();
    setup();
  }

  private final EngineCore engineCore;
  private final GameEventManager eventManager;
  private final GameResolution gameResolution;
  private final GameLogicController logicController;
  private final GameGraphicController graphicController;
  private final GameAudioController audioController;
  private final WindowManager windowManager;

  public void registerEvents(Listener listener) {
    this.eventManager.registerListener(listener);
  }

  private void setup() {
    setupSwing();
    setupGraphics();
    setupLogic();
    setupAudio();
  }

  private void setupSwing() {
    GameLogger.log("Swing setup...");
    engineCore.addMouseListener(new MouseClickEventAdapter(eventManager, windowManager));
  }

  private void setupGraphics() {
    GameLogger.log("Graphics setup...");
    Background background = new Background(gameResolution, Color.LIGHT_GRAY);
    graphicController.putGraphicTask("Background", background);
    graphicController.putGraphicTask(GraphicPriority.HIGH,"WindowTask", windowManager);
    graphicController.setFpsLimitEnabled(true);
    graphicController.setFpsLimit(60);
    graphicController.setFpsColor(Color.GREEN);
    graphicController.setInternalFpsDrawerEnabled(true);

    windowManager.addWindow(new TestWindow(new IntBoundingBox(0, 0, 200, 200)));
  }

  private void setupLogic() {
    GameLogger.log("Logic setup...");

  }

  private void setupAudio() {
    GameLogger.log("Audio setup...");

  }

}
