package com.tagteam.tileexplorer.startscreen;

import com.tagteam.tileexplorer.core.TileExplorerCore;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentMouseEnterEvent;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentMouseLeaveEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragEndEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragStartEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.components.WindowComponent;
import com.tagteam.tileexplorer.util.UtilResource;
import com.tagteam.tileexplorer.util.graphics.UtilGraphics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 28.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public abstract class MenuButton extends WindowComponent {

  public MenuButton(GameWindow host, int relativeX, int relativeY, int width, int height, Image buttonImage, Image hoverImage) {
    super(host, relativeX, relativeY, width, height);
    this.buttonImage = buttonImage;
    this.hoverImage = hoverImage;
  }

  public MenuButton(GameWindow host, int relativeX, int relativeY, int width, int height, String imageName, String hoverName)
      throws IOException {
    super(host, relativeX, relativeY, width, height);
    buttonImage = ImageIO.read(UtilResource.getBufferedResource(imageName));
    hoverImage = ImageIO.read(UtilResource.getBufferedResource(hoverName));
  }

  private final Image buttonImage;
  private final Image hoverImage;
  private boolean isHovered = false;

  @Override
  public void handleClick(ComponentClickEvent event) {
    onClick(event);
  }

  @Override
  public void handleDragStart(MouseDragStartEvent event) {

  }

  @Override
  public void handleDragEnd(MouseDragEndEvent event) {

  }

  @Override
  public void handleMouseEnter(ComponentMouseEnterEvent event) {
    isHovered = true;
  }

  @Override
  public void handleMouseLeave(ComponentMouseLeaveEvent event) {
    isHovered = false;
  }


  @Override
  public void accept(Graphics graphics) {
    if (isHovered) {
      UtilGraphics.drawScaledImage(this.hoverImage, this.getGlobalBox(), graphics);
    } else {
      UtilGraphics.drawScaledImage(this.buttonImage, this.getGlobalBox(), graphics);
    }
  }

  public abstract void onClick(ComponentClickEvent event);

}