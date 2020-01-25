package com.tagteam.tileexplorer.game.gameflow;

import com.gestankbratwurst.le_engine.graphics.GTask;
import com.tagteam.tileexplorer.util.graphics.UtilGraphics;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import java.awt.Graphics;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
public class Tile implements GTask {

  private final int boardX;
  private final int boardY;
  private final int tileSize;
  private final IntVect2D globalPosition;

  @Getter
  private final Environment environment;

  public Color getColor() {
    return environment.getColor();
  }

  @Override
  public void accept(Graphics graphics) {
    graphics.setColor(getColor());
    IntBoundingBox box = new IntBoundingBox(globalPosition.getX(), globalPosition.getY(), globalPosition.getX() + tileSize,
        globalPosition.getY() + tileSize);
    UtilGraphics.fillRect(box, graphics);
  }

}