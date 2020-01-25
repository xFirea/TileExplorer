package com.tagteam.tileexplorer.util.graphics;

import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public class UtilGraphics {

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
      startColor = UtilColor.brighten(startColor);
      graphics.setColor(startColor);

    }
  }

  //private static final Table<BufferedImage, Double, BufferedImage> SCALED_CACHE = HashBasedTable.create();

  public static void drawScaledImage(BufferedImage image, IntBoundingBox box, Graphics graphics) {
    int x = box.getPosition().getX();
    int y = box.getPosition().getY();
    int width = box.getWidth();
    int height = box.getHeight();
    graphics.drawImage(image, x, y, width, height, null);
  }

  public static void drawCenterText(IntVect2D position, Graphics graphics, String text, Font font) {
    graphics.setFont(font);
    FontMetrics fontMetrics = graphics.getFontMetrics(font);
    int w2 = fontMetrics.stringWidth(text) / 2;
    int h2 = position.getY() - (fontMetrics.getHeight() / 2) + fontMetrics.getAscent();
    graphics.drawString(text, position.getX() - w2, h2);
  }


}
