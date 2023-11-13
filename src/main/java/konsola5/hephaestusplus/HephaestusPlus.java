package konsola5.hephaestusplus;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import io.github.fabricators_of_create.porting_lib.util.TierSortingRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;

public class HephaestusPlus implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("hephaestusplus");
	public static final String MOD_ID = "hephaestusplus";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		MixinExtrasBootstrap.init();
		Registry.init();
		// The Metallurgium tier, surprised that mods generally don't initialize tiers like that
		if (!TierSortingRegistry.isTierSorted(MetallurgiumTier.instance)) {
			if (TierSortingRegistry.getSortedTiers().size() != 0) {
				TierSortingRegistry.registerTier(MetallurgiumTier.instance,
						new ResourceLocation(HephaestusPlus.MOD_ID + ":metallurgium"), List.of(TierSortingRegistry.getSortedTiers().get(TierSortingRegistry.getSortedTiers().size())), List.of());
			} else {
				TierSortingRegistry.registerTier(MetallurgiumTier.instance,
						new ResourceLocation(HephaestusPlus.MOD_ID + ":metallurgium"), List.of(Tiers.NETHERITE), List.of());
			}
		}
		LOGGER.info("Hello Fabric world!");
	}

	public static ResourceLocation getResource(String name) {
		return new ResourceLocation(MOD_ID, name);
	}
	public static String makeTranslationKey(String base, @Nullable ResourceLocation name) {
		return net.minecraft.Util.makeDescriptionId(base, name);
	}

	public static String makeTranslationKey(String base, String name) {
		return makeTranslationKey(base, getResource(name));
	}

	public static MutableComponent makeTranslation(String base, String name) {
		return Component.translatable(makeTranslationKey(base, name));
	}

	public static MutableComponent makeTranslation(String base, String name, Object... arguments) {
		return Component.translatable(makeTranslationKey(base, name), arguments);
	}
}