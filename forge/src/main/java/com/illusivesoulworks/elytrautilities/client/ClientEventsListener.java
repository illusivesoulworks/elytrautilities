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

import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@SuppressWarnings("unused")
public class ClientEventsListener {

  public static void setup() {
    MinecraftForge.EVENT_BUS.register(new ClientEventsListener());
  }

  @SubscribeEvent
  public void clientTick(final TickEvent.ClientTickEvent evt) {

    if (evt.phase == TickEvent.Phase.END) {
      ClientEvents.clientTick();
    }
  }

  @SubscribeEvent
  public void rightClick(final PlayerInteractEvent.RightClickBlock evt) {

    if (ClientEvents.restrictFirework(evt.getEntity(), evt.getItemStack().getItem())) {
      evt.setCanceled(true);
    }
  }

  @SubscribeEvent
  public void input(final MovementInputUpdateEvent evt) {
    ClientEvents.triggerFlight(evt.getEntity(), evt.getInput());
  }

  @SubscribeEvent
  public void onRenderGameOverlay(final RenderGuiOverlayEvent.Post evt) {

    if (evt.getOverlay() == VanillaGuiOverlay.POTION_ICONS.type()) {
      ClientEvents.renderIcon(evt.getGuiGraphics());
    }
  }
}
