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
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

public class ElytraUtilitiesQuiltClientMod implements ClientModInitializer,
    SpectreConfigInitializer {

  @Override
  public void onInitializeClient(ModContainer modContainer) {
    KeyRegistry.setup();
    ClientTickEvents.END.register(world -> ClientEvents.clientTick());
    HudRenderCallback.EVENT.register(
        (matrixStack, tickDelta) -> ClientEvents.renderIcon(matrixStack));
  }

  @Override
  public void onInitializeConfig(ModContainer modContainer) {
    ElytraUtilitiesMod.setup();
  }
}
