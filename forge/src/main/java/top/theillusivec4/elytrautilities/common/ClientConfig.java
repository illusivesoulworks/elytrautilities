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

import top.theillusivec4.elytrautilities.common.ConfigReader;

public class ClientConfig {

    public static boolean canSimpleTakeoff() {
        return ConfigReader.CLIENT.simpleTakeoff.get();
    }

    public static boolean canRenderIcon() {
        return ConfigReader.CLIENT.toggleIcon.get();
    }

    public static boolean isJumpTriggerable() {
        return ConfigReader.CLIENT.jumpTriggerable.get();
    }

    public static boolean isElytraEnabled() {
        return ConfigReader.CLIENT.enableElytra.get();
    }

    public static void setElytraEnabled(boolean state) {
        ConfigReader.CLIENT.enableElytra.set(state);
        ConfigReader.CLIENT.enableElytra.save();
    }
}