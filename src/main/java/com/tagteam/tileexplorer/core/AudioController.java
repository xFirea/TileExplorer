package com.tagteam.tileexplorer.core;

import com.gestankbratwurst.le_engine.audio.GameAudioController;
import com.google.common.base.Preconditions;
import javax.sound.sampled.Clip;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class AudioController {

  private static GameAudioController GAME_AUDIO;

  protected static void init(GameAudioController audioController) {
    GAME_AUDIO = audioController;
  }

  public static void playOnce(String clipName) {
    Clip clip = GAME_AUDIO.getClip(clipName);
    Preconditions.checkArgument(clip != null);
    clip.setFramePosition(0);
    clip.start();
  }

  public static void stop(String clipName) {
    Clip clip = GAME_AUDIO.getClip(clipName);
    Preconditions.checkArgument(clip != null);
    clip.setFramePosition(0);
    clip.stop();
  }

  public static void playLoop(String clipName, int count) {
    Clip clip = GAME_AUDIO.getClip(clipName);
    Preconditions.checkArgument(clip != null);
    clip.loop(count);
    clip.setFramePosition(0);
    clip.start();
  }

  public static void setVolume(String clipName) {
    Clip clip = GAME_AUDIO.getClip(clipName);
    Preconditions.checkArgument(clip != null);
    clip.setFramePosition(0);
    clip.stop();
  }

}
