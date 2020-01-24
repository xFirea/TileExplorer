package com.tagteam.tileexplorer.util.swingutil;

import com.tagteam.tileexplorer.game.user.GameCursor;
import com.tagteam.tileexplorer.game.user.GameUser;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import lombok.AllArgsConstructor;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
@AllArgsConstructor
public class MouseMoveEventAdapter implements MouseMotionListener {

  private final GameCursor gameCursor;

  @Override
  public void mouseDragged(MouseEvent e) {
    gameCursor.updatePosition(e);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    gameCursor.updatePosition(e);
  }

}