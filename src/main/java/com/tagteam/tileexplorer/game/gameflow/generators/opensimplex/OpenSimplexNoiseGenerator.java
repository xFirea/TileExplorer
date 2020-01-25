package com.tagteam.tileexplorer.game.gameflow.generators.opensimplex;

import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.tagteam.tileexplorer.game.gameflow.generators.TileGenerator;
import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.game.gameflow.tiles.Environment;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import com.tagteam.tileexplorer.util.math.OpenSimplexNoise;
import java.util.Objects;
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
public class OpenSimplexNoiseGenerator implements TileGenerator {

  public OpenSimplexNoiseGenerator(long seed, double scaleInversion) {
    this.scaleInversion = scaleInversion;
    this.openSimplexNoise = new OpenSimplexNoise(seed);
    this.biomeHeights = ImmutableRangeMap.<Double, Biome>builder()
        .put(Range.atMost(-0.5D), Biome.SEA)
        .put(Range.openClosed(-0.5D, -0.45D), Biome.BEACH)
        .put(Range.openClosed(-0.45D, -0.1D), Biome.PLAINS)
        .put(Range.openClosed(-0.1D, 0.25D), Biome.FOREST)
        .put(Range.openClosed(0.25D, 0.3D), Biome.DENSE_FOREST)
        .put(Range.openClosed(0.3D, 0.35D), Biome.FOREST)
        .put(Range.open(0.35D, 0.75D), Biome.MOUNTAINS)
        .put(Range.atLeast(0.75D), Biome.MOUNTAIN_PEAK)
        .build();
  }

  private final ThreadLocalRandom random = ThreadLocalRandom.current();
  private final OpenSimplexNoise openSimplexNoise;
  private final RangeMap<Double, Biome> biomeHeights;
  private final double scaleInversion;

  @Override
  public Environment getGeneratedEnvironment(TileMap tileMap, int posX, int posY) {
    double heigth = openSimplexNoise.eval(posX / scaleInversion, posY / scaleInversion);
    return new Environment(Objects.requireNonNull(biomeHeights.get(heigth)), random.nextDouble(-1.5, 1.5));
  }

}
