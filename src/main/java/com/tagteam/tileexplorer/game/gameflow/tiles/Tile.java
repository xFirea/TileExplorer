package com.tagteam.tileexplorer.game.gameflow.tiles;

import com.gestankbratwurst.le_engine.graphics.GTask;
import com.tagteam.tileexplorer.util.graphics.UtilGraphics;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import lombok.Getter;
import lombok.Setter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public class Tile implements GTask {

  public Tile(int boardX, int boardY, int tileSize, Environment environment) {
    this.boardX = boardX;
    this.boardY = boardY;
    this.tileSize = tileSize;
    this.environment = environment;
  }

  private final int boardX;
  private final int boardY;
  @Setter
  private int tileSize;
  private IntVect2D currentGlobalPosition;

  @Getter
  private final Environment environment;

  public Color getColor() {
    return environment.getColor();
  }

  public void render(IntVect2D currentGlobalPosition, Graphics graphics) {
    this.currentGlobalPosition = currentGlobalPosition;
    accept(graphics);
  }

  @Override
  public void accept(Graphics graphics) {
    graphics.setColor(getColor());
    IntBoundingBox box = new IntBoundingBox(currentGlobalPosition.getX(), currentGlobalPosition.getY(), currentGlobalPosition
        .getX() + tileSize,
        currentGlobalPosition.getY() + tileSize);
    BufferedImage image = environment.getBiome().getBufferedImage();
    UtilGraphics.drawScaledImage(image, box, graphics);
    //UtilGraphics.fillRect(box, graphics);
  }

}