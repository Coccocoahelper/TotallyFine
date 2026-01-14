/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * For more information, see the LICENSE file.
 */
package io.thatonecoder.totallyfine.config;

import io.github.axolotlclient.AxolotlClientConfig.api.AxolotlClientConfig;
import io.github.axolotlclient.AxolotlClientConfig.api.manager.ConfigManager;
import io.github.axolotlclient.AxolotlClientConfig.api.options.OptionCategory;
import io.github.axolotlclient.AxolotlClientConfig.impl.managers.VersionedJsonConfigManager;
import io.github.axolotlclient.AxolotlClientConfig.impl.options.BooleanOption;
import io.thatonecoder.totallyfine.TotallyFine;
import lombok.Getter;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.ornithemc.osl.lifecycle.api.client.MinecraftClientEvents;

import java.util.function.Supplier;

public class TotallyFineConfig {

	public static TotallyFineConfig instance = new TotallyFineConfig();

	@Getter
    private final OptionCategory category = OptionCategory.create("TotallyFine");
	private final OptionCategory performance = OptionCategory.create("performance");
	private final OptionCategory details = OptionCategory.create("details");
	private final OptionCategory playerModels = OptionCategory.create("playerModels");

	// Performance
	private final BooleanOption particleCulling = new BooleanOption("particleCulling", true);

	// Details
	public final BooleanOption sky = new BooleanOption("sky", true);
	public final BooleanOption clouds = new BooleanOption("clouds", true);
	public final BooleanOption stars = new BooleanOption("stars", true);
	public final BooleanOption sun = new BooleanOption("sun", true);
	public final BooleanOption moon = new BooleanOption("moon", true);
	public final BooleanOption snowAndRain = new BooleanOption("snowAndRain", true);
	public final BooleanOption fog = new BooleanOption("fog", true);
	public final BooleanOption vignette = new BooleanOption("vignette", true);
	public final BooleanOption disableTextShadows = new BooleanOption("disableTextShadows", false);
	public final BooleanOption renderItemInHand = new BooleanOption("renderItemInHand", true);

	// Player models
	public final BooleanOption capes = new BooleanOption("capes", true);
	public final BooleanOption ears = new BooleanOption("ears", true);
	public final BooleanOption leftArm = new BooleanOption("leftArm", true);
	public final BooleanOption rightArm = new BooleanOption("rightArm", true);

	public void initConfig() {
		category.add(
			performance,
			details
		);

		category.add(
			disableTextShadows,
			renderItemInHand
		);

		performance.add(
			particleCulling
		);

		details.add(
			sky,
			clouds,
			stars,
			sun,
			moon,
			snowAndRain,
			fog,
			vignette
		);

		details.add(
			playerModels
		);

		playerModels.add(
			capes,
			ears,
			leftArm,
			rightArm
		);

		ConfigManager configManager = new VersionedJsonConfigManager(FabricLoader.getInstance().getConfigDir().resolve(TotallyFine.MODID + ".json"),
			category, 1, (configVersion, configVersion1, optionCategory, jsonObject) -> jsonObject);
		AxolotlClientConfig.getInstance().register(configManager);
		configManager.load();
	}
}