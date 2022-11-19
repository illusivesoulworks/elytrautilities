/*
 * Copyright (C) 2022 Illusive Soulworks
 *
 * Elytra Utilities is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * Elytra Utilities is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Elytra Utilities. If not, see <https://www.gnu.org/licenses/>.
 */

package com.illusivesoulworks.elytrautilities.config;

import com.illusivesoulworks.elytrautilities.ElytraUtilitiesConstants;
import com.illusivesoulworks.spectrelib.config.SpectreConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ElytraUtilitiesConfig {

  public static final SpectreConfigSpec CLIENT_SPEC;
  public static final Client CLIENT;
  private static final String CONFIG_PREFIX = "gui." + ElytraUtilitiesConstants.MOD_ID + ".config.";

  static {
    final Pair<Client, SpectreConfigSpec> specPair = new SpectreConfigSpec.Builder()
        .configure(Client::new);
    CLIENT_SPEC = specPair.getRight();
    CLIENT = specPair.getLeft();
  }

  public static class Client {

    public final SpectreConfigSpec.BooleanValue toggleIcon;
    public final SpectreConfigSpec.BooleanValue simpleTakeoff;
    public final SpectreConfigSpec.BooleanValue enableElytra;
    public final SpectreConfigSpec.BooleanValue jumpTriggerable;

    Client(SpectreConfigSpec.Builder builder) {
      builder.comment("Client only settings").push("client");

      toggleIcon = builder.comment(
              "Set to true to enable an icon that appears on the HUD when elytra flight is disabled")
          .translation(CONFIG_PREFIX + "toggleIcon").define("toggleIcon", true);
      simpleTakeoff = builder.comment(
              "Set to true to enable holding down the Trigger Elytra keybind to automatically use a firework from inventory to takeoff from ground level")
          .translation(CONFIG_PREFIX + "simpleTakeoff").define("simpleTakeoff", true);
      enableElytra = builder.comment(
              "Set to true to enable elytra flight as normal, false to disable elytra flight. This can also be managed in-game through the \"Toggle Elytra\" keybinding.")
          .translation(CONFIG_PREFIX + "enableElytra").define("enableElytra", true);
      jumpTriggerable = builder.comment(
              "Set to true to enable jump triggering the elytra while falling as normal, false to disable.")
          .translation(CONFIG_PREFIX + "jumpTriggerable").define("jumpTriggerable", true);

      builder.pop();
    }
  }
}
