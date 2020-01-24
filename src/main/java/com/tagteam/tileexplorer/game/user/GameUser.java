package com.tagteam.tileexplorer.game.user;

import com.google.common.base.Preconditions;
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
public class GameUser {

  @Getter
  private static GameUser instance;

  public static GameUser get() {
    return instance == null ? instance = new GameUser() : instance;
  }

  private GameUser() {
    Preconditions.checkState(instance == null);
    instance = this;
    this.gameCursor = new GameCursor();
  }

  @Getter
  private final GameCursor gameCursor;

}
