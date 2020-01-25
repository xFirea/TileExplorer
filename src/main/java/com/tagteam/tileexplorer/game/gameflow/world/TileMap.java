package com.tagteam.tileexplorer.game.gameflow.world;

import com.google.common.base.Preconditions;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class TileMap {

  public TileMap(int size) {
    this.tileArray = new Tile[size][size];
    this.size = size;
  }

  @Getter
  private final int size;
  private final Tile[][] tileArray;

  public Tile getTile(int x, int y) {
    if (!(x < 0 || x >= size || y < 0 || y >= size)) {
      return tileArray[x][y];
    }
    return null;
  }

  public void setTile(int x, int y, Tile tile) {
    Preconditions.checkArgument(!(x < 0 || x >= size || y < 0 || y >= size));
    tileArray[x][y] = tile;
  }

}
