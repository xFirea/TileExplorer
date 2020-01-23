package com.tagteam.tileexplorer.util.swingutil;

import com.tagteam.tileexplorer.game.events.GameEventManager;
import com.tagteam.tileexplorer.game.events.windowclick.MouseClickType;
import com.tagteam.tileexplorer.game.events.windowclick.ClickEvent;
import com.tagteam.tileexplorer.util.IntVect2D;
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

  public MouseClickEventAdapter(GameEventManager eventManager) {
    this.gameEventManager = eventManager;
  }

  private final GameEventManager gameEventManager;

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    IntVect2D position = new IntVect2D(e.getX(), e.getY());
    MouseClickType clickType;

    if (SwingUtilities.isLeftMouseButton(e)) {
      clickType = MouseClickType.LEFT;
    } else if (SwingUtilities.isRightMouseButton(e)) {
      clickType = MouseClickType.RIGHT;
    } else if (SwingUtilities.isMiddleMouseButton(e)) {
      clickType = MouseClickType.MIDDLE;
    } else {
      clickType = MouseClickType.EXTRA;
    }

    ClickEvent event = new ClickEvent(position, clickType);
    gameEventManager.callWindowClick(event);
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
