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

package top.theillusivec4.elytrautilities.mixin;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import top.theillusivec4.elytrautilities.client.ClientFlightController;
import top.theillusivec4.elytrautilities.common.ClientConfig;

@Mixin(value = ClientPlayerEntity.class, priority = 900)
public class ClientPlayerMixin {

  @ModifyVariable(
      at = @At(
          target = "net/minecraft/util/MovementInput.jumping:Z",
          value = "FIELD",
          opcode = Opcodes.GETFIELD,
          ordinal = 2),
      method = "aiStep",
      ordinal = 5)
  private boolean elytrautilities$aiStep(boolean flag7) {

    if (ClientFlightController.isFlightDisabled() || !ClientConfig.isJumpTriggerable()) {
      return true;
    } else {
      return flag7;
    }
  }
}
