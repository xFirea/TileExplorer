package com.tagteam.tileexplorer.util.graphics;

import com.google.common.base.Preconditions;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class UtilColor {

  // TODO compute brighter color
  public static Color brighten(Color color) {
    return color;
  }

  public static Color getRandomColor() {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
  }

  public static void randomShadeOffset(int[] rgb, int max) {
    Preconditions.checkArgument(rgb.length == 3);
    ThreadLocalRandom random = ThreadLocalRandom.current();
    rgb[0] += random.nextInt(-max, max + 1);
    rgb[1] += random.nextInt(-max, max + 1);
    rgb[2] += random.nextInt(-max, max + 1);
    for (int i = 0; i < 3; i++) {
      if (rgb[i] < 0) {
        rgb[i] = 0;
      } else if (rgb[i] > 255) {
        rgb[i] = 255;
      }
    }
  }

}
