package com.tagteam.tileexplorer.util;

import java.awt.Color;
import java.awt.Graphics;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public class GraphicsUtil {

  public static void drawRect(IntBoundingBox boundingBox, Graphics graphics) {
    IntVect2D leftC = boundingBox.getLeftCorner();
    IntVect2D rightC = boundingBox.getRightCorner();
    int x = leftC.getX();
    int y = leftC.getY();
    int width = rightC.getX() - x;
    int height = rightC.getY() - y;
    graphics.drawRect(x, y, width, height);
  }

  public static void fillRect(IntBoundingBox boundingBox, Graphics graphics) {
    IntVect2D leftC = boundingBox.getLeftCorner();
    IntVect2D rightC = boundingBox.getRightCorner();
    int x = leftC.getX();
    int y = leftC.getY();
    int width = rightC.getX() - x;
    int height = rightC.getY() - y;
    graphics.fillRect(x, y, width, height);
  }

  public static void drawRectCorner(IntBoundingBox boundingBox, int thickness, Graphics graphics, Color startColor) {
    IntVect2D leftC = boundingBox.getLeftCorner();
    IntVect2D rightC = boundingBox.getRightCorner();
    int x = leftC.getX();
    int y = leftC.getY();
    int width = rightC.getX() - x;
    int height = rightC.getY() - y;
    graphics.setColor(startColor);
    for (int iteration = 0; iteration < thickness; iteration++) {
      graphics.drawRect(x, y, width, height);
      if (x-- < 0) {
        x = 0;
      }
      if (y-- < 0) {
        y = 0;
      }
      width += 2;
      height += 2;
      startColor = ColorUtil.brighten(startColor);
      graphics.setColor(startColor);
    }
  }

}
