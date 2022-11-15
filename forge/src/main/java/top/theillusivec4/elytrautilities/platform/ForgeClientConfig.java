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

package top.theillusivec4.elytrautilities.platform;

import top.theillusivec4.elytrautilities.common.ConfigReader;
import top.theillusivec4.elytrautilities.platform.services.IClientConfig;

public class ForgeClientConfig implements IClientConfig {

  @Override
  public boolean canSimpleTakeoff() {
    return ConfigReader.CLIENT.simpleTakeoff.get();
  }

  @Override
  public boolean canRenderIcon() {
    return ConfigReader.CLIENT.toggleIcon.get();
  }

  @Override
  public boolean isJumpTriggerable() {
    return ConfigReader.CLIENT.jumpTriggerable.get();
  }

  @Override
  public boolean isElytraEnabled() {
    return ConfigReader.CLIENT.enableElytra.get();
  }

  @Override
  public void setElytraEnabled(boolean state) {
    ConfigReader.CLIENT.enableElytra.set(state);
    ConfigReader.CLIENT.enableElytra.save();
  }
}
