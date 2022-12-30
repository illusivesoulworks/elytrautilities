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

package top.theillusivec4.elytrautilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

  public static final String MOD_ID = "elytrautilities";
  public static final String MOD_NAME = "Elytra Utilities";
  public static final Logger LOG = LogManager.getLogger(MOD_NAME);

  public static class Config {

    public static final String RENDER_ICON_DESC =
        "Set to true to enable an icon that appears on the HUD when elytra flight is disabled";
    public static final String SIMPLE_TAKEOFF_DESC =
        "Set to true to enable holding down the Trigger Elytra keybind to automatically use a firework from inventory to takeoff from ground level";
    public static final String ENABLE_ELYTRA_DESC =
            "Set to true to enable elytra flight as normal, false to disable elytra flight. This can also be managed in-game through the \"Toggle Elytra\" keybinding.";
    public static final String ENABLE_JUMP_TRIGGER =
            "Set to true to enable jump triggering the elytra while falling as normal, false to disable.";
  }
}