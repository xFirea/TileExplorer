package com.tagteam.tileexplorer.game.gameflow;

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

  PLAINS("Wiesen", 25.0D, 98, 201, 47),
  FOREST("Laubwald", 18.0D, 75, 122, 43),
  DENSE_FOREST("Dichter Laubwald", 16.0D, 57, 94, 32),
  DESERT("Wüste", 40.0D, 178, 169, 40),
  LAKE("See", 12.0D, 40, 146, 178),
  OASIS("Oase", 35.0D, 40, 178, 150);

  @Getter
  private final String displayName;
  @Getter
  private final double baseTemperature;
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