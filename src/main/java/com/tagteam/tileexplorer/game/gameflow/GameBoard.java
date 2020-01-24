package com.tagteam.tileexplorer.game.gameflow;

import com.tagteam.tileexplorer.TestTile;
import com.tagteam.tileexplorer.game.events.windowclick.WindowClickEvent;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import java.awt.Graphics;

public class GameBoard extends GameWindow {

  private static final int TILE_AMOUNT = 11;

  public GameBoard(int tileSize, int posX, int posY) {
    super(new IntBoundingBox(posX, posY, posX + (tileSize * TILE_AMOUNT), posY + (tileSize * TILE_AMOUNT)), new Color(103, 126, 146));
    this.tileSize = tileSize;
    fillTiles();
  }

  private final int tileSize;
  private Tile[][] tileField = new Tile[TILE_AMOUNT][TILE_AMOUNT];

  public Tile getTile(int posX, int posY) {
    return tileField[posX][posY];
  }

  public void setTile(int posX, int posY, Tile tile) {
    tileField[posX][posY] = tile;
  }


  private void fillTiles() {

    IntVect2D upperLeft = super.getPosition();
    for (int x = 0; x < TILE_AMOUNT; x++) {
      for (int y = 0; y < TILE_AMOUNT; y++) {

        IntVect2D position = new IntVect2D(upperLeft.getX() + (x * tileSize), upperLeft.getY() + (y * tileSize));

        Tile tile = new TestTile(x, y, tileSize, position, new Environment(Biome.DESERT));

        setTile(x, y, tile);
      }
    }
  }

  @Override
  public void onOpen() {

  }

  @Override
  public void onClose() {

  }

  @Override
  public void onClick(WindowClickEvent event) {

  }

  @Override
  public boolean canBeDragged() {
    return false;
  }

  @Override
  protected void onMove(int newX, int newY) {

  }

  @Override
  protected void render(Graphics graphics) {
    for (int x = 0; x < TILE_AMOUNT; x++) {
      for (int y = 0; y < TILE_AMOUNT; y++) {
        getTile(x, y).accept(graphics);
      }
    }
  }

}