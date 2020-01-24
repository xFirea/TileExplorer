package com.tagteam.tileexplorer.util.swingutil;

import com.tagteam.tileexplorer.core.TaskController;
import com.tagteam.tileexplorer.game.events.GameEventManager;
import com.tagteam.tileexplorer.game.events.windowclick.ClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseClickType;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragEndEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragStartEvent;
import com.tagteam.tileexplorer.game.events.windowclick.WindowClickEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.WindowManager;
import com.tagteam.tileexplorer.util.GameLogger;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public class MouseClickEventAdapter implements MouseListener {

  public MouseClickEventAdapter(GameEventManager eventManager, WindowManager windowManager) {
    this.gameEventManager = eventManager;
    this.windowManager = windowManager;
  }

  private final GameEventManager gameEventManager;
  private final WindowManager windowManager;
  private IntVect2D startClick;
  private boolean dragStart = false;
  private boolean m1Pressed = false;

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      m1Pressed = true;
      IntVect2D startPoint = new IntVect2D(e.getX(), e.getY());
      dragStart = false;
      TaskController.runTaskLater(() -> {
        if (m1Pressed) {
          startClick = startPoint;
          new MouseDragStartEvent(startClick).callEvent();
          dragStart = true;
        }
      }, 5L);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    IntVect2D position = new IntVect2D(e.getX(), e.getY());
    MouseClickType clickType;

    if (SwingUtilities.isLeftMouseButton(e)) {
      m1Pressed = false;
      if (dragStart) {
        new MouseDragEndEvent(startClick, position).callEvent();
      }
      dragStart = false;
    }

    if (SwingUtilities.isLeftMouseButton(e)) {
      clickType = MouseClickType.LEFT;
    } else if (SwingUtilities.isRightMouseButton(e)) {
      clickType = MouseClickType.RIGHT;
    } else if (SwingUtilities.isMiddleMouseButton(e)) {
      clickType = MouseClickType.MIDDLE;
    } else {
      clickType = MouseClickType.EXTRA;
    }

    GameWindow window = windowManager.getTopWindowAt(position);
    if (window == null) {
      ClickEvent clickEvent = new ClickEvent(position, clickType);
      gameEventManager.callEvent(clickEvent);
    } else {
      WindowClickEvent windowClickEvent = new WindowClickEvent(position, clickType, window);
      gameEventManager.callEvent(windowClickEvent);
      window.checkClick(windowClickEvent);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

}