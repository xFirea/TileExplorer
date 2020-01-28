package com.tagteam.tileexplorer.game.windows;

import com.tagteam.tileexplorer.core.AudioController;
import com.tagteam.tileexplorer.game.events.EventHandler;
import com.tagteam.tileexplorer.game.events.Listener;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragEndEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragStartEvent;
import com.tagteam.tileexplorer.game.user.CursorRenderer;
import com.tagteam.tileexplorer.game.user.GameCursor;
import com.tagteam.tileexplorer.game.user.GameUser;
import com.tagteam.tileexplorer.game.windows.components.WindowComponent;
import com.tagteam.tileexplorer.graphics.mouserenderer.WindowFrameMouseRenderer;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import lombok.AllArgsConstructor;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
@AllArgsConstructor
public class WindowDragListener implements Listener {

  private final WindowManager windowManager;

  @EventHandler
  public void onWindowStartDrag(MouseDragStartEvent event) {
    GameWindow window = windowManager.getTopWindowAt(event.getStartPosition());
    GameCursor cursor = GameUser.get().getGameCursor();

    if (window == null) {
      return;
    }
    WindowComponent draggedComponent = window.getComponentAt(event.getStartPosition());
    if (draggedComponent == null) {
      if (window.canBeDragged()) {
        cursor.setCurrentHeldWindow(window);

        int relX = event.getStartPosition().getX() - window.getPosition().getX();
        int relY = event.getStartPosition().getY() - window.getPosition().getY();
        int width = window.getBoundingBox().getWidth();
        int height = window.getBoundingBox().getHeight();
        CursorRenderer renderer = new WindowFrameMouseRenderer(Color.ORANGE, 1, relX, relY, width, height);
        cursor.setCursorDrawer(renderer);
      }
    } else {
      GameUser.get().getGameCursor().setCurrentHeldComponent(draggedComponent);
      draggedComponent.handleDragStart(event);
    }
  }

  @EventHandler
  public void onWindowEndDrag(MouseDragEndEvent event) {
    GameCursor cursor = GameUser.get().getGameCursor();
    GameWindow window = cursor.getCurrentHeldWindow();
    WindowComponent component = cursor.getCurrentHeldComponent();

    if (window != null) {
      if (!window.canBeDragged()) {
        return;
      }
      IntVect2D from = event.getStartPosition();
      IntVect2D to = event.getEndPosition();
      int dx = from.getX() - to.getX();
      int dy = from.getY() - to.getY();
      IntVect2D windowPos = window.getPosition();
      window.moveTo(windowPos.getX() - dx, windowPos.getY() - dy);
      cursor.setCurrentHeldWindow(null);
      cursor.setCursorDrawer(null);
      AudioController.playOnce("DRAG");
    }

    if (component != null) {
      component.handleDragEnd(event);
      GameUser.get().getGameCursor().setCurrentHeldComponent(null);
    }

  }

}
