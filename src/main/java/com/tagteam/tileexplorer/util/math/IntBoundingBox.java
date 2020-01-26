package com.tagteam.tileexplorer.util.math;

import com.google.common.base.Preconditions;
import java.util.Objects;
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

  public IntBoundingBox(int xl, int yl, int xh, int yh) {
    this(new IntVect2D(xl, yl), new IntVect2D(xh, yh), true);
  }

  public IntBoundingBox(int xl, int yl, int xh, int yh, boolean fixed) {
    this(new IntVect2D(xl, yl), new IntVect2D(xh, yh), fixed);
  }

  public IntBoundingBox(IntVect2D leftCorner, IntVect2D rightCorner) {
    this(leftCorner, rightCorner, true);
  }

  public IntBoundingBox(IntVect2D leftCorner, IntVect2D rightCorner, boolean fixed) {
    Preconditions.checkArgument(leftCorner.isSmallerOrEqualTo(rightCorner));
    this.leftCorner = leftCorner;
    this.rightCorner = rightCorner;
    this.fixed = fixed;
  }

  @Getter
  private final IntVect2D leftCorner;
  @Getter
  private final IntVect2D rightCorner;
  @Getter
  private final boolean fixed;

  public int getWidth() {
    return rightCorner.getX() - leftCorner.getX();
  }

  public int getHeight() {
    return rightCorner.getY() - leftCorner.getY();
  }

  public IntVect2D getPosition() {
    return this.getLeftCorner();
  }

  public IntVect2D getTopLeftCorner() {
    return getPosition();
  }

  public IntVect2D getTopRightCorner() {
    return new IntVect2D(getPosition().getX() + getWidth(), getPosition().getY());
  }

  public IntVect2D getBottomRightCorner() {
    return new IntVect2D(getPosition().getX() + getWidth(), getPosition().getY() + getHeight());
  }

  public IntVect2D getBottomLeftCorner() {
    return new IntVect2D(getPosition().getX(), getPosition().getY() + getHeight());
  }

  public IntVect2D getCenter() {
    int width = getWidth();
    int height = getHeight();
    return new IntVect2D(leftCorner.getX() + (width / 2), leftCorner.getY() + (height / 2));
  }

  public void shiftRight(int amount) {
    Preconditions.checkState(!fixed);
    leftCorner.shiftRight(amount);
    rightCorner.shiftRight(amount);
  }

  public void shiftDown(int amount) {
    Preconditions.checkState(!fixed);
    leftCorner.shiftDown(amount);
    rightCorner.shiftDown(amount);
  }

  public boolean contains(IntVect2D pointer) {
    return leftCorner.isSmallerOrEqualTo(pointer) && rightCorner.isBiggerOrEqualTo(pointer);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof IntBoundingBox)) {
      return false;
    }
    IntBoundingBox otherBox = (IntBoundingBox) other;
    return this.leftCorner.equals(otherBox.leftCorner) && this.rightCorner.equals(otherBox.rightCorner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(leftCorner.getX(), leftCorner.getY(), rightCorner.getX(), rightCorner.getY());
  }

}