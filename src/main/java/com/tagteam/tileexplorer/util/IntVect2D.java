package com.tagteam.tileexplorer.util;

import lombok.Data;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

@Data
public class IntVect2D {

  private final int x;
  private final int y;

  public boolean isSmallerOrEqualTo(IntVect2D otherVect) {
    return x <= otherVect.x && y <= otherVect.y;
  }

  public boolean isBiggerOrEqualTo(IntVect2D otherVect) {
    return x >= otherVect.x && y >= otherVect.y;
  }

}
