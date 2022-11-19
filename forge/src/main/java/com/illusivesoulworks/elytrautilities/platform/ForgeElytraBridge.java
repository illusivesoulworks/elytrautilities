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

package com.illusivesoulworks.elytrautilities.platform;

import com.illusivesoulworks.elytrautilities.ElytraUtilitiesForgeMod;
import com.illusivesoulworks.elytrautilities.common.integration.CaelusPlugin;
import com.illusivesoulworks.elytrautilities.platform.services.IElytraBridge;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class ForgeElytraBridge implements IElytraBridge {

  @Override
  public boolean canFly(Player player) {

    if (ElytraUtilitiesForgeMod.isCaelusLoaded) {
      return CaelusPlugin.canFly(player);
    }
    return player.getItemBySlot(EquipmentSlot.CHEST).canElytraFly(player);
  }
}
