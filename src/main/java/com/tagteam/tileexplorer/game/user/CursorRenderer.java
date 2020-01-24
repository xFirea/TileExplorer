package com.tagteam.tileexplorer.game.user;

import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Graphics;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public interface CursorRenderer {

  void drawCursor(GameCursor cursor, Graphics graphics);

}
