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

package top.theillusivec4.elytrautilities.common;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import top.theillusivec4.elytrautilities.Constants;

public class ConfigReader {

  public static final ForgeConfigSpec CLIENT_SPEC;
  public static final Client CLIENT;
  private static final String CONFIG_PREFIX = "gui." + Constants.MOD_ID + ".config.";

  static {
    final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder()
        .configure(Client::new);
    CLIENT_SPEC = specPair.getRight();
    CLIENT = specPair.getLeft();
  }

  public static class Client {

    public final ForgeConfigSpec.BooleanValue toggleIcon;
    public final ForgeConfigSpec.BooleanValue simpleTakeoff;
    public final ForgeConfigSpec.BooleanValue enableElytra;
    public final ForgeConfigSpec.BooleanValue jumpTriggerable;

    Client(ForgeConfigSpec.Builder builder) {

      builder.comment("Client only settings").push("client");

      toggleIcon = builder.comment(Constants.Config.RENDER_ICON_DESC)
          .translation(CONFIG_PREFIX + "toggleIcon").define("toggleIcon", true);
      simpleTakeoff = builder.comment(Constants.Config.SIMPLE_TAKEOFF_DESC)
          .translation(CONFIG_PREFIX + "simpleTakeoff").define("simpleTakeoff", true);
      enableElytra = builder.comment(Constants.Config.ENABLE_ELYTRA_DESC)
          .translation(CONFIG_PREFIX + "enableElytra").define("enableElytra", true);
      jumpTriggerable = builder.comment(Constants.Config.ENABLE_JUMP_TRIGGER)
          .translation(CONFIG_PREFIX + "jumpTriggerable").define("jumpTriggerable", true);

      builder.pop();
    }
  }
}
