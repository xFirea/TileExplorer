package com.tagteam.tileexplorer.game.events.windowclick;

import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.components.WindowComponent;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 26.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class ComponentMouseEnterEvent extends WindowMouseEnterEvent {

  public ComponentMouseEnterEvent(GameWindow enteredWindow, WindowComponent component) {
    super(enteredWindow);
    this.component = component;
  }

  @Getter
  private final WindowComponent component;

}
