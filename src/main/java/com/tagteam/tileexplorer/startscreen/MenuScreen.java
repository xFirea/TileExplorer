package com.tagteam.tileexplorer.startscreen;

import com.gestankbratwurst.le_engine.startmenu.GameResolution;
import com.tagteam.tileexplorer.core.TileExplorerCore;
import com.tagteam.tileexplorer.game.events.windowclick.WindowClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.WindowMouseEnterEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.WindowManager;
import com.tagteam.tileexplorer.util.graphics.UtilGraphics;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

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

  public MenuScreen(GameResolution resolution, WindowManager windowManager) {
    super(new IntBoundingBox(0, 0, resolution.getWidth(), resolution.getHeight()), Color.GRAY);
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    this.windowManager = windowManager;
    background = toolkit.getImage(TileExplorerCore.class.getClassLoader().getResource("gifs/startBack.gif"));
  }

  private final Image background;
  private final WindowManager windowManager;

  @Override
  public void onOpen() {

  }

  @Override
  public void onClose() {

  }

  @Override
  public void onClick(WindowClickEvent event) {
    System.out.println("Click");
  }

  @Override
  public boolean canBeDragged() {
    return false;
  }

  @Override
  protected void onMove(int newX, int newY) {

  }

  @Override
  protected void render(Graphics graphics) {
    UtilGraphics.drawScaledImage(background, this.boundingBox, graphics);
    //graphics.drawImage(background, 0, 0, null);
    graphics.setColor(Color.BLACK);
  }

  @Override
  public void handleEnter(WindowMouseEnterEvent event) {

  }
}
