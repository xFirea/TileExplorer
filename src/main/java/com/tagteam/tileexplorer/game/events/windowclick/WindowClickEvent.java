package com.tagteam.tileexplorer.game.events.windowclick;

import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class WindowClickEvent extends ClickEvent {

  public WindowClickEvent(IntVect2D clickedPosition, MouseClickType mouseClickType, GameWindow clickedWindow) {
    super(clickedPosition, mouseClickType);
    this.clickedWindow = clickedWindow;
  }

  @Getter
  private final GameWindow clickedWindow;

}
