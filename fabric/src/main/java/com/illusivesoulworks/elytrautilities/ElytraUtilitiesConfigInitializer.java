package com.illusivesoulworks.elytrautilities;

import com.illusivesoulworks.spectrelib.config.SpectreLibInitializer;

public class ElytraUtilitiesConfigInitializer implements SpectreLibInitializer {

  @Override
  public void onInitializeConfig() {
    ElytraUtilitiesMod.setup();
  }
}
