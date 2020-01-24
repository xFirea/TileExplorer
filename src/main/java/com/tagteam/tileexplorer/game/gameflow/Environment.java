package com.tagteam.tileexplorer.game.gameflow;

import lombok.Getter;
import lombok.Setter;

public class Environment {

  public Environment(Biome biome) {
    this(biome, 0D);
  }

  public Environment(Biome biome, double tempOffset) {
    this.biome = biome;
    this.tempOffset = tempOffset;
  }

  private final Biome biome;
  @Getter
  @Setter
  private double tempOffset;

  public double getCurrentTemperature() {
    return biome.getBaseTemperature() + tempOffset;
  }

}
