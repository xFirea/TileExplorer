package com.tagteam.tileexplorer.game.gameflow;

import com.tagteam.tileexplorer.game.events.windowclick.WindowClickEvent;
import com.tagteam.tileexplorer.game.gameflow.generators.TileGenerator;
import com.tagteam.tileexplorer.game.gameflow.tiles.Environment;
import com.tagteam.tileexplorer.game.gameflow.tiles.Tile;
import com.tagteam.tileexplorer.game.gameflow.world.TileMap;
import com.tagteam.tileexplorer.game.windows.GameWindow;
import com.tagteam.tileexplorer.util.math.IntBoundingBox;
import com.tagteam.tileexplorer.util.math.IntVect2D;
import java.awt.Color;
import java.awt.Graphics;

public class GameBoard extends GameWindow {

  private static final int TILE_AMOUNT = 1000;
  private static final int VISIBLE_RADIUS = 5;

  public GameBoard(int tileSize, int posX, int posY) {
    super(new IntBoundingBox(posX, posY, posX + (tileSize * TILE_AMOUNT), posY + (tileSize * TILE_AMOUNT)), new Color(103, 126, 146));
    this.tileSize = tileSize;
  }

  private int centerX = 200;
  private int centerY = 200;
  private final int tileSize;
  private TileMap tileMap = new TileMap(TILE_AMOUNT);

  public Tile getTile(int posX, int posY) {
    return tileMap.getTile(posX, posY);
  }

  public void setTile(int posX, int posY, Tile tile) {
    tileMap.setTile(posX, posY, tile);
  }

  public void fillTiles(TileGenerator generator) {

    IntVect2D upperLeft = super.getPosition();
    for (int x = 0; x < TILE_AMOUNT; x++) {
      for (int y = 0; y < TILE_AMOUNT; y++) {

        IntVect2D position = new IntVect2D(upperLeft.getX() + (x * tileSize), upperLeft.getY() + (y * tileSize));

        Environment environment = generator.getGeneratedEnvironment(tileMap, x, y);

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
    for (int x = centerX - VISIBLE_RADIUS; x < centerX + VISIBLE_RADIUS; x++) {
      for (int y = centerY - VISIBLE_RADIUS; y < centerY + VISIBLE_RADIUS; y++) {
        if (!(x < 0 || y < 0 || x >= TILE_AMOUNT || y >= TILE_AMOUNT)) {
          getTile(x, y).accept(graphics);
        }
      }
    }
  }

}