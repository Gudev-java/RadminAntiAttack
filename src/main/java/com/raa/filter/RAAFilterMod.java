package com.raa.filter;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RAAFilterMod implements ModInitializer {
	public static final String MOD_ID = "raa-filter";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("RAA фильтр запущен. Теперь боты сосут бибу.");
	}
}
