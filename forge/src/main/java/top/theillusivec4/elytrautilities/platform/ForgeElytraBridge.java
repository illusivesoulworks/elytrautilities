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

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import top.theillusivec4.elytrautilities.platform.services.IElytraBridge;

public class ForgeElytraBridge implements IElytraBridge {

  @Override
  public boolean canFly(Player player) {
    return player.getItemBySlot(EquipmentSlot.CHEST).canElytraFly(player);
  }
}
