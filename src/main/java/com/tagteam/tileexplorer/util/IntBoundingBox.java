package com.tagteam.tileexplorer.util;

import com.google.common.base.Preconditions;
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

public class IntBoundingBox {

  public IntBoundingBox(IntVect2D leftCorner, IntVect2D rightCorner) {
    Preconditions.checkArgument(leftCorner.isSmallerOrEqualTo(rightCorner));
    this.leftCorner = leftCorner;
    this.rightCorner = rightCorner;
  }

  @Getter
  private final IntVect2D leftCorner;
  @Getter
  private final IntVect2D rightCorner;

  public boolean contains(IntVect2D pointer) {
    return leftCorner.isSmallerOrEqualTo(pointer) && rightCorner.isBiggerOrEqualTo(pointer);
  }

}