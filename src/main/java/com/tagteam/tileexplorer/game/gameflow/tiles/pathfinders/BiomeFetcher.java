package com.tagteam.tileexplorer.game.gameflow.tiles.pathfinders;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 25.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public class BiomeFetcher {

  public BiomeFetcher(TileMap tileMap, int startX, int startY) {
    this.tileMap = tileMap;
    this.startX = startX;
    this.startY = startY;
  }

  private final TileMap tileMap;
  private final int startX;
  private final int startY;

  private int biomeSize;

  private ArrayDeque<IntVect2D> possibleTiles = Queues.newArrayDeque();

  private HashSet<IntVect2D> biomeTiles = new HashSet<>();
  private Biome biome;

  public HashSet<IntVect2D> start() {

    biomeTiles.add(new IntVect2D(startX, startY));
    biomeSize = 1;

    biome = tileMap.getTile(startX, startY).getEnvironment().getBiome();

    possibleTiles.addAll(getAdjacent(new IntVect2D(startX, startY)));

    do {

      validatePossibleTiles();

    } while (!possibleTiles.isEmpty());

    return biomeTiles;
  }

  private void validatePossibleTiles() {
    for (int i = 0; i < possibleTiles.size(); i++) {
      IntVect2D pos = possibleTiles.pop();

      if (!biomeTiles.contains(pos)) {
        biomeTiles.add(pos);
        biomeSize++;

        possibleTiles.addAll(getAdjacent(pos));
      }
    }
  }

  private ArrayList<IntVect2D> getAdjacent(IntVect2D pos) {

    ArrayList<IntVect2D> validTiles = Lists.newArrayListWithExpectedSize(4);

    Tile tile;

    tile = tileMap.getTile(pos.getX() - 1, pos.getY());

    if (tile != null && tile.getEnvironment().getBiome() == biome) {
      validTiles.add(new IntVect2D(pos.getX() - 1, pos.getY()));
    }

    tile = tileMap.getTile(pos.getX() + 1, pos.getY());

    if (tile != null && tile.getEnvironment().getBiome() == biome) {
      validTiles.add(new IntVect2D(pos.getX() + 1, pos.getY()));
    }

    tile = tileMap.getTile(pos.getX(), pos.getY() - 1);

    if (tile != null && tile.getEnvironment().getBiome() == biome) {
      validTiles.add(new IntVect2D(pos.getX(), pos.getY() - 1));
    }

    tile = tileMap.getTile(pos.getX(), pos.getY() + 1);

    if (tile != null && tile.getEnvironment().getBiome() == biome) {
      validTiles.add(new IntVect2D(pos.getX(), pos.getY() + 1));
    }

    return validTiles;
  }


}
