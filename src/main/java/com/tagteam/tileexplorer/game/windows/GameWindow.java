package com.tagteam.tileexplorer.game.windows;

import com.gestankbratwurst.le_engine.graphics.GTask;
import com.google.common.collect.Lists;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.WindowClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.WindowMouseEnterEvent;
import com.tagteam.tileexplorer.game.windows.components.WindowComponent;
import com.tagteam.tileexplorer.util.graphics.UtilGraphics;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import lombok.EqualsAndHashCode;
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

@EqualsAndHashCode
public abstract class GameWindow implements GTask {

  public GameWindow(IntBoundingBox boundingBox, Color backgroundColor) {
    this.boundingBox = boundingBox;
    this.backgroundColor = backgroundColor;
    this.windowComponents = Lists.newArrayList();
  }

  @Getter
  protected IntBoundingBox boundingBox;
  protected Color backgroundColor;
  protected final ArrayList<WindowComponent> windowComponents;

  public IntVect2D getPosition() {
    return boundingBox.getPosition();
  }

  public IntVect2D getRelativePosition(IntVect2D globalVect) {
    IntVect2D boxPos = boundingBox.getLeftCorner();
    return new IntVect2D(globalVect.getX() - boxPos.getX(), globalVect.getY() - boxPos.getY());
  }

  public void close() {
    onClose();
    WindowManager.getInstance().removeWindow(this);
  }

  public void moveTo(IntVect2D newPosition) {
    moveTo(newPosition.getX(), newPosition.getY());
  }

  public void moveTo(int x, int y) {
    int width = this.boundingBox.getWidth();
    int height = this.boundingBox.getHeight();
    boundingBox = new IntBoundingBox(x, y, x + width, y + height);
    onMove(x, y);
  }

  public void focus() {
    WindowManager.getInstance().focusWindow(this);
  }

  public WindowComponent getComponentAt(IntVect2D globalVect) {
    for (int index = windowComponents.size() - 1; index >= 0; index--) {
      WindowComponent component = windowComponents.get(index);
      if (component.isHitLocal(getRelativePosition(globalVect))) {
        return component;
      }
    }
    return null;
  }

  public void checkClick(WindowClickEvent event) {
    WindowComponent hitComponent = getComponentAt(event.getClickedPosition());
    if (hitComponent != null) {
      ComponentClickEvent componentClickEvent = new ComponentClickEvent(event, hitComponent);
      componentClickEvent.callEvent();
      hitComponent.handleClick(componentClickEvent);
      return;
    }
    onClick(event);
  }

  @Override
  public void accept(Graphics graphics) {
    graphics.setColor(backgroundColor);
    UtilGraphics.fillRect(boundingBox, graphics);
    UtilGraphics.drawRectCorner(boundingBox, 2, graphics, Color.BLACK);
    render(graphics);
    for (WindowComponent component : windowComponents) {
      component.accept(graphics);
    }
  }

  public boolean contains(IntVect2D pointer) {
    return boundingBox.contains(pointer);
  }

  public abstract void onOpen();

  public abstract void onClose();

  public abstract void onClick(WindowClickEvent event);

  public abstract boolean canBeDragged();

  protected abstract void onMove(int newX, int newY);

  protected abstract void render(Graphics graphics);

  public abstract void handleEnter(WindowMouseEnterEvent event);

}