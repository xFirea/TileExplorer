package com.tagteam.tileexplorer.game.gameflow.generators.opensimplex;

import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;

public class ValleyDomain implements SimplexHeightDomain {

  @Override
  public Biome getBiome(double temp, double height, double vegetation) {

    if (temp < 0) {
      // COLD
    }else if(temp < 25){

    }


  }
}
