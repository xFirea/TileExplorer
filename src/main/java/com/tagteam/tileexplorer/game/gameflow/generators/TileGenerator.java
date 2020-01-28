package com.tagteam.tileexplorer.game.gameflow.generators;

import com.google.common.collect.Sets;
import com.tagteam.tileexplorer.game.events.game.TileGenerateEvent;
import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.game.gameflow.tiles.Environment;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.game.gameflow.tiles.pathfinders.BiomeFetcher;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.util.HashSet;
import java.util.Set;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public interface TileGenerator {

  Environment getGeneratedEnvironment(TileMap tileMap, int posX, int posY);

  default long getSeed() {
    return 0;
  }

  default void fillMap(TileMap tileMap, int tileSize, int lakeMaxSize) {
    for (int x = 0; x < tileMap.getRowAndColumnSize(); x++) {
      for (int y = 0; y < tileMap.getRowAndColumnSize(); y++) {
        Environment environment = getGeneratedEnvironment(tileMap, x, y);
        Tile tile = new Tile(tileMap, x, y, tileSize, environment);
        new TileGenerateEvent(tile, tileMap).callEvent();
        tileMap.setTile(x, y, tile);
      }
    }
    Set<Tile> seaTiles = Sets.newHashSet();
    for (Tile tile : tileMap) {
      if (tile.getEnvironment().getBiome() == Biome.SEA) {
        seaTiles.add(tile);
      }
    }
    while (!seaTiles.isEmpty()) {
      Tile startTile = seaTiles.stream().findAny().get();
      seaTiles.remove(startTile);
      BiomeFetcher fetcher = new BiomeFetcher(startTile);
      HashSet<Tile> seaBatch = fetcher.start();
      if (seaBatch.size() <= lakeMaxSize) {
        for (Tile lakeTile : seaBatch) {
          IntVect2D lakePos = lakeTile.getPostion();
          Tile newTile = new Tile(tileMap, lakePos.getX(), lakePos.getY(), tileSize,
              new Environment(Biome.LAKE, lakeTile.getEnvironment().getTemp()));
          tileMap.setTile(lakePos.getX(), lakePos.getY(), newTile);
        }
      }

      seaTiles.removeAll(seaBatch);
    }
  }

}
