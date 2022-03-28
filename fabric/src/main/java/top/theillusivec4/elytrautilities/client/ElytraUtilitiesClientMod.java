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

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import top.theillusivec4.elytrautilities.common.config.AutoConfigPlugin;

public class ElytraUtilitiesClientMod implements ClientModInitializer {

  public static boolean isConfigLoaded = false;

  @Override
  public void onInitializeClient() {
    KeyRegistry.setup();
    ClientTickEvents.END_WORLD_TICK.register(world -> ClientEvents.clientTick());
    HudRenderCallback.EVENT.register(
        (matrixStack, tickDelta) -> ClientEvents.renderIcon(matrixStack));
    // Config
    isConfigLoaded = FabricLoader.getInstance().isModLoaded("cloth-config2");

    if (isConfigLoaded) {
      AutoConfigPlugin.init();
    }
    ServerLifecycleEvents.SERVER_STARTED.register(server -> {

      if (isConfigLoaded) {
        AutoConfigPlugin.bake();
      }
    });
    ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {

      if (isConfigLoaded) {
        AutoConfigPlugin.bake();
      }
    });
  }
}
