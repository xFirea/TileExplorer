package com.tagteam.tileexplorer.game.events.windowclick;

import com.tagteam.tileexplorer.game.events.Cancellable;
import com.tagteam.tileexplorer.game.events.Event;
import com.tagteam.tileexplorer.util.IntVect2D;
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

public class ClickEvent extends Event implements Cancellable {

  public ClickEvent(IntVect2D clickedPosition, MouseClickType mouseClickType) {
    this.clickedPosition = clickedPosition;
    this.mouseClickType = mouseClickType;
  }

  private boolean cancelled = false;
  @Getter
  private final IntVect2D clickedPosition;
  @Getter
  private final MouseClickType mouseClickType;

  @Override
  public boolean isCancelled() {
    return cancelled;
  }

  @Override
  public void setCancelled(boolean set) {
    cancelled = set;
  }

}