package com.tagteam.tileexplorer.startscreen;

import com.tagteam.tileexplorer.game.events.windowclick.ComponentClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentMouseEnterEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragEndEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragStartEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.components.WindowComponent;
import com.tagteam.tileexplorer.util.graphics.UtilGraphics;
import java.awt.Color;
import java.awt.Graphics;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 28.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class MenuComponent extends WindowComponent {

  public MenuComponent(GameWindow host, int relativeX, int relativeY, int width, int height) {
    super(host, relativeX, relativeY, width, height);
  }

  @Override
  public void handleClick(ComponentClickEvent event) {

  }

  @Override
  public void handleDragStart(MouseDragStartEvent event) {

  }

  @Override
  public void handleDragEnd(MouseDragEndEvent event) {

  }

  @Override
  public void handleMouseEnter(ComponentMouseEnterEvent event) {

  }

  @Override
  public void accept(Graphics graphics) {
    graphics.setColor(Color.DARK_GRAY);
    UtilGraphics.fillRect(this.getGlobalBox(), graphics);
    UtilGraphics.drawRectCorner(this.getGlobalBox(), 3, graphics, Color.GRAY);
  }

}
