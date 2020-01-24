package com.tagteam.tileexplorer.util.graphics;

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

}
