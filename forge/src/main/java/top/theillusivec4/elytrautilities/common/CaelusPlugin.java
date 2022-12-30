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

package top.theillusivec4.elytrautilities.common;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import top.theillusivec4.caelus.api.CaelusApi;

public class CaelusPlugin {

  public static boolean isCaelusLoaded = false;

  public static boolean canFly(final LivingEntity livingEntity) {
    if (isCaelusLoaded) {
      return CaelusApi.canElytraFly(livingEntity);
    }
    return livingEntity.getItemBySlot(EquipmentSlotType.CHEST).canElytraFly(livingEntity);
  }
}
