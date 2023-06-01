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

import com.illusivesoulworks.elytrautilities.ElytraUtilitiesMod;
import com.illusivesoulworks.spectrelib.config.SpectreConfigInitializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ElytraUtilitiesFabricClientMod implements ClientModInitializer,
    SpectreConfigInitializer {

  @Override
  public void onInitializeClient() {
    KeyRegistry.setup();
    ClientTickEvents.END_WORLD_TICK.register(world -> ClientEvents.clientTick());
    HudRenderCallback.EVENT.register(
        (matrixStack, tickDelta) -> ClientEvents.renderIcon(matrixStack));
  }

  @Override
  public void onInitialize() {
    // NO-OP
  }

  @Override
  public void onInitializeConfig() {
    ElytraUtilitiesMod.setup();
  }
}
