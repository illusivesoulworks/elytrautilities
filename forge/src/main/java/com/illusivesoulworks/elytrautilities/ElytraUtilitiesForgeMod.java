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

package com.illusivesoulworks.elytrautilities;

import com.illusivesoulworks.elytrautilities.client.ClientEventsListener;
import com.illusivesoulworks.elytrautilities.client.KeyRegistry;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;

@Mod(ElytraUtilitiesConstants.MOD_ID)
public class ElytraUtilitiesForgeMod {

  public static boolean isCaelusLoaded = false;

  public ElytraUtilitiesForgeMod() {
    ElytraUtilitiesMod.setup();
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    isCaelusLoaded = ModList.get().isLoaded("caelus");
    modEventBus.addListener(this::clientSetup);
    modEventBus.addListener(this::registerKeys);
    ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
        () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY,
            (a, b) -> true));
  }

  private void clientSetup(final FMLClientSetupEvent evt) {
    ClientEventsListener.setup();
  }

  private void registerKeys(final RegisterKeyMappingsEvent evt) {
    KeyRegistry.setup();
    evt.register(KeyRegistry.getToggle());
    evt.register(KeyRegistry.getTrigger());
  }
}
