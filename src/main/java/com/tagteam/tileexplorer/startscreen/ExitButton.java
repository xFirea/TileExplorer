package com.tagteam.tileexplorer.startscreen;

import com.tagteam.tileexplorer.core.AudioController;
import com.tagteam.tileexplorer.core.TileExplorerCore;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentClickEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
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
public class ExitButton extends MenuButton {

  public ExitButton(GameWindow host, int relativeX, int relativeY, int width, int height, TileExplorerCore core)
      throws IOException {
    super(host, relativeX, relativeY, width, height, "images/quit_button.png", "images/quit_button_hover.png");
    this.core = core;
  }

  private final TileExplorerCore core;

  @Override
  public void onClick(ComponentClickEvent event) {
    AudioController.playOnce("UI_CLICK_2");
    // TODO trigger save?
    core.closeApplication(false);
  }

}