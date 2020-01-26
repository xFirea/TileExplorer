package com.tagteam.tileexplorer.game.keylistener;

import com.tagteam.tileexplorer.game.gameflow.GameBoard;
import com.tagteam.tileexplorer.game.gameflow.generators.opensimplex.TripleLayerOpenSimplexNoiseGenerator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import lombok.AllArgsConstructor;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 25.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

@AllArgsConstructor
public class MapDebugKeyListener implements KeyListener {

  @Override
  public void keyTyped(KeyEvent e) {

  }

  private final GameBoard board;

  @Override
  public void keyPressed(KeyEvent e) {
    int keyValue = e.getKeyCode();
    int yAdd = 0;
    int xAdd = 0;
    switch (keyValue) {
      case KeyEvent.VK_LEFT:
        xAdd -= 1;
        break;
      case KeyEvent.VK_1:
        board.setVisibleRadius(board.getVisibleRadius() + 2);
        break;
      case KeyEvent.VK_2:
        board.setVisibleRadius(board.getVisibleRadius() - 2);
        break;
      case KeyEvent.VK_3:
        board.setTileSize(board.getTileSize() + 1);
        break;
      case KeyEvent.VK_4:
        board.setTileSize(board.getTileSize() - 1);
        break;
      case KeyEvent.VK_RIGHT:
        xAdd += 1;
        break;
      case KeyEvent.VK_UP:
        yAdd -= 1;
        break;
      case KeyEvent.VK_DOWN:
        yAdd += 1;
        break;
      case KeyEvent.VK_R:
        board.fillTiles(new TripleLayerOpenSimplexNoiseGenerator(System.nanoTime(), 12.5, 10, 10, 10,0));
        break;
      default:
        break;
    }
    board.addCurrentCoordinates(xAdd, yAdd);
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

}
