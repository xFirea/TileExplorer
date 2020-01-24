package com.tagteam.tileexplorer.game.windows.components;

import com.gestankbratwurst.le_engine.graphics.GTask;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragEndEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragStartEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.math.IntVect2D;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public abstract class WindowComponent implements GTask {

  public WindowComponent(GameWindow host, int relativeX, int relativeY, int width, int height) {
    this.relativeX = relativeX;
    this.relativeY = relativeY;
    this.width = width;
    this.height = height;
    this.host = host;
  }

  protected final GameWindow host;
  protected final int relativeX;
  protected final int relativeY;
  protected final int width;
  protected final int height;

  public int getGlobalX() {
    return host.getPosition().getX() + relativeX;
  }

  public int getGlobalY() {
    return host.getPosition().getY() + relativeY;
  }

  public IntBoundingBox getRelativeBox() {
    return new IntBoundingBox(new IntVect2D(relativeX, relativeY), new IntVect2D(width + relativeX, height + relativeY), false);
  }

  public IntBoundingBox getGlobalBox() {
    int globalX = getGlobalX();
    int globalY = getGlobalY();
    return new IntBoundingBox(new IntVect2D(globalX, globalY), new IntVect2D(width + globalX, height + globalY), false);
  }

  public boolean isHit(IntVect2D globalPosition) {
    return getGlobalBox().contains(globalPosition);
  }

  public boolean isHitLocal(IntVect2D localPosition) {
    return getRelativeBox().contains(localPosition);
  }

  public abstract void handleClick(ComponentClickEvent event);

  public abstract void handleDragStart(MouseDragStartEvent event);

  public abstract void handleDragEnd(MouseDragEndEvent event);

}
