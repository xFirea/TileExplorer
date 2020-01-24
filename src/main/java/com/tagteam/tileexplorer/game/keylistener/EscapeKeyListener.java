package com.tagteam.tileexplorer.game.keylistener;

import com.gestankbratwurst.le_engine.EngineCore;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class EscapeKeyListener implements KeyListener {

  private final EngineCore engineCore;

  @Override
  public void keyTyped(KeyEvent keyEvent) {

  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    // TODO settings window
    if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {

    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
