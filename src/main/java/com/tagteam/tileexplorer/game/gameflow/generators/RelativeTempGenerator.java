package com.tagteam.tileexplorer.game.gameflow.generators;

import com.google.common.collect.Lists;
import com.tagteam.tileexplorer.game.gameflow.tiles.Environment;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.util.collections.RandomWeightCollection;
import java.util.ArrayList;

public class RelativeTempGenerator implements TileGenerator {

  @Override
  public Environment getGeneratedEnvironment(TileMap tileMap, int posX, int posY) {

    ArrayList<Tile> adjacentTiles = Lists.newArrayList();

    double averageTemp = 0;
    double tempOffset = 0;

    for (int x = posX - 1; x < posX + 1; x++) {
      for (int y = posY - 1; y < posY + 1; y++) {

        if ((x >= 0 && y >= 0) && (x < tileMap.getSize() && y < tileMap.getSize()) && tileMap.getTile(x, y) != null && !(x == posX
            && y == posY)) {
          adjacentTiles.add(tileMap.getTile(x, y));
        }

      }

    }

    for (Tile tile : adjacentTiles) {
      averageTemp += tile.getEnvironment().getCurrentTemperature();
    }

    RandomWeightCollection<Biome> weightedBiomes = new RandomWeightCollection<>();

    averageTemp = averageTemp / adjacentTiles.size();

    if (adjacentTiles.size() == 0) {
      averageTemp = Biome.PLAINS.getBaseTemperature();
    }

    for (Biome biome : Biome.values()) {
      double deltaTemp = Math.abs(averageTemp - biome.getBaseTemperature());

      if (deltaTemp < 1) {
        deltaTemp = 1;
      }

      double weight = 1D / deltaTemp;
      weightedBiomes.add(weight, biome);
    }

    Biome randomBiom = weightedBiomes.next();
    return new Environment(randomBiom, tempOffset);
  }

}
