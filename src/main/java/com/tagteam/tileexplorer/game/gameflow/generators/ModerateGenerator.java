/*
package com.tagteam.tileexplorer.game.gameflow.generators;

import com.google.common.collect.Lists;
import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.game.gameflow.tiles.Environment;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import com.tagteam.tileexplorer.util.collections.RandomWeightCollection;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ModerateGenerator implements TileGenerator {


  private final double tempOffset = 2;

  @Override
  public Environment getGeneratedEnvironment(TileMap tileMap, int posX, int posY) {

    if ((posX == 0 || posY == 0) || (posX > tileMap.getSize() || posY > tileMap.getSize())) {
      return new Environment(Biome.FOREST, 0);
    }

    ArrayList<Tile> directlyAdjacent = getDirectlyAdjacent(tileMap, posX, posY);
    ArrayList<Tile> diagonalAdjacent = getDiagonalAdjacent(tileMap, posX, posY);

    double averageTemp = 0;

    int waterBodies = 0;

    for (Tile tile : directlyAdjacent) {

      if (tile != null) {
        averageTemp = averageTemp + tile.getEnvironment().getCurrentTemperature() * 2;

        if (tile.getEnvironment().getBiome() == Biome.LAKE) {
          waterBodies++;
        }

      }

    }

    for (Tile tile : diagonalAdjacent) {

      if (tile != null) {
        averageTemp = averageTemp + tile.getEnvironment().getCurrentTemperature();
      }

    }

    averageTemp = averageTemp / (diagonalAdjacent.size() + directlyAdjacent.size());

    RandomWeightCollection<Biome> weightedBiomes = new RandomWeightCollection<>();

    if (directlyAdjacent.size() + diagonalAdjacent.size() == 0) {
      averageTemp = Biome.PLAINS.getBaseTemperature();
    }

    for (Biome biome : Biome.values()) {
      double deltaTemp = Math.abs(averageTemp - biome.getBaseTemperature());

      if (deltaTemp < 1) {
        deltaTemp = 1;
      }

      double weight = 1D / (deltaTemp * 10);

      if (biome == Biome.LAKE) {
        weightedBiomes.add(weight + (waterBodies / 2.8), biome);
      } else {
        weightedBiomes.add(weight, biome);
      }

    }

    double temp = ThreadLocalRandom.current().nextDouble(-(tempOffset / 2), tempOffset / 2);

    Biome randomBiome = weightedBiomes.next();

    return new Environment(randomBiome, temp);

  }

  private ArrayList<Tile> getDirectlyAdjacent(TileMap map, int posX, int posY) {

    ArrayList<Tile> adjacentTiles = Lists.newArrayList();

    if (isValid(map, posX, posY - 1)) {
      adjacentTiles.add(map.getTile(posX, posY - 1));
    }

    if (isValid(map, posX, posY + 1)) {
      adjacentTiles.add(map.getTile(posX, posY + 1));
    }

    if (isValid(map, posX - 1, posY)) {
      adjacentTiles.add(map.getTile(posX - 1, posY));
    }

    if (isValid(map, posX + 1, posY + 1)) {
      adjacentTiles.add(map.getTile(posX, posY + 1));
    }

    return adjacentTiles;
  }

  private ArrayList<Tile> getDiagonalAdjacent(TileMap map, int posX, int posY) {

    ArrayList<Tile> adjacentTiles = Lists.newArrayList();

    if (posX == 0 || posY == 0) {
      return adjacentTiles;
    }

    if (isValid(map, posX - 1, posY - 1)) {
      adjacentTiles.add(map.getTile(posX - 1, posY - 1));
    }

    if (isValid(map, posX + 1, posY + 1)) {
      adjacentTiles.add(map.getTile(posX + 1, posY + 1));
    }

    if (isValid(map, posX - 1, posY + 1)) {
      adjacentTiles.add(map.getTile(posX - 1, posY + 1));
    }

    if (isValid(map, posX + 1, posY - 1)) {
      adjacentTiles.add(map.getTile(posX + 1, posY - 1));
    }

    return adjacentTiles;
  }

  private boolean isValid(TileMap map, int posX, int posY) {

    if ((posX >= map.getSize() || posY >= map.getSize()) || (posX < 0 || posY < 0)) {
      return false;
    }

    return map.getTile(posX, posY) != null;
  }
}*/
