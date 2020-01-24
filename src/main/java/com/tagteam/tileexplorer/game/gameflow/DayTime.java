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
public enum DayTime {

  DAY("Tag"),
  NIGHT("Nacht");

  @Getter
  private final String displayName;

}
