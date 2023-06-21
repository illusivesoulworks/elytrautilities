package com.illusivesoulworks.elytrautilities;

import com.illusivesoulworks.spectrelib.config.SpectreLibInitializer;
import org.quiltmc.loader.api.ModContainer;

public class ElytraUtilitiesConfigInitializer implements SpectreLibInitializer {

  @Override
  public void onInitializeConfig(ModContainer modContainer) {
    ElytraUtilitiesMod.setup();
  }
}
