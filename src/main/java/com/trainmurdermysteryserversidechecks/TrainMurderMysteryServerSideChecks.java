package com.trainmurdermysteryserversidechecks;

import com.trainmurdermysteryserversidechecks.checks.ServerChecks;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainMurderMysteryServerSideChecks implements ModInitializer {
	public static final String MOD_ID = "trainmurdermysteryserversidechecks";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

        //Server Keybind Checks
        ServerChecks.init();

	}
}