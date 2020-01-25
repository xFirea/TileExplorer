package com.tagteam.tileexplorer.core;

import com.gestankbratwurst.le_engine.startmenu.GameResolution;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 25.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class GameOptions {

  @Getter
  protected static int BASE_VISIBLE_RADIUS;
  @Getter
  protected static int MAP_SIZE;
  @Getter
  protected static GameResolution GAME_RESOLUTION;
  @Getter
  protected static double WINDOW_SCALE;

}
