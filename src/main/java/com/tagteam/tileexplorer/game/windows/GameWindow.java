package com.tagteam.tileexplorer.game.windows;

import com.gestankbratwurst.le_engine.graphics.GTask;
import com.google.common.collect.Lists;
import com.tagteam.tileexplorer.util.GraphicsUtil;
import com.tagteam.tileexplorer.util.IntBoundingBox;
import com.tagteam.tileexplorer.util.IntVect2D;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public abstract class GameWindow implements GTask {

  public GameWindow(IntBoundingBox boundingBox, Color backgroundColor) {
    this.boundingBox = boundingBox;
    this.backgroundColor = backgroundColor;
  }

  private IntBoundingBox boundingBox;
  private Color backgroundColor;

  private List<GTask> subTasks = Lists.newArrayList();

  @Override
  public void accept(Graphics graphics) {
    graphics.setColor(backgroundColor);
    GraphicsUtil.fillRect(boundingBox, graphics);
    GraphicsUtil.drawRectCorner(boundingBox, 2, graphics, Color.BLACK);

    for (GTask task : subTasks) {
      task.accept(graphics);
    }
  }

  public boolean contains(IntVect2D pointer) {
    return boundingBox.contains(pointer);
  }

  public abstract void onOpen();

  public abstract void onClose();

  public abstract void onClick(int x, int y);

}