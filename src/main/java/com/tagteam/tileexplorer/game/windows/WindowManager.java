package com.tagteam.tileexplorer.game.windows;

import com.gestankbratwurst.le_engine.graphics.GTask;
import com.google.common.collect.Lists;
import com.tagteam.tileexplorer.core.TileExplorerCore;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.annotation.Nullable;
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
public class WindowManager implements GTask {

  @Getter
  private static WindowManager instance;

  public WindowManager(TileExplorerCore core) {
    instance = this;
    this.activeWindows = Lists.newArrayList();
    core.registerEvents(new WindowDragListener(this));
  }

  private final ArrayList<GameWindow> activeWindows;

  private boolean isFocused(GameWindow window) {
    if (activeWindows.size() == 0) {
      return false;
    }
    return activeWindows.get(0) == window;
  }

  public void addWindow(GameWindow window) {
    this.activeWindows.add(window);
    window.onOpen();
  }

  public void removeWindow(GameWindow window) {
    window.onClose();
    this.activeWindows.remove(window);
  }

  public void focusWindow(GameWindow window) {
    if (isFocused(window)) {
      return;
    }
    activeWindows.remove(window);
    activeWindows.add(window);
  }

  @Nullable
  public GameWindow getTopWindowAt(IntVect2D position) {
    GameWindow topWindow = null;
    for (int index = activeWindows.size() - 1; index >= 0; index--) {
      GameWindow window = activeWindows.get(index);
      if (window.contains(position)) {
        topWindow = window;
        break;
      }
    }
    return topWindow;
  }

  @Override
  public void accept(Graphics graphics) {
    for (GameWindow window : activeWindows) {
      window.accept(graphics);
    }
  }

}
