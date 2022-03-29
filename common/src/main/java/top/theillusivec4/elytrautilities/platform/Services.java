/*
 * Copyright (C) 2022 C4
 *
 * This file is part of Elytra Utilities.
 *
 * Elytra Utilities is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Elytra Utilities is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * and the GNU Lesser General Public License along with Elytra Utilities.
 * If not, see <https://www.gnu.org/licenses/>.
 *
 */

package top.theillusivec4.elytrautilities.platform;

import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import top.theillusivec4.elytrautilities.Constants;
import top.theillusivec4.elytrautilities.platform.services.IElytraBridge;
import top.theillusivec4.elytrautilities.platform.services.IPlatform;

public class Services {

  public static final IPlatform PLATFORM = load(IPlatform.class);
  public static final IElytraBridge ELYTRA_BRIDGE = load(IElytraBridge.class);

  public static <T> T load(Class<T> clazz) {
    T loadedService = null;

    try {
      loadedService = ServiceLoader.load(clazz).iterator().next();
    } catch (NoSuchElementException e) {
      Constants.LOG.error("Failed to load service for " + clazz.getName());
    }
    Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
    return loadedService;
  }
}
