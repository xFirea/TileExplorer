package com.tagteam.tileexplorer.util.swingutil;

import com.tagteam.tileexplorer.game.events.windowclick.ComponentMouseEnterEvent;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentMouseLeaveEvent;
import com.tagteam.tileexplorer.game.events.windowclick.WindowMouseEnterEvent;
import com.tagteam.tileexplorer.game.user.GameCursor;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.game.windows.WindowManager;
import com.tagteam.tileexplorer.game.windows.components.WindowComponent;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
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
public class MouseMoveEventAdapter implements MouseMotionListener {

  private final WindowManager windowManager;
  private final GameCursor gameCursor;

  private void handleMouseMovement(MouseEvent event) {
    gameCursor.updatePosition(event);
    IntVect2D cursorPos = gameCursor.getCurrentPosition();
    GameWindow window = windowManager.getTopWindowAt(cursorPos);
    if (gameCursor.getCurrentlyHoveringWindow() != window) {
      WindowMouseEnterEvent windowMouseEnterEvent = new WindowMouseEnterEvent(window);
      windowMouseEnterEvent.callEvent();
      if (window != null) {
        window.handleEnter(windowMouseEnterEvent);
      }
      gameCursor.setCurrentlyHoveringWindow(window);
    }
    if (window != null) {
      WindowComponent component = window.getComponentAt(cursorPos);
      WindowComponent currentComponent = gameCursor.getCurrentlyHoveringComponent();
      if (currentComponent != component) {

        ComponentMouseLeaveEvent componentMouseLeaveEvent = new ComponentMouseLeaveEvent(window, currentComponent);
        componentMouseLeaveEvent.callEvent();
        if (currentComponent != null) {
          currentComponent.handleMouseLeave(componentMouseLeaveEvent);
        }

        ComponentMouseEnterEvent componentMouseEnterEvent = new ComponentMouseEnterEvent(window, component);
        componentMouseEnterEvent.callEvent();
        if (component != null) {
          component.handleMouseEnter(componentMouseEnterEvent);
        }

        gameCursor.setCurrentlyHoveringComponent(component);
      }
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    handleMouseMovement(e);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    handleMouseMovement(e);
  }

}
