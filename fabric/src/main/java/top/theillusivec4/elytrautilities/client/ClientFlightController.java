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

package top.theillusivec4.elytrautilities.client;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.TranslatableText;
import top.theillusivec4.elytrautilities.Constants;

public class ClientFlightController {

  private static boolean flightEnabled = true;

  public static boolean isFlightDisabled() {
    return !flightEnabled;
  }

  public static void toggleFlight(ClientPlayerEntity player) {
    TranslatableText text;
    flightEnabled = !flightEnabled;

    if (flightEnabled) {
      text = new TranslatableText(Constants.MOD_ID + ".enableFlight");
    } else {
      text = new TranslatableText(Constants.MOD_ID + ".disableFlight");
    }
    player.sendMessage(text, true);
  }
}
