package com.tagteam.tileexplorer.game.gameflow.generators.opensimplex;

import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;

public class MountainDomain implements SimplexHeightDomain {

  @Override
  public Biome getBiome(double temp, double height, double vegetation) {
    if (height > 0.925) {
      return Biome.MOUNTAIN_PEAK;
    }
    return Biome.MOUNTAINS;
  }
}
