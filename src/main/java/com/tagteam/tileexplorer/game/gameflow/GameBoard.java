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
import lombok.Getter;
import lombok.Setter;

public class GameBoard extends GameWindow {

  public GameBoard(int tileSize, int posX, int posY, TileGenerator generator, int boardSize, int visibleRadius) {
    super(new IntBoundingBox(posX, posY, posX + (tileSize * ((visibleRadius * 2) + 1)), posY + (tileSize * ((visibleRadius * 2) + 1))),
        new Color(103, 126, 146));
    this.boardSize = boardSize;
    this.visibleRadius = visibleRadius;
    this.tileMap = new TileMap(boardSize);
    this.tileSize = tileSize;
    this.fillTiles(generator);
  }

  @Getter
  @Setter
  private int currentX = 0;
  @Getter
  @Setter
  private int currentY = 0;
  private final int boardSize;
  @Getter
  private int visibleRadius;
  @Getter
  private int tileSize;
  private final TileMap tileMap;

  public void setTileSize(int size) {
    tileSize = size;
    for (int x = 0; x < boardSize; x++) {
      for (int y = 0; y < boardSize; y++) {
        getTile(x, y).setTileSize(size);
      }
    }
    updateBoardView();
  }

  public void setVisibleRadius(int radius) {
    visibleRadius = radius;
    updateBoardView();
  }

  private void updateBoardView() {
    int posX = super.getPosition().getX();
    int posY = super.getPosition().getY();
    super.boundingBox = new IntBoundingBox(posX, posY, posX + (tileSize * ((visibleRadius * 2) + 1)),
        posY + (tileSize * ((visibleRadius * 2) + 1)));
  }

  public void addCurrentCoordinates(int x, int y) {
    currentX += x;
    currentY += y;
  }

  public Tile getTile(int posX, int posY) {
    return tileMap.getTile(posX, posY);
  }

  public void setTile(int posX, int posY, Tile tile) {
    tileMap.setTile(posX, posY, tile);
  }

  public void fillTiles(TileGenerator generator) {
    for (int x = 0; x < boardSize; x++) {
      for (int y = 0; y < boardSize; y++) {
        Environment environment = generator.getGeneratedEnvironment(tileMap, x, y);
        Tile tile = new Tile(x, y, tileSize, environment);
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
    int x = (int) ((double) relativePos.getX() / (double) tileSize);
    int y = (int) ((double) relativePos.getY() / (double) tileSize);
    Tile tile = this.getTile(x, y);
    System.out.println("Temp: " + tile.getEnvironment().getTemp());
    System.out.println("Biom: " + tile.getEnvironment().getBiome());
  }

  @Override
  public boolean canBeDragged() {
    return true;
  }

  @Override
  protected void onMove(int newX, int newY) {

  }

  @Override
  protected void render(Graphics graphics) {
    for (int x = -visibleRadius; x < visibleRadius + 1; x++) {
      for (int y = -visibleRadius; y < visibleRadius + 1; y++) {

        int boardX = currentX + x;
        int boardY = currentY + y;

        if (!(boardX < 0 || boardY < 0 || boardX >= boardSize || boardY >= boardSize)) {
          IntVect2D windowPos = this.getPosition();
          int windowRelX = windowPos.getX() + ((x + visibleRadius) * tileSize);
          int windowRelY = windowPos.getY() + ((y + visibleRadius) * tileSize);
          IntVect2D screenPos = new IntVect2D(windowRelX, windowRelY);
          getTile(boardX, boardY).render(screenPos, graphics);
        }

      }
    }
  }

}