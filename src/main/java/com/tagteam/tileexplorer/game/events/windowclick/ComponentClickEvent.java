package com.tagteam.tileexplorer.game.events.windowclick;

import com.tagteam.tileexplorer.game.windows.components.WindowComponent;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class ComponentClickEvent extends WindowClickEvent {

  public ComponentClickEvent(WindowClickEvent clickEvent, WindowComponent component) {
    super(clickEvent.getClickedPosition(), clickEvent.getMouseClickType(), clickEvent.getClickedWindow());
    this.component = component;
  }

  @Getter
  private final WindowComponent component;

}
