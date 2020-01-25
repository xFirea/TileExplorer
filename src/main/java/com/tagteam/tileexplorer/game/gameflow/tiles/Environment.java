package com.tagteam.tileexplorer.game.gameflow.tiles;

import com.tagteam.tileexplorer.game.gameflow.tiles.Biome;
import com.tagteam.tileexplorer.util.graphics.UtilColor;
import java.awt.Color;
import lombok.Getter;
import lombok.Setter;

public class Environment {

  public Environment(Biome biome) {
    this(biome, 0D);
  }

  public Environment(Biome biome, double tempOffset) {
    this.biome = biome;
    this.tempOffset = tempOffset;
    int[] baseRGB = biome.getRGB();
    UtilColor.randomShadeOffset(baseRGB, 6);
    this.color = new Color(baseRGB[0], baseRGB[1], baseRGB[2]);
  }

  @Getter
  private final Biome biome;
  @Getter
  @Setter
  private double tempOffset;
  private Color color;

  public double getCurrentTemperature() {
    return biome.getBaseTemperature() + tempOffset;
  }

  public Color getColor() {
    return color;
  }

}
