package com.tagteam.tileexplorer.game.gameflow.generators;

import com.tagteam.tileexplorer.game.gameflow.tiles.Environment;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public interface TileGenerator {

  Environment getGeneratedEnvironment(TileMap tileMap, int posX, int posY);

}
