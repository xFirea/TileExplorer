package com.tagteam.tileexplorer.util.collections;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class RandomWeightCollection<E> {

  private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
  private final Random random;
  private double total = 0;

  public RandomWeightCollection() {
    this(ThreadLocalRandom.current());
  }

  public Collection<E> values() {
    return this.map.values();
  }

  public void remove(E object) {
    for (Entry<Double, E> entry : this.map.entrySet()) {
      if (entry.getValue().equals(object)) {
        this.map.remove(entry.getKey(), entry.getValue());
      }
    }
  }

  public RandomWeightCollection(Random random) {
    this.random = random;
  }

  public RandomWeightCollection<E> add(double weight, E result) {
    if (weight <= 0) {
      return this;
    }
    total += weight;
    map.put(total, result);
    return this;
  }

  public E next() {
    double value = random.nextDouble() * total;
    Entry<Double, E> entry = map.higherEntry(value);
    return entry == null ? null : entry.getValue();
  }

}
