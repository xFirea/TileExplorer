package com.tagteam.tileexplorer.game.events;

import com.google.common.base.Preconditions;
import com.tagteam.tileexplorer.game.events.windowclick.ClickEvent;
import com.tagteam.tileexplorer.util.CallableMethod;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 23.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

public class GameEventManager {

  public GameEventManager() {
    this.eventMap = new Object2ObjectOpenHashMap<>();
    eventMap.put(ClickEvent.class, new ObjectOpenHashSet<>());
  }

  private final Map<Class<? extends Event>, Set<CallableMethod>> eventMap;

  public void registerEvents(Listener listener) {
    for (Method method : listener.getClass().getMethods()) {
      if (method.isAnnotationPresent(EventHandler.class)) {
        Class<?>[] params = method.getParameterTypes();
        Preconditions.checkState(params.length == 1);
        Class<?> paramType = params[0];
        if (Event.class.isAssignableFrom(paramType)) {
          eventMap.get(paramType).add(new CallableMethod(method, listener));
        }
      }
    }
  }

  public void callWindowClick(ClickEvent event) {
    for (CallableMethod callable : eventMap.get(event.getClass())) {
      callable.invoke(event);
    }
  }

}