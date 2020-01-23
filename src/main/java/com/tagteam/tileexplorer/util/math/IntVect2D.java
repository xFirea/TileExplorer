package com.tagteam.tileexplorer.util.math;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

@AllArgsConstructor
public class IntVect2D {

  @Getter
  private int x;
  @Getter
  private int y;

  protected void shiftRight(int amount) {
    x += amount;
  }

  protected void shiftDown(int amount) {
    y += amount;
  }

  public boolean isSmallerOrEqualTo(IntVect2D otherVect) {
    return x <= otherVect.x && y <= otherVect.y;
  }

  public boolean isBiggerOrEqualTo(IntVect2D otherVect) {
    return x >= otherVect.x && y >= otherVect.y;
  }

}
