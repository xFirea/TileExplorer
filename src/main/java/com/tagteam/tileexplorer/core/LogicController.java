package com.tagteam.tileexplorer.core;

import com.gestankbratwurst.le_engine.logic.GameLogicController;
import com.gestankbratwurst.le_engine.logic.LogicElement;
import com.gestankbratwurst.le_engine.logic.LogicPriority;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class LogicController {

  private static GameLogicController LOGIC;

  protected static void init(GameLogicController logicController) {
    LOGIC = logicController;
  }

  public void addLogic(LogicPriority logicPriority, LogicElement logicElement) {
    LOGIC.addLogicElement(logicPriority, logicElement);
  }

  public void addLogic(LogicElement logicElement) {
    LOGIC.addLogicElement(LogicPriority.LOW, logicElement);
  }

}
