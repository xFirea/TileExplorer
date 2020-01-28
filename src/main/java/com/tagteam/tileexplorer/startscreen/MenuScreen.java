package com.tagteam.tileexplorer.startscreen;

import com.gestankbratwurst.le_engine.audio.GameAudioController;
import com.gestankbratwurst.le_engine.startmenu.GameResolution;
import com.tagteam.tileexplorer.core.AudioController;
import com.tagteam.tileexplorer.core.TileExplorerCore;
import com.tagteam.tileexplorer.game.events.windowclick.WindowClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.WindowMouseEnterEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.WindowManager;
import com.tagteam.tileexplorer.util.graphics.UtilGraphics;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import org.apache.commons.io.IOUtils;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 27.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class MenuScreen extends GameWindow {

  public MenuScreen(GameResolution resolution, WindowManager windowManager, GameAudioController gameAudioController) {
    super(new IntBoundingBox(0, 0, resolution.getWidth(), resolution.getHeight()), Color.GRAY);
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    this.gameAudioController = gameAudioController;
    this.windowManager = windowManager;
    background = toolkit.getImage(TileExplorerCore.class.getClassLoader().getResource("gifs/startBack.gif"));
    logo = toolkit.getImage(TileExplorerCore.class.getClassLoader().getResource("images/te_logo.png"));
    this.gameResolution = resolution;
    int middle = resolution.getWidth() / 2;
    int leftHit = middle - (int) (resolution.getWidth() / 100D * 20D);
    int rightHit = middle + (middle - leftHit);
    int logoHeight = (int) ((rightHit - leftHit) * 9D / 16D / 3);
    int topPadding = (int) (resolution.getHeight() / 100D * 10D);
    logoBox = new IntBoundingBox(leftHit, topPadding, rightHit, topPadding + logoHeight);
    copyRightFont = new Font("Arial", Font.BOLD, (int) (12D / 640D * gameResolution.getHeight()));
  }

  private final GameResolution gameResolution;
  private final Image background;
  private final Image logo;
  private final IntBoundingBox logoBox;
  private final WindowManager windowManager;
  private final Font copyRightFont;
  private final GameAudioController gameAudioController;

  @Override
  public void onOpen() {
    int menuX = logoBox.getPosition().getX();
    int menuY = logoBox.getPosition().getY() + logoBox.getHeight() + 4;
    int menuWidth = logoBox.getWidth();
    int menuHeight = (int) (gameResolution.getHeight() / 100D * 55D);
    MenuComponent menuComponent = new MenuComponent(this, menuX, menuY, menuWidth, menuHeight);
    this.windowComponents.add(menuComponent);

    BufferedInputStream inputStream = IOUtils.buffer(TileExplorerCore.class.getClassLoader().getResourceAsStream("sounds/main_theme.wav"));
    gameAudioController.createClip(inputStream, "MainTheme");
    AudioController.play("MainTheme");
  }

  @Override
  public void onClose() {

  }

  @Override
  public void onClick(WindowClickEvent event) {

  }

  @Override
  public boolean canBeDragged() {
    return false;
  }

  @Override
  protected void onMove(int newX, int newY) {

  }

  private void renderMusicLogo(Graphics graphics) {
    graphics.setFont(copyRightFont);
    graphics.setColor(Color.ORANGE);
    String cr = "© Music by HeatleyBros";
    graphics.drawString(cr, 4, gameResolution.getHeight() - 4);
  }

  @Override
  protected void render(Graphics graphics) {
    UtilGraphics.drawScaledImage(background, this.boundingBox, graphics);
    UtilGraphics.drawScaledImage(logo, this.logoBox, graphics);
    graphics.setColor(Color.BLACK);
    renderMusicLogo(graphics);
  }

  @Override
  public void handleEnter(WindowMouseEnterEvent event) {

  }

}
