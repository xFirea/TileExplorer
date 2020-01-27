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
        Tile tile = new Tile(x, y, tileSize, environment);
        new TileGenerateEvent(tile, tileMap).callEvent();
        tileMap.setTile(x, y, tile);
      }
    }
    Set<Tile> seaTiles = Sets.newHashSetWithExpectedSize(tileMap.getRowAndColumnSize() * tileMap.getRowAndColumnSize());
    for (Tile tile : tileMap) {
      if (tile.getEnvironment().getBiome() == Biome.SEA) {
        seaTiles.add(tile);
      }
    }
    while (!seaTiles.isEmpty()) {
      IntVect2D pos = seaTiles.stream().findAny().get().getPostion();
      BiomeFetcher fetcher = new BiomeFetcher(tileMap, pos.getX(), pos.getY());
      HashSet<IntVect2D> seaBatch = fetcher.start();
      if (seaBatch.size() <= lakeMaxSize) {
        for (IntVect2D lakePos : seaBatch) {
          Tile oldTile = tileMap.getTile(lakePos.getX(), lakePos.getY());
          seaTiles.remove(oldTile);
          tileMap.setTile(lakePos.getX(), lakePos.getY(),
              new Tile(lakePos.getX(), lakePos.getY(), tileSize, new Environment(Biome.LAKE, oldTile.getEnvironment().getTemp())));
        }
      } else {
        for (IntVect2D seaPos : seaBatch) {
          seaTiles.remove(tileMap.getTile(seaPos.getX(), seaPos.getY()));
        }
      }
    }
  }

}
