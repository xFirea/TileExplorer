package com.tagteam.tileexplorer.game.gameflow.generators.opensimplex;

import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;

public class MountainDomain implements SimplexHeightDomain {

  @Override
  public Biome getBiome(double temp, double height, double vegetation) {

    if(temp > 42.5){
      return Biome.DESERT;
    }

    if (height > 0.55) {

      System.out.println("[Debug] Mountainpeak generated!");

      return Biome.MOUNTAIN_PEAK;
    }
    if (temp < 5 && vegetation > 0.5) {
      return Biome.MOUNTAIN_FOREST;
    }
    return Biome.MOUNTAINS;
  }

}
