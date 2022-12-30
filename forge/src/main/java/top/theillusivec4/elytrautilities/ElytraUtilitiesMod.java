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
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import top.theillusivec4.elytrautilities.common.network.NetworkHandler;
import top.theillusivec4.elytrautilities.client.ClientEventsListener;
import top.theillusivec4.elytrautilities.client.KeyRegistry;
import top.theillusivec4.elytrautilities.common.CaelusPlugin;
import top.theillusivec4.elytrautilities.common.ConfigReader;

@Mod(Constants.MOD_ID)
public class ElytraUtilitiesMod {

  public ElytraUtilitiesMod() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    modEventBus.addListener(this::setup);
    modEventBus.addListener(this::clientSetup);
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigReader.CLIENT_SPEC);
    ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
        () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    CaelusPlugin.isCaelusLoaded = ModList.get().isLoaded("caelus");
  }

  private void setup(FMLCommonSetupEvent evt) {
    NetworkHandler.register();
  }

  private void clientSetup(final FMLClientSetupEvent evt) {
    KeyRegistry.setup();
    ClientEventsListener.setup();
  }
}