package com.tagteam.tileexplorer.util;

import com.gestankbratwurst.le_engine.EngineCore;
import java.text.SimpleDateFormat;
import java.util.Date;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public class GameLogger {

  private static boolean enabled;

  public static void init(EngineCore engineCore) {
    GameLogger.enabled = engineCore.isConsoleEnabled();
  }

  public static void log(Object message) {
    if (enabled) {
      String date = new SimpleDateFormat("HH:mm:ss").format(new Date());
      String output = "[" + date + "] " + message.toString();
      System.out.println(output);
    }
  }

}
