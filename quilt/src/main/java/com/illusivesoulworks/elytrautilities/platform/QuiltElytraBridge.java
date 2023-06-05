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

import com.illusivesoulworks.elytrautilities.platform.services.IElytraBridge;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;

public class QuiltElytraBridge implements IElytraBridge {

  @Override
  public boolean canFly(Player player) {
    ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
    boolean flag = stack.getItem() instanceof ElytraItem && ElytraItem.isFlyEnabled(stack);
    return EntityElytraEvents.ALLOW.invoker().allowElytraFlight(player) &&
        (flag || EntityElytraEvents.CUSTOM.invoker().useCustomElytra(player, false));
  }
}
