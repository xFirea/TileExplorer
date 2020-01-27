package com.tagteam.tileexplorer.game.gameflow.tiles.pathfinders;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class IslandFetcher {

  private static final ImmutableSet<Biome> WATER_BIOMES = ImmutableSet.of(Biome.LAKE, Biome.SEA);

  public IslandFetcher(TileMap tileMap, int startX, int startY) {
    this.tileMap = tileMap;
    this.startX = startX;
    this.startY = startY;
  }

  private final TileMap tileMap;
  private final int startX;
  private final int startY;

  private int biomeSize;

  private ArrayDeque<IntVect2D> possibleTiles = Queues.newArrayDeque();

  private HashSet<IntVect2D> islandTiles = Sets.newHashSet();

  public HashSet<IntVect2D> start() {

    if(WATER_BIOMES.contains(tileMap.getTile(startX, startY).getEnvironment().getBiome())){
      return  islandTiles;
    }

    islandTiles.add(new IntVect2D(startX, startY));
    biomeSize = 1;

    possibleTiles.addAll(getAdjacent(new IntVect2D(startX, startY)));

    do {

      validatePossibleTiles();

    } while (!possibleTiles.isEmpty());

    return islandTiles;

  }

  private void validatePossibleTiles() {
    for (int i = 0; i < possibleTiles.size(); i++) {
      IntVect2D pos = possibleTiles.pop();

      if (!islandTiles.contains(pos)) {
        islandTiles.add(pos);
        biomeSize++;

        possibleTiles.addAll(getAdjacent(pos));
      }
    }
  }

  private ArrayList<IntVect2D> getAdjacent(IntVect2D pos) {

    ArrayList<IntVect2D> validTiles = Lists.newArrayListWithExpectedSize(4);

    Tile tile;

    tile = tileMap.getTile(pos.getX() - 1, pos.getY());

    if (tile != null && !WATER_BIOMES.contains(tile.getEnvironment().getBiome())) {
      validTiles.add(new IntVect2D(pos.getX() - 1, pos.getY()));
    }

    tile = tileMap.getTile(pos.getX() + 1, pos.getY());

    if (tile != null && !WATER_BIOMES.contains(tile.getEnvironment().getBiome())) {
      validTiles.add(new IntVect2D(pos.getX() + 1, pos.getY()));
    }

    tile = tileMap.getTile(pos.getX(), pos.getY() - 1);

    if (tile != null && !WATER_BIOMES.contains(tile.getEnvironment().getBiome())) {
      validTiles.add(new IntVect2D(pos.getX(), pos.getY() - 1));
    }

    tile = tileMap.getTile(pos.getX(), pos.getY() + 1);

    if (tile != null && !WATER_BIOMES.contains(tile.getEnvironment().getBiome())) {
      validTiles.add(new IntVect2D(pos.getX(), pos.getY() + 1));
    }

    return validTiles;
  }

}
