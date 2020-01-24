package com.tagteam.tileexplorer.graphics.mouserenderer;

import com.tagteam.tileexplorer.game.user.CursorRenderer;
import com.tagteam.tileexplorer.game.user.GameCursor;
import com.tagteam.tileexplorer.util.graphics.UtilGraphics;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import java.awt.Graphics;
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
public class WindowFrameMouseRenderer implements CursorRenderer {

  private final Color frameColor;
  private final int frameThickness;
  private final int relativeWindowX;
  private final int relativeWindowY;
  private final int width;
  private final int height;

  @Override
  public void drawCursor(GameCursor cursor, Graphics graphics) {
    IntVect2D position = cursor.getCurrentPosition();
    int startX = position.getX() - relativeWindowX;
    int startY = position.getY() - relativeWindowY;
    IntBoundingBox frameBox = new IntBoundingBox(startX, startY, startX + width, startY + height);
    UtilGraphics.drawRectCorner(frameBox, frameThickness, graphics, frameColor);
  }

}