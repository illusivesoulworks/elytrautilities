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

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import top.theillusivec4.elytrautilities.client.ClientEventsListener;
import top.theillusivec4.elytrautilities.client.KeyRegistry;
import top.theillusivec4.elytrautilities.common.CaelusPlugin;
import top.theillusivec4.elytrautilities.common.ConfigReader;

@Mod(Constants.MOD_ID)
public class ElytraUtilitiesMod {

  private static boolean isCaelusLoaded = false;

  public ElytraUtilitiesMod() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    modEventBus.addListener(this::clientSetup);
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigReader.CLIENT_SPEC);
    ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
        () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    isCaelusLoaded = ModList.get().isLoaded("caelus");
  }

  public static boolean canFly(ClientPlayerEntity player) {

    if (isCaelusLoaded) {
      return CaelusPlugin.canFly(player);
    }
    return player.getItemBySlot(EquipmentSlotType.CHEST).canElytraFly(player);
  }

  private void clientSetup(final FMLClientSetupEvent evt) {
    KeyRegistry.setup();
    ClientEventsListener.setup();
  }
}