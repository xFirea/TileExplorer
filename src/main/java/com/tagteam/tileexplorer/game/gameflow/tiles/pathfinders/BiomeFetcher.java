package com.tagteam.tileexplorer.game.gameflow.tiles.pathfinders;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.game.gameflow.world.Direction;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
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
    this(tileMap.getTile(startX, startY));
  }

  public BiomeFetcher(Tile startTile) {
    this.startTile = startTile;
  }

  private final Tile startTile;

  private ArrayDeque<Tile> possibleTiles = Queues.newArrayDeque();

  private HashSet<Tile> biomeTiles = Sets.newHashSet();
  private Biome biome;

  public HashSet<Tile> start() {

    biomeTiles.add(startTile);

    biome = startTile.getEnvironment().getBiome();

    possibleTiles.addAll(getAdjacent(startTile));

    do {

      validatePossibleTiles();

    } while (!possibleTiles.isEmpty());

    return biomeTiles;
  }

  private void validatePossibleTiles() {
    for (int i = 0; i < possibleTiles.size(); i++) {
      Tile pos = possibleTiles.pop();

      if (!biomeTiles.contains(pos)) {
        biomeTiles.add(pos);

        possibleTiles.addAll(getAdjacent(pos));
      }
    }
  }

  private ArrayList<Tile> getAdjacent(Tile tile) {

    ArrayList<Tile> validTiles = Lists.newArrayListWithExpectedSize(4);

    for (Direction direction : Direction.values()) {
      Tile relativeTile = tile.getRelative(direction);
      if (relativeTile != null && tile.getEnvironment().getBiome() == biome) {
        validTiles.add(relativeTile);
      }
    }

    return validTiles;
  }


}
