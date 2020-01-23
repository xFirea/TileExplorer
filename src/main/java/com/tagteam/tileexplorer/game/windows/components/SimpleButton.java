package com.tagteam.tileexplorer.game.windows.components;

import com.tagteam.tileexplorer.game.events.windowclick.ComponentClickEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.util.graphics.UtilGraphics;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
public abstract class SimpleButton extends WindowComponent {

  private static final String EMPTY = "";

  public SimpleButton(GameWindow host, int relativeX, int relativeY, int width, int height) {
    super(host, relativeX, relativeY, width, height);
  }

  @Getter
  @Setter
  private String middleText = EMPTY;
  @Getter
  @Setter
  private Font textFont = new Font("Arial", Font.BOLD, this.getGlobalBox().getHeight() - 2);
  @Getter
  @Setter
  private Color fontColor = Color.BLACK;
  @Getter
  @Setter
  private Color cornerColor = Color.GRAY;
  @Getter
  @Setter
  private Color fillColor = Color.LIGHT_GRAY;
  @Getter
  @Setter
  private int cornerThickness = 2;

  @Override
  public void accept(Graphics graphics) {
    graphics.setColor(fillColor);
    UtilGraphics.fillRect(getGlobalBox(), graphics);
    UtilGraphics.drawRectCorner(getGlobalBox(), cornerThickness, graphics, cornerColor);
    drawExtra(graphics);
    if (!middleText.equals(EMPTY)) {
      IntVect2D buttonCenter = this.getGlobalBox().getCenter();
      graphics.setColor(fontColor);
      UtilGraphics.drawCenterText(buttonCenter, graphics, middleText, textFont);
    }
  }

  @Override
  public void handleClick(ComponentClickEvent event) {
    onButtonClicked(event);
  }

  public abstract void drawExtra(Graphics graphics);

  public abstract void onButtonClicked(ComponentClickEvent event);

}