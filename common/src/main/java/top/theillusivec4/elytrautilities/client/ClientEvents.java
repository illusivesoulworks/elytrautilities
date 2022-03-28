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

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import top.theillusivec4.elytrautilities.Constants;
import top.theillusivec4.elytrautilities.platform.ClientServices;
import top.theillusivec4.elytrautilities.platform.Services;

public class ClientEvents {

  private static final int TRIGGER_FIREWORK_TICKS = 10;
  private static final ResourceLocation DISABLED_ICON = new ResourceLocation(Constants.MOD_ID,
      "textures/gui/flight_disabled.png");

  private static boolean triggerJump = false;
  private static int cooldown = 0;
  private static boolean triggerFlight = false;
  private static int triggerFlightUse = 0;

  private static void startFlight(LocalPlayer player) {
    player.connection.send(new ServerboundPlayerCommandPacket(player,
        ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
    triggerJump = false;
    triggerFlight = false;
    triggerFlightUse++;
  }

  public static void triggerFlight(Player player, Input input) {

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
    LocalPlayer player = Minecraft.getInstance().player;

    if (isToggleKeyDown && cooldown <= 0) {
      ClientFlightController.toggleFlight(player);
      cooldown = 20;
    }

    if (player != null) {
      final boolean canFly = !ClientFlightController.isFlightDisabled() &&
          !player.getAbilities().flying &&
              !player.isPassenger() && !player.onClimbable() && !player.isFallFlying() &&
              !player.isInWater() && !player.hasEffect(MobEffects.LEVITATION) &&
              Services.ELYTRA_BRIDGE.canFly(player);

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

      if (ClientServices.CLIENT_CONFIG.canSimpleTakeoff() && triggerFlightUse > 0) {

        if (isTriggerKeyDown) {
          triggerFlightUse++;
        } else {

          if (triggerFlightUse > TRIGGER_FIREWORK_TICKS) {
            InteractionHand hand = null;

            if (player.getMainHandItem().getItem() == Items.FIREWORK_ROCKET) {
              hand = InteractionHand.MAIN_HAND;
            } else if (player.getOffhandItem().getItem() == Items.FIREWORK_ROCKET) {
              hand = InteractionHand.OFF_HAND;
            }

            if (hand != null) {
              player.connection.send(new ServerboundUseItemPacket(hand));
            }
          }
          triggerFlightUse = 0;
        }
      } else {
        triggerFlightUse = 0;
      }
    }
  }

  public static void renderIcon(PoseStack poseStack) {

    if (ClientServices.CLIENT_CONFIG.canRenderIcon() && ClientFlightController.isFlightDisabled()) {
      RenderSystem.setShaderTexture(0, DISABLED_ICON);
      GuiComponent.blit(poseStack, 2, 2, 0, 0, 24, 24, 24, 24);
    }
  }
}
