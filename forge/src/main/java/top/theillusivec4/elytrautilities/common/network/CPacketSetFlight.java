/*
 * Copyright (C) 2019  C4
 *
 * This file is part of Caelus, a mod made for Minecraft.
 *
 * Caelus is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Caelus is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Caelus.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.elytrautilities.common.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.network.NetworkEvent;
import top.theillusivec4.elytrautilities.common.CaelusPlugin;

import java.util.function.Supplier;

public class CPacketSetFlight {

  public static void encode(CPacketSetFlight msg, PacketBuffer buf) {
  }

  public static CPacketSetFlight decode(PacketBuffer buf) {
    return new CPacketSetFlight();
  }

  public static void handle(CPacketSetFlight msg, Supplier<NetworkEvent.Context> ctx) {

    ctx.get().enqueueWork(() -> {
      ServerPlayerEntity sender = ctx.get().getSender();

      if (sender == null) {
        return;
      }
      sender.stopFallFlying();

      if (!sender.isOnGround() && !sender.isFallFlying() && !sender.isInWater() && !sender
          .hasEffect(Effects.LEVITATION) && CaelusPlugin.canFly(sender)) {
        sender.startFallFlying();
      } else {
        sender.stopFallFlying();
      }
    });
    ctx.get().setPacketHandled(true);
  }
}
