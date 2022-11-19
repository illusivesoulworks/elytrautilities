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

package com.illusivesoulworks.elytrautilities.client;

import com.illusivesoulworks.elytrautilities.ElytraUtilitiesConstants;
import com.illusivesoulworks.elytrautilities.config.ElytraUtilitiesConfig;
import com.illusivesoulworks.elytrautilities.platform.ClientServices;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class ClientFlightController {

  public static boolean isFlightDisabled() {
    return !ElytraUtilitiesConfig.CLIENT.enableElytra.get();
  }

  public static void toggleFlight(Player player) {
    MutableComponent text;
    boolean state = !ElytraUtilitiesConfig.CLIENT.enableElytra.get();
    ElytraUtilitiesConfig.CLIENT.enableElytra.set(state);
    ElytraUtilitiesConfig.CLIENT.enableElytra.save();

    if (state) {
      text = Component.translatable(ElytraUtilitiesConstants.MOD_ID + ".enableFlight");
    } else {
      text = Component.translatable(ElytraUtilitiesConstants.MOD_ID + ".disableFlight");
    }
    player.displayClientMessage(text, true);
  }
}
