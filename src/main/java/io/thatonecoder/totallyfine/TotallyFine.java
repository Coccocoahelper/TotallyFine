package io.thatonecoder.totallyfine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.ornithemc.osl.entrypoints.api.client.ClientModInitializer;

import java.util.ArrayList;
import java.util.List;

public class TotallyFine implements ClientModInitializer {

	public static final String MODID = "totallyfine";

	private static TotallyFine instance;

	// Since AxolotlClient may initialize this class as a module before it gets loaded as a mod by fabric we have to defer the former to run after the latter.
	// But since the load order is non-deterministic this may not always be the case
	private static boolean loadedByFabric;
	private static final List<Runnable> tasks = new ArrayList<>();

	public TotallyFine() {
		if (instance != null) {
			throw new IllegalStateException();
		}

		loadedByFabric = true;
		instance = this;
		tasks.forEach(Runnable::run);
		tasks.clear();
	}

	public static void runAfterFabricLoad(Runnable task) {
		if (loadedByFabric) {
			task.run();
		} else tasks.add(task);
	}

	@Override
	public void initClient() {
		LOGGER.info("Initializing TotallyFine!");
		TotallyFineConfig.instance.initConfig();
	}
}