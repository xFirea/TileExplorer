package com.tagteam.tileexplorer.game.windows;

import com.gestankbratwurst.le_engine.graphics.GTask;
import com.google.common.collect.Lists;
import com.tagteam.tileexplorer.util.IntVect2D;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.annotation.Nullable;

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

  public WindowManager() {
    this.activeWindows = Lists.newLinkedList();
  }

  private final LinkedList<GameWindow> activeWindows;

  public void addWindow(GameWindow window) {
    this.activeWindows.add(window);
  }

  public void removeWindow(GameWindow window) {
    this.activeWindows.remove(window);
  }

  public void focusWindow(GameWindow window) {
    activeWindows.remove(window);
    activeWindows.add(window);
  }

  @Nullable
  public GameWindow getTopWindowAt(IntVect2D position) {
    GameWindow topWindow = null;
    for (GameWindow window : activeWindows) {
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
