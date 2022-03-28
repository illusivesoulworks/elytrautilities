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

package top.theillusivec4.elytrautilities;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;
import top.theillusivec4.elytrautilities.client.ClientEventsListener;
import top.theillusivec4.elytrautilities.client.KeyRegistry;
import top.theillusivec4.elytrautilities.common.ConfigReader;

@Mod(Constants.MOD_ID)
public class ElytraUtilitiesMod {

  public ElytraUtilitiesMod() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    modEventBus.addListener(this::clientSetup);
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigReader.CLIENT_SPEC);
    ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
        () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY,
            (a, b) -> true));
  }

  private void clientSetup(final FMLClientSetupEvent evt) {
    KeyRegistry.setup();
    ClientEventsListener.setup();
  }
}