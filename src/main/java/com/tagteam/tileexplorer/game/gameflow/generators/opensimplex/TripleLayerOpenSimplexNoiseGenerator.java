package com.tagteam.tileexplorer.game.gameflow.generators.opensimplex;

import com.tagteam.tileexplorer.game.gameflow.generators.TileGenerator;
import com.tagteam.tileexplorer.game.gameflow.generators.opensimplex.BeachDomain;
import com.tagteam.tileexplorer.game.gameflow.generators.opensimplex.MountainDomain;
import com.tagteam.tileexplorer.game.gameflow.generators.opensimplex.SimplexHeightDomain;
import com.tagteam.tileexplorer.game.gameflow.generators.opensimplex.ValleyDomain;
import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.game.gameflow.tiles.Environment;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import com.tagteam.tileexplorer.util.math.OpenSimplexNoise;
import java.util.concurrent.ThreadLocalRandom;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 25.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public class TripleLayerOpenSimplexNoiseGenerator implements TileGenerator {

  public TripleLayerOpenSimplexNoiseGenerator(long seed, double seaLevelScale,
      double heightMapScale, double tempScale, double vegetationScale, double seaLevel) {
    this.random = ThreadLocalRandom.current();
    this.seaLevelScale = seaLevelScale;
    this.vegetationScale = vegetationScale;
    this.seaLevel = seaLevel;
    this.heightMapScale = heightMapScale;
    this.tempScale = tempScale;
    this.seaLevelNoise1 = new OpenSimplexNoise(seed);
    this.seaLevelNoise2 = new OpenSimplexNoise(seed + 1);
    this.heightNoise = new OpenSimplexNoise(seed + 10);
    this.tempNoise = new OpenSimplexNoise(seed + -10);
    this.vegetationNoise = new OpenSimplexNoise(seed + -20);

    this.beachDomain = new BeachDomain();
    this.valleyDomain = new ValleyDomain();
    this.mountainDomain = new MountainDomain();
  }

  private final ThreadLocalRandom random;
  private final OpenSimplexNoise seaLevelNoise1;
  private final OpenSimplexNoise seaLevelNoise2;
  private final OpenSimplexNoise heightNoise;
  private final OpenSimplexNoise tempNoise;
  private final OpenSimplexNoise vegetationNoise;
  private final double seaLevelScale;
  private final double heightMapScale;
  private final double tempScale;
  private final double seaLevel;
  private final double vegetationScale;

  private final SimplexHeightDomain beachDomain;
  private final SimplexHeightDomain valleyDomain;
  private final SimplexHeightDomain mountainDomain;

  private double evalSeaLevel(int x, int y) {
    double eval = seaLevelNoise1.eval(x / seaLevelScale * 0.3, y / seaLevelScale * 0.1);
    eval += seaLevelNoise1.eval(x / seaLevelScale * 0.1, y / seaLevelScale * 0.3);
    eval += seaLevelNoise2.eval(x / seaLevelScale, y / seaLevelScale);
    eval /= 3;

    double lowSwings = (seaLevelNoise1.eval(x / seaLevelScale * 0.1, y / seaLevelScale * 0.1)) / 5;
    double highSwings = (seaLevelNoise1.eval(x / seaLevelScale * 3, y / seaLevelScale * 3)) / 8;
    eval += (lowSwings + highSwings) / 2;

    return eval;
  }

  private double evalVegetation(int x, int y) {
    double vegetation = vegetationNoise.eval(x / vegetationScale * 0.5, y / vegetationScale * 0.5);
    double off = vegetationNoise.eval(x / vegetationScale * 1.95, y / vegetationScale * 2);
    off -= vegetationNoise.eval(x / vegetationScale * 2, y / vegetationScale * 1.95);
    off /= 2;
    vegetation += off;
    return vegetation;
  }

  private double evalHeight(int x, int y) {
    return heightNoise.eval(x / heightMapScale * 2.5, y / heightMapScale * 2.5) / 4D;
  }

  private double evalTemp(int x, int y) {
    return (tempNoise.eval(x / tempScale * 0.35, y / tempScale * 0.35) + 0.5) * 40;
  }

  @Override
  public Environment getGeneratedEnvironment(TileMap tileMap, int posX, int posY) {
    double level = evalSeaLevel(posX, posY);
    Biome biome;

    double temp = evalTemp(posX, posY);
    double vegetation = evalVegetation(posX, posY);

    if (level < seaLevel) {
      biome = Biome.SEA;
    } else if (level < seaLevel + 0.025) {
      biome = beachDomain.getBiome(temp, level, vegetation);
    } else if (level < seaLevel + 0.38) {
      biome = valleyDomain.getBiome(temp, level, vegetation);
    } else {
      double deltaMountain = evalHeight(posX, posY);
      if (level - deltaMountain >= seaLevel + 0.2) {
        biome = mountainDomain.getBiome(temp, level, vegetation);
      } else {
        biome = valleyDomain.getBiome(temp, level, vegetation);
      }
    }

    return new Environment(biome, temp);
  }
}
