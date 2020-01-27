package com.tagteam.tileexplorer.util.game;

import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import java.util.Iterator;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 25.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public class TileMapIterator implements Iterator<Tile> {

  public TileMapIterator(TileMap tileMap) {
    this.tileMap = tileMap;
    this.size = tileMap.getRowAndColumnSize();
  }

  private final TileMap tileMap;
  private final int size;
  private int currentX = 0;
  private int currentY = 0;

  private boolean hasNext = true;

  @Override
  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public Tile next() {
    Tile tile = tileMap.getTile(currentX, currentY);
    if (++currentY == size) {
      hasNext = !(++currentX == size);
      currentY = 0;
    }
    return tile;
  }
}