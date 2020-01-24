package com.tagteam.tileexplorer.game.gameflow;

import com.tagteam.tileexplorer.game.user.GameUser;
import java.util.function.Predicate;
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

  PLAINS("Wiesen", 25.0D),
  FOREST("Laubwald", 18.0D),
  DENSE_FOREST("Dichter Laubwald", 16.0D),
  DESERT("Wüste", 40.0D),
  LAKE("See", 22.0D),
  OASIS("Oase", 35.0D);

  @Getter
  private final String displayName;
  @Getter
  private final double baseTemperature;

}
