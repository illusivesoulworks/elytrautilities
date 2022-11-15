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

import net.minecraft.client.player.LocalPlayer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.elytrautilities.client.ClientEvents;
import top.theillusivec4.elytrautilities.client.ClientFlightController;
import top.theillusivec4.elytrautilities.platform.ClientServices;

@Mixin(value = LocalPlayer.class, priority = 900)
public class LocalPlayerMixin {

  @ModifyVariable(
      at = @At(
          target = "net/minecraft/client/player/Input.jumping:Z",
          value = "FIELD",
          opcode = Opcodes.GETFIELD,
          ordinal = 2),
      method = "aiStep",
      ordinal = 4)
  private boolean elytrautilities$aiStep(boolean flag7) {

    if (ClientFlightController.isFlightDisabled() || !ClientServices.CLIENT_CONFIG.isJumpTriggerable()) {
      return true;
    } else {
      return flag7;
    }
  }

  @SuppressWarnings("ConstantConditions")
  @Inject(
      at = @At(
          value = "INVOKE",
          target = "net/minecraft/client/tutorial/Tutorial.onInput(Lnet/minecraft/client/player/Input;)V"),
      method = "aiStep")
  private void elytrautilities$input(CallbackInfo ci) {
    LocalPlayer player = (LocalPlayer) (Object) this;
    ClientEvents.triggerFlight(player, player.input);
  }
}
