package com.tagteam.tileexplorer.game.gameflow.tiles;

import com.tagteam.tileexplorer.util.graphics.UtilColor;
import java.awt.Color;
import lombok.Getter;

public class Environment {

  public Environment(Biome biome) {
    this(biome, 0D);
  }

  public Environment(Biome biome, double temp) {
    this.biome = biome;
    this.temp = temp;
    int[] baseRGB = biome.getRGB();
    UtilColor.randomShadeOffset(baseRGB, 5);
    this.color = new Color(baseRGB[0], baseRGB[1], baseRGB[2]);
  }

  @Getter
  private final Biome biome;
  @Getter
  private double temp;
  private Color color;

  public Color getColor() {
    return color;
  }

}
