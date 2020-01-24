package com.tagteam.tileexplorer.util;

import com.tagteam.tileexplorer.core.TileExplorerCore;
import java.io.BufferedInputStream;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of TileExplorer and was created at the 24.01.2020
 *
 * TileExplorer can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class UtilResource {

  public static BufferedInputStream getBufferedResource(String fileName) {
    return IOUtils.buffer(Objects.requireNonNull(TileExplorerCore.class.getClassLoader().getResourceAsStream(fileName)));
  }

}
