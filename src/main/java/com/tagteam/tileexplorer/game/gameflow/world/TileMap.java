package com.tagteam.tileexplorer.game.gameflow.world;

import com.google.common.base.Preconditions;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.util.game.TileMapIterator;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
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
    this.rowAndColumnSize = size;
    this.randomHash = ThreadLocalRandom.current().nextInt();
  }

  @Getter
  private final int rowAndColumnSize;
  private final int randomHash;
  private final Tile[][] tileArray;

  private boolean tilesChanged = false;
  private double averageTemp = 0D;

  private void recalculateAverageTemp() {
    double tempSum = 0;
    for (Tile tile : this) {
      tempSum += tile.getEnvironment().getTemp();
    }
    tempSum /= (rowAndColumnSize * rowAndColumnSize);
    averageTemp = tempSum;
  }

  public double getAverageTemp() {
    if (tilesChanged) {
      recalculateAverageTemp();
    }
    return averageTemp;
  }

  public Tile getTile(int x, int y) {
    if (!(x < 0 || x >= rowAndColumnSize || y < 0 || y >= rowAndColumnSize)) {
      return tileArray[x][y];
    }
    return null;
  }

  public void setTile(int x, int y, Tile tile) {
    Preconditions.checkArgument(!(x < 0 || x >= rowAndColumnSize || y < 0 || y >= rowAndColumnSize));
    tilesChanged = true;
    tileArray[x][y] = tile;
  }

  @Override
  public Iterator<Tile> iterator() {
    return new TileMapIterator(this);
  }

  @Override
  public void forEach(Consumer<? super Tile> action) {
    for (int x = 0; x < rowAndColumnSize; x++) {
      for (int y = 0; y < rowAndColumnSize; y++) {
        action.accept(getTile(x, y));
      }
    }
  }

  @Override
  public int hashCode() {
    return randomHash;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof TileMap)) {
      return false;
    }
    return other == this;
  }

}