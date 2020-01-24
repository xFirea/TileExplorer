package com.tagteam.tileexplorer.game.gameflow;

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
  private TileMap tileMap = new TileMap(TILE_AMOUNT);

  public Tile getTile(int posX, int posY) {
    return tileMap.getTile(posX, posY);
  }

  public void setTile(int posX, int posY, Tile tile) {
    tileMap.setTile(posX, posY, tile);
  }

  private void fillTiles() {

    IntVect2D upperLeft = super.getPosition();
    for (int x = 0; x < TILE_AMOUNT; x++) {
      for (int y = 0; y < TILE_AMOUNT; y++) {

        IntVect2D position = new IntVect2D(upperLeft.getX() + (x * tileSize), upperLeft.getY() + (y * tileSize));

        Environment environment = TileGenerator.getGeneratedEnvironment(tileMap, x, y);

        Tile tile = new Tile(x, y, tileSize, position, environment);

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
    this.focus();
    IntVect2D relativePos = this.getRelativePosition(event.getClickedPosition());
    int x = relativePos.getX() / tileSize;
    int y = relativePos.getY() / tileSize;
    Tile tile = this.getTile(x, y);
    System.out.println("Temp: " + tile.getEnvironment().getCurrentTemperature());
    System.out.println("Biom: " + tile.getEnvironment().getBiome());
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