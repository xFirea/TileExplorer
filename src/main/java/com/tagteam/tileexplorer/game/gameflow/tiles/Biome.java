package com.tagteam.tileexplorer.game.gameflow.tiles;

import com.google.common.collect.Maps;
import com.tagteam.tileexplorer.core.TileExplorerCore;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import lombok.Getter;
import org.apache.commons.io.IOUtils;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public enum Biome {

  JUNGLE("Dschungel", 198, 247, 84),
  SAVANNAH("Savanne", 198, 247, 84),
  DESERT("Wüste", 178, 169, 40),
  BEACH("Strand", 220, 220, 98),
  PLAINS("Wiesen", 98, 201, 47),
  FOREST("Laubwald", 75, 122, 43),
  SEA("Meer", 42, 64, 206),
  DENSE_FOREST("Dichter Laubwald", 57, 94, 32),
  MOUNTAINS("Berge", 107, 107, 107),
  MOUNTAIN_FOREST("Bergwald", 71, 108, 68),
  LAKE("See", 40, 146, 178),
  MOUNTAIN_PEAK("Bergspitze", 209, 209, 209),
  SNOW_PLAINS("Beschneite Felder", 227, 247, 225),
  PINE_FOREST("Kalter Nadelwald", 117, 161, 113),
  DENSE_PINE_FOREST("Dichter beschneiter Nadelwald", 105, 172, 95),
  DEEP_ICE_FIELDS("Permafrost", 225, 255, 255);


  Biome(String displayName, int baseRed, int baseGreen, int baseBlue) {
    this.displayName = displayName;
    this.baseRed = baseRed;
    this.baseGreen = baseGreen;
    this.baseBlue = baseBlue;
    imageCache = Maps.newHashMap();
  }

  @Getter
  private final String displayName;
  @Getter
  private final int baseRed;
  @Getter
  private final int baseGreen;
  @Getter
  private final int baseBlue;

  private final HashMap<Biome, BufferedImage> imageCache;

  public BufferedImage getBufferedImage() {
    BufferedImage image = imageCache.get(this);
    if (image == null) {
      //InputStream inputStream = TileExplorerCore.class.getClassLoader().getResourceAsStream(this.toString() + "_IMAGE.png");
      InputStream inputStream = TileExplorerCore.class.getClassLoader().getResourceAsStream(this.toString() + "_IMAGE.png");
      BufferedInputStream bufferedInputStream;
      if (inputStream != null) {
        bufferedInputStream = IOUtils.buffer(inputStream);
      } else {
        System.out.println("Did not find: " + this.toString());
        inputStream = TileExplorerCore.class.getClassLoader().getResourceAsStream("PLACEHOLDER.png");
        assert inputStream != null;
        bufferedInputStream = IOUtils.buffer(inputStream);
      }

      try {
        BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);
        imageCache.put(this, bufferedImage);
        image = bufferedImage;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return image;
  }

  public int[] getRGB() {
    return new int[]{baseRed, baseGreen, baseBlue};
  }

}