package com.tagteam.tileexplorer.game.windows.components;

import com.tagteam.tileexplorer.core.AudioController;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragEndEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragStartEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import java.awt.Color;
import java.awt.Graphics;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class CloseButton extends SimpleButton {

  public CloseButton(GameWindow host, int width, int height) {
    super(host, host.getBoundingBox().getWidth() - width, 0, width, height);
    this.setFillColor(new Color(201, 36, 36));
    this.setMiddleText("X");
  }

  @Override
  public void drawExtra(Graphics graphics) {

  }

  @Override
  public void onButtonClicked(ComponentClickEvent event) {
    AudioController.play("UI_CLICK_3");
    this.host.close();
  }

  @Override
  public void handleDragStart(MouseDragStartEvent event) {

  }

  @Override
  public void handleDragEnd(MouseDragEndEvent event) {

  }

}