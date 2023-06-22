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

package com.illusivesoulworks.elytrautilities.client;

import com.illusivesoulworks.elytrautilities.ElytraUtilitiesConstants;
import com.illusivesoulworks.elytrautilities.config.ElytraUtilitiesConfig;
import com.illusivesoulworks.elytrautilities.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ClientEvents {

  private static final int TRIGGER_FIREWORK_TICKS = 10;
  private static final ResourceLocation DISABLED_ICON = new ResourceLocation(
      ElytraUtilitiesConstants.MOD_ID, "textures/gui/flight_disabled.png");

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

    if (player.onGround() && triggerJump) {
      input.jumping = true;
      triggerJump = false;
      triggerFlight = true;
    }
  }

  public static boolean restrictFirework(Player player, Item item) {
    return item instanceof FireworkRocketItem &&
        ElytraUtilitiesConfig.CLIENT.restrictFireworks.get() && !player.isFallFlying();
  }

  public static void clientTick() {
    final boolean isFocused = Minecraft.getInstance().isWindowActive();
    final boolean isToggleKeyDown = KeyRegistry.getToggle().isDown() && isFocused;
    final boolean isTriggerKeyDown = KeyRegistry.getTrigger().isDown() && isFocused;
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

          if (!player.onGround()) {
            startFlight(player);
          } else {
            triggerJump = true;
          }
        }

        if (triggerFlight && !player.onGround() && !player.isFallFlying()) {
          startFlight(player);
        }
      }

      if (cooldown > 0) {
        cooldown--;
      }

      if (ElytraUtilitiesConfig.CLIENT.simpleTakeoff.get() && triggerFlightUse > 0) {

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
              player.connection.send(new ServerboundUseItemPacket(hand, 0));
            }
          }
          triggerFlightUse = 0;
        }
      } else {
        triggerFlightUse = 0;
      }
    }
  }

  public static void renderIcon(GuiGraphics guiGraphics) {

    if (ElytraUtilitiesConfig.CLIENT_SPEC.isLoaded() &&
        ElytraUtilitiesConfig.CLIENT.toggleIcon.get() &&
        ClientFlightController.isFlightDisabled()) {
      guiGraphics.blit(DISABLED_ICON, 2, 2, 0, 0, 24, 24, 24, 24);
    }
  }
}
