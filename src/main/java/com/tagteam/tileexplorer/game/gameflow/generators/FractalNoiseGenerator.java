package com.tagteam.tileexplorer.game.gameflow.generators;

import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.game.gameflow.tiles.Environment;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import com.tagteam.tileexplorer.util.math.FractalNoise;
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
public class FractalNoiseGenerator implements TileGenerator {

  public FractalNoiseGenerator(int size, float roughtness) {
    this.fractalNoise = new FractalNoise(roughtness, size, size);
    fractalNoise.initialise();
    this.biomeHeights = ImmutableRangeMap.<Float, Biome>builder()
        .put(Range.atMost(-0.5F), Biome.SEA)
        .put(Range.openClosed(-0.5F, -0.45F), Biome.BEACH)
        .put(Range.openClosed(-0.45F, -0.1F), Biome.PLAINS)
        .put(Range.openClosed(-0.1F, 0.25F), Biome.FOREST)
        .put(Range.openClosed(0.25F, 0.3F), Biome.DENSE_FOREST)
        .put(Range.openClosed(0.3F, 0.35F), Biome.FOREST)
        .put(Range.open(0.35F, 0.75F), Biome.MOUNTAINS)
        .put(Range.atLeast(0.75F), Biome.MOUNTAIN_PEAK)
        .build();
  }

  ThreadLocalRandom random = ThreadLocalRandom.current();
  private final FractalNoise fractalNoise;
  private final RangeMap<Float, Biome> biomeHeights;

  @Override
  public Environment getGeneratedEnvironment(TileMap tileMap, int posX, int posY) {
    float height = fractalNoise.getHeight(posX, posY);
    return new Environment(Objects.requireNonNull(biomeHeights.get(height)), random.nextDouble(-1.5, 1.5));
  }

}