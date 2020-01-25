package com.tagteam.tileexplorer.game.events;

import com.google.common.base.Preconditions;
import com.tagteam.tileexplorer.game.events.windowclick.ClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.ComponentClickEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragEndEvent;
import com.tagteam.tileexplorer.game.events.windowclick.MouseDragStartEvent;
import com.tagteam.tileexplorer.game.events.windowclick.WindowClickEvent;
import com.tagteam.tileexplorer.util.reflections.InvokableSingleParamObject;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;

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

  @Getter(AccessLevel.PROTECTED)
  private static GameEventManager instance;

  public GameEventManager() {
    instance = this;
    this.eventMap = new Object2ObjectOpenHashMap<>();
    eventMap.put(ClickEvent.class, new ObjectOpenHashSet<>());
    eventMap.put(WindowClickEvent.class, new ObjectOpenHashSet<>());
    eventMap.put(ComponentClickEvent.class, new ObjectOpenHashSet<>());
    eventMap.put(MouseDragStartEvent.class, new ObjectOpenHashSet<>());
    eventMap.put(MouseDragEndEvent.class, new ObjectOpenHashSet<>());
  }

  private final Map<Class<? extends Event>, Set<InvokableSingleParamObject>> eventMap;

  public void registerListener(Listener listener) {
    for (Method method : listener.getClass().getMethods()) {
      if (method.isAnnotationPresent(EventHandler.class)) {
        Class<?>[] params = method.getParameterTypes();
        Preconditions.checkState(params.length == 1);
        Class<?> paramType = params[0];
        if (Event.class.isAssignableFrom(paramType)) {
          eventMap.get(paramType).add(new InvokableSingleParamObject<>(method, listener));
        }
      }
    }
  }

  public void callEvent(Event event) {
    for (InvokableSingleParamObject callable : eventMap.get(event.getClass())) {
      callable.invoke(event);
    }
  }

}