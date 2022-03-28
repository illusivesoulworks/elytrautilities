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

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.elytrautilities.client.ClientFlightController;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

  protected PlayerMixin(EntityType<? extends LivingEntity> $$0, Level $$1) {
    super($$0, $$1);
  }

  @Inject(at = @At("HEAD"), method = "tryToStartFallFlying", cancellable = true)
  private void elytrautilities$tryToStartFallFlying(CallbackInfoReturnable<Boolean> cir) {

    if (this.getLevel().isClientSide() && ClientFlightController.isFlightDisabled()) {
      cir.setReturnValue(false);
    }
  }
}
