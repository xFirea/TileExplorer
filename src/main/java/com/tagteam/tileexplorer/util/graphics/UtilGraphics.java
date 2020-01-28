package com.tagteam.tileexplorer.util.graphics;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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

  private static final Table<BufferedImage, Double, BufferedImage> SCALED_CACHE = HashBasedTable.create();

  public static void drawResizedImage(BufferedImage image, int posX, int posY, double scale, Graphics graphics) {
    BufferedImage scaledImage = SCALED_CACHE.get(image, scale);
    if (scaledImage == null) {
      scaledImage = createResizedCopy(image, (int) (image.getWidth() * scale), (int) (image.getHeight() * scale), false);
      SCALED_CACHE.put(image, scale, scaledImage);
    }
    graphics.drawImage(scaledImage, posX, posY, null);
  }

  public static void drawScaledImage(Image image, IntBoundingBox box, Graphics graphics) {
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

  private static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
    int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
    Graphics2D g = scaledBI.createGraphics();
    if (preserveAlpha) {
      g.setComposite(AlphaComposite.Src);
    }
    g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
    g.dispose();
    return scaledBI;
  }

}
