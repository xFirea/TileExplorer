package com.tagteam.tileexplorer;

import com.tagteam.tileexplorer.game.gameflow.Environment;
import com.tagteam.tileexplorer.game.gameflow.Tile;
import com.tagteam.tileexplorer.game.user.GameUser;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class TestTile extends Tile {

  public TestTile(int boardX, int boardY, int tileSize, IntVect2D globalPosition, Environment environment) {
    super(boardX, boardY, tileSize, globalPosition, environment);
  }

  private final ThreadLocalRandom random = ThreadLocalRandom.current();
  private final Color color = new Color(random.nextInt(50, 70), random.nextInt(50, 110), random.nextInt(50, 70));

  @Override
  public void onTileEnter(GameUser user) {

  }

  @Override
  protected Color getColor() {
    return color;
  }
}
