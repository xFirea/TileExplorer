package com.tagteam.tileexplorer.game.events.game;

import com.tagteam.tileexplorer.game.events.Event;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 27.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

@AllArgsConstructor
public class TileGenerateEvent extends Event {

  @Getter
  private final Tile generatedTile;
  @Getter
  private final TileMap tileMap;

}
