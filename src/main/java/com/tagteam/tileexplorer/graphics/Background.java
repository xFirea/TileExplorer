package com.tagteam.tileexplorer.graphics;

    import com.gestankbratwurst.le_engine.graphics.GTask;
    import com.gestankbratwurst.le_engine.startmenu.GameResolution;
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

public class Background implements GTask {

  public Background(GameResolution resolution, Color backgroundColor) {
    this.resolution = resolution;
    this.backgroundColor = backgroundColor;
  }

  private final GameResolution resolution;
  private final Color backgroundColor;

  @Override
  public void accept(Graphics graphics) {
    graphics.setColor(backgroundColor);
    graphics.fillRect(0, 0, resolution.getWidth(), resolution.getHeight());
  }

}