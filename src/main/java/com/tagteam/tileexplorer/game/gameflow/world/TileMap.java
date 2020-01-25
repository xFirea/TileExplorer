package com.tagteam.tileexplorer.game.gameflow.world;

import com.google.common.base.Preconditions;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.util.game.TileMapIterator;
import java.util.Iterator;
import java.util.function.Consumer;
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
public class TileMap implements Iterable<Tile> {

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

  @Override
  public Iterator<Tile> iterator() {
    return new TileMapIterator(this);
  }

  @Override
  public void forEach(Consumer<? super Tile> action) {
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        action.accept(getTile(x, y));
      }
    }
  }

}