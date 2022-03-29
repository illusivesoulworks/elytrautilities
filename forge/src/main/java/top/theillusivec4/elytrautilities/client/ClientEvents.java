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

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ResourceLocation;
import top.theillusivec4.elytrautilities.Constants;
import top.theillusivec4.elytrautilities.ElytraUtilitiesMod;
import top.theillusivec4.elytrautilities.common.ConfigReader;

public class ClientEvents {

  private static final int TRIGGER_FIREWORK_TICKS = 10;
  private static final ResourceLocation DISABLED_ICON =
      new ResourceLocation(Constants.MOD_ID, "textures/gui/flight_disabled.png");

  private static boolean triggerJump = false;
  private static int cooldown = 0;
  private static boolean triggerFlight = false;
  private static int triggerFlightUse = 0;

  private static void startFlight(ClientPlayerEntity player) {
    player.connection.send(
        new CEntityActionPacket(player, CEntityActionPacket.Action.START_FALL_FLYING));
    triggerJump = false;
    triggerFlight = false;
    triggerFlightUse++;
  }

  public static void triggerFlight(PlayerEntity player, MovementInput input) {

    if (player.isOnGround() && triggerJump) {
      input.jumping = true;
      triggerJump = false;
      triggerFlight = true;
    }
  }

  public static void clientTick() {
    final boolean isFocused = Minecraft.getInstance().isWindowActive();
    final boolean isToggleKeyDown = KeyRegistry.isToggleDown() && isFocused;
    final boolean isTriggerKeyDown = KeyRegistry.isTriggerDown() && isFocused;
    ClientPlayerEntity player = Minecraft.getInstance().player;

    if (isToggleKeyDown && cooldown <= 0) {
      ClientFlightController.toggleFlight(player);
      cooldown = 20;
    }

    if (player != null) {
      final boolean canFly = !ClientFlightController.isFlightDisabled() &&
          !player.abilities.flying && !player.isPassenger() && !player.onClimbable() &&
          !player.isFallFlying() && !player.isInWater() &&
          !player.hasEffect(Effects.LEVITATION) && ElytraUtilitiesMod.canFly(player);

      if (canFly) {

        if (isTriggerKeyDown && !triggerJump) {

          if (!player.isOnGround()) {
            startFlight(player);
          } else {
            triggerJump = true;
          }
        }

        if (triggerFlight && !player.isOnGround() && !player.isFallFlying()) {
          startFlight(player);
        }
      }

      if (cooldown > 0) {
        cooldown--;
      }

      if (ConfigReader.CLIENT.simpleTakeoff.get() && triggerFlightUse > 0) {

        if (isTriggerKeyDown) {
          triggerFlightUse++;
        } else {

          if (triggerFlightUse > TRIGGER_FIREWORK_TICKS) {
            Hand hand = null;

            if (player.getMainHandItem().getItem() == Items.FIREWORK_ROCKET) {
              hand = Hand.MAIN_HAND;
            } else if (player.getOffhandItem().getItem() == Items.FIREWORK_ROCKET) {
              hand = Hand.OFF_HAND;
            }

            if (hand != null) {
              player.connection.send(new CPlayerTryUseItemPacket(hand));
            }
          }
          triggerFlightUse = 0;
        }
      } else {
        triggerFlightUse = 0;
      }
    }
  }

  public static void renderIcon(MatrixStack poseStack) {

    if (ConfigReader.CLIENT.toggleIcon.get() && ClientFlightController.isFlightDisabled()) {
      Minecraft.getInstance().getTextureManager().bind(DISABLED_ICON);
      AbstractGui.blit(poseStack, 2, 2, 0, 0, 24, 24, 24, 24);
    }
  }
}
