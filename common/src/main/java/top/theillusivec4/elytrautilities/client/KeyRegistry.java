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

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import top.theillusivec4.elytrautilities.Constants;
import top.theillusivec4.elytrautilities.platform.ClientServices;
import top.theillusivec4.elytrautilities.platform.services.IKeyRegistry;

public class KeyRegistry {

  private static final String TOGGLE_DESC = "key." + Constants.MOD_ID + ".toggle.desc";
  private static final String TRIGGER_DESC = "key." + Constants.MOD_ID + ".trigger.desc";
  private static final String CONFIG_CATEGORY = "key." + Constants.MOD_ID + ".category";

  private static KeyMapping toggleFlight;
  private static KeyMapping triggerFlight;

  public static void setup() {
    IKeyRegistry clientPlatform = ClientServices.KEY_REGISTRY;
    toggleFlight =
        clientPlatform.createKeyMapping(InputConstants.UNKNOWN, TOGGLE_DESC, CONFIG_CATEGORY);
    triggerFlight =
        clientPlatform.createKeyMapping(InputConstants.UNKNOWN, TRIGGER_DESC, CONFIG_CATEGORY);
  }

  public static boolean isToggleDown() {
    return toggleFlight.isDown();
  }

  public static boolean isTriggerDown() {
    return triggerFlight.isDown();
  }
}
