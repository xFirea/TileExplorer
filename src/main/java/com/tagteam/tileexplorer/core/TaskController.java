package com.tagteam.tileexplorer.core;

import com.gestankbratwurst.le_engine.logic.GameScheduler;
import com.gestankbratwurst.le_engine.logic.GameTask;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class TaskController {

  private static GameScheduler SCHEDULER;

  protected static void init(GameScheduler gameScheduler) {
    SCHEDULER = gameScheduler;
  }

  public static GameTask runTask(Runnable runnable) {
    return SCHEDULER.runTask(runnable);
  }

  public static GameTask runTaskLater(Runnable runnable, long tickDelay) {
    return SCHEDULER.runTaskLater(runnable, tickDelay);
  }

  public static GameTask runRepeatedTask(Runnable runnable, long tickDelay, long repeatedDelay) {
    return SCHEDULER.scheduleRepeatingTask(runnable, tickDelay, repeatedDelay);
  }

}