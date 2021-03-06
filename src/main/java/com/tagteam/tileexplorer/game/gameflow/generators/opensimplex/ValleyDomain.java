package com.tagteam.tileexplorer.game.gameflow.generators.opensimplex;

import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;

public class ValleyDomain implements SimplexHeightDomain {

  @Override
  public Biome getBiome(double temp, double height, double vegetation) {
    if (temp < 7.5) {
      if (temp < -12.5) {
        return Biome.DEEP_ICE_FIELDS;
      }
      if (vegetation < 0) {
        return Biome.SNOW_PLAINS;
      } else if (vegetation < 0.7) {
        return Biome.PINE_FOREST;
      } else {
        return Biome.DENSE_PINE_FOREST;
      }
    } else if (temp < 35.5) {
      if (vegetation < -0.10) {
        return Biome.PLAINS;
      } else if (vegetation < 0.75) {
        return Biome.FOREST;
      } else {
        return Biome.DENSE_FOREST;
      }
    } else {
      if (temp > 50) {
        return Biome.DESERT;
      }
      if (vegetation < 0.5) {
        return Biome.DESERT;
      } else if (vegetation < 0.75) {
        return Biome.SAVANNAH;
      } else {
        return Biome.JUNGLE;
      }
    }
  }
}
