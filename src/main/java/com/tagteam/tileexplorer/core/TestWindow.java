package com.tagteam.tileexplorer.core;

import com.gestankbratwurst.le_engine.audio.GameAudioController;
import com.tagteam.tileexplorer.game.events.windowclick.WindowClickEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.components.CloseButton;
import com.tagteam.tileexplorer.util.GameLogger;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import java.awt.Color;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class TestWindow extends GameWindow {

  public TestWindow(IntBoundingBox boundingBox) {
    super(boundingBox, Color.DARK_GRAY);
    this.windowComponents.add(new CloseButton(this, 18, 18));
  }

  @Override
  public void onOpen() {
    GameLogger.log("Open");
  }

  @Override
  public void onClose() {
    GameLogger.log("Close");
  }

  @Override
  public void onClick(WindowClickEvent event) {
    GameLogger.log("I got clicked");
  }

  @Override
  public boolean canBeDragged() {
    return true;
  }

  @Override
  protected void onMove(int newX, int newY) {
    GameLogger.log("I got dragged");
  }

}