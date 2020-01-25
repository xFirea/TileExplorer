package com.tagteam.tileexplorer.util.math;

import java.util.Random;
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
public class FractalNoise {

  /**
   * Source of entropy
   */
  private Random random;

  /**
   * Amount of roughness
   */
  private float roughness;

  /**
   * Plasma fractal grid
   */
  private float[][] grid;


  /**
   * Generate a noise source based upon the midpoint displacement fractal.
   *
   * @param rand      The random number generator
   * @param roughness a roughness parameter
   * @param width     the width of the grid
   * @param height    the height of the grid
   */
  public FractalNoise(Random rand, float roughness, int width, int height) {
    this.roughness = roughness / width;
    grid = new float[width][height];
    random = (rand == null) ? new Random() : rand;
  }

  public FractalNoise(float roughness, int width, int height) {
    this(ThreadLocalRandom.current(), roughness, width, height);
  }

  public float getHeight(int x, int y) {
    return grid[x][y];
  }

  public void initialise() {
    int xh = grid.length - 1;
    int yh = grid[0].length - 1;

    // set the corner points
    grid[0][0] = random.nextFloat() - 0.5f;
    grid[0][yh] = random.nextFloat() - 0.5f;
    grid[xh][0] = random.nextFloat() - 0.5f;
    grid[xh][yh] = random.nextFloat() - 0.5f;

    // generate the fractal
    generate(0, 0, xh, yh);
  }


  // Add a suitable amount of random displacement to a point
  private float roughen(float v, int l, int h) {
    return v + roughness * (float) (random.nextGaussian() * (h - l));
  }


  // generate the fractal
  private void generate(int xl, int yl, int xh, int yh) {
    int xm = (xl + xh) / 2;
    int ym = (yl + yh) / 2;
    if ((xl == xm) && (yl == ym)) {
      return;
    }

    grid[xm][yl] = 0.5f * (grid[xl][yl] + grid[xh][yl]);
    grid[xm][yh] = 0.5f * (grid[xl][yh] + grid[xh][yh]);
    grid[xl][ym] = 0.5f * (grid[xl][yl] + grid[xl][yh]);
    grid[xh][ym] = 0.5f * (grid[xh][yl] + grid[xh][yh]);

    float v = roughen(0.5f * (grid[xm][yl] + grid[xm][yh]), xl + yl, yh
        + xh);
    grid[xm][ym] = v;
    grid[xm][yl] = roughen(grid[xm][yl], xl, xh);
    grid[xm][yh] = roughen(grid[xm][yh], xl, xh);
    grid[xl][ym] = roughen(grid[xl][ym], yl, yh);
    grid[xh][ym] = roughen(grid[xh][ym], yl, yh);

    generate(xl, yl, xm, ym);
    generate(xm, yl, xh, ym);
    generate(xl, ym, xm, yh);
    generate(xm, ym, xh, yh);
  }


  /**
   * Dump out as a CSV
   */
  public void printAsCSV() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        System.out.print(grid[i][j]);
        System.out.print(",");
      }
      System.out.println();
    }
  }


  /**
   * Convert to a Boolean array
   *
   * @return the boolean array
   */
  public boolean[][] toBooleans() {
    int w = grid.length;
    int h = grid[0].length;
    boolean[][] ret = new boolean[w][h];
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        ret[i][j] = grid[i][j] < 0;
      }
    }
    return ret;
  }

}
