package com.tagteam.tileexplorer.game.gameflow.tiles;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

@AllArgsConstructor
public enum Biome {

  SAVANNA("Savanne", 198, 247, 84),
  DESERT("Wüste", 178, 169, 40),
  OASIS("Oase",  40, 178, 150),
  BEACH("Strand",  220, 220, 98),
  PLAINS("Wiesen", 98, 201, 47),
  FOREST("Laubwald",  75, 122, 43),
  SEA("Meer",  42, 64, 206),
  DENSE_FOREST("Dichter Laubwald", 57, 94, 32),
  MOUNTAINS("Berge",  107, 107, 107),
  LAKE("See", 40, 146, 178),
  MOUNTAIN_PEAK("Bergspitze", 209, 209, 209),
  SNOW_PLAINS("Beschneite Felder", 227, 247, 225),
  PINE_FOREST("Kalter Nadelwald", 71, 108, 68),
  DEEP_ICE_FIELDS("Permafrost", 225, 255, 255);


  @Getter
  private final String displayName;
  @Getter
  private final int baseRed;
  @Getter
  private final int baseGreen;
  @Getter
  private final int baseBlue;

  public int[] getRGB() {
    return new int[]{baseRed, baseGreen, baseBlue};
  }

}