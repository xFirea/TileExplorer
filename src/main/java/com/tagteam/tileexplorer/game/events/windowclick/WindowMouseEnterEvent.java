package com.tagteam.tileexplorer.game.events.windowclick;

import com.tagteam.tileexplorer.game.events.Event;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import lombok.Data;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 26.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

@Data
public class WindowMouseEnterEvent extends Event {

  private final GameWindow enteredWindow;

}
