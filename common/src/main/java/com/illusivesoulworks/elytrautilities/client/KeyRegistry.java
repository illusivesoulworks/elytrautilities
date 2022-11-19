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
import com.illusivesoulworks.elytrautilities.platform.ClientServices;
import com.illusivesoulworks.elytrautilities.platform.services.IKeyRegistry;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class KeyRegistry {

  private static final String TOGGLE_DESC =
      "key." + ElytraUtilitiesConstants.MOD_ID + ".toggle.desc";
  private static final String TRIGGER_DESC =
      "key." + ElytraUtilitiesConstants.MOD_ID + ".trigger.desc";
  private static final String CONFIG_CATEGORY =
      "key." + ElytraUtilitiesConstants.MOD_ID + ".category";

  private static KeyMapping toggleFlight;
  private static KeyMapping triggerFlight;

  public static void setup() {
    IKeyRegistry clientPlatform = ClientServices.KEY_REGISTRY;
    toggleFlight =
        clientPlatform.createKeyMapping(InputConstants.UNKNOWN, TOGGLE_DESC, CONFIG_CATEGORY);
    triggerFlight =
        clientPlatform.createKeyMapping(InputConstants.UNKNOWN, TRIGGER_DESC, CONFIG_CATEGORY);
  }

  public static KeyMapping getToggle() {
    return toggleFlight;
  }

  public static KeyMapping getTrigger() {
    return triggerFlight;
  }
}
