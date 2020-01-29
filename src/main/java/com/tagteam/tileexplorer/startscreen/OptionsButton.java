package com.tagteam.tileexplorer.startscreen;

import com.tagteam.tileexplorer.core.AudioController;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentClickEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.WindowManager;
import java.io.IOException;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 28.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class OptionsButton extends MenuButton {

  public OptionsButton(GameWindow host, int relativeX, int relativeY, int width, int height, WindowManager windowManager)
      throws IOException {
    super(host, relativeX, relativeY, width, height, "images/options_button.png", "images/options_button_hover.png");
    this.windowManager = windowManager;
  }

  private final WindowManager windowManager;

  @Override
  public void onClick(ComponentClickEvent event) {
    AudioController.playOnce("NOPE");
  }

}