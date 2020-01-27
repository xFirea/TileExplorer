package com.tagteam.tileexplorer.game.user;

import com.gestankbratwurst.le_engine.graphics.GTask;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.components.WindowComponent;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import lombok.Getter;
import lombok.Setter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class GameCursor implements GTask {

  @Getter
  @Setter
  private GameWindow currentHeldWindow = null;
  @Getter
  @Setter
  private WindowComponent currentHeldComponent = null;
  @Getter
  @Setter
  private CursorRenderer cursorDrawer = null;
  @Getter
  private IntVect2D currentPosition;
  @Getter
  @Setter
  private GameWindow currentlyHoveringWindow;
  @Getter
  @Setter
  private WindowComponent currentlyHoveringComponent;

  public void updatePosition(MouseEvent event) {
    currentPosition = new IntVect2D(event.getX(), event.getY());
  }

  @Override
  public void accept(Graphics graphics) {
    if (cursorDrawer != null) {
      cursorDrawer.drawCursor(this, graphics);
    }
  }

}