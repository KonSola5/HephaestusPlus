package konsola5.hephaestusplus;

import dev.architectury.fluid.FluidStack;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.EntryType;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

public class REIPlugin implements REIClientPlugin {
    @Override
    public void registerEntries(EntryRegistry registry) {
        // Hide fluids and Legendary Banglum if Mythic Metals isn't loaded
        if (!FabricLoader.getInstance().isModLoaded("mythicmetals")) {
            removeFluid(registry, HephPlusRegistry.moltenAdamantite  .get(), HephPlusRegistry.moltenAdamantite.asItem());
            removeFluid(registry, HephPlusRegistry.moltenAquarium    .get(), HephPlusRegistry.moltenAquarium.asItem());
            removeFluid(registry, HephPlusRegistry.moltenBanglum     .get(), HephPlusRegistry.moltenBanglum.asItem());
            removeFluid(registry, HephPlusRegistry.moltenCarmot      .get(), HephPlusRegistry.moltenCarmot.asItem());
            removeFluid(registry, HephPlusRegistry.moltenCelestium   .get(), HephPlusRegistry.moltenCelestium.asItem());
            removeFluid(registry, HephPlusRegistry.moltenDurasteel   .get(), HephPlusRegistry.moltenDurasteel.asItem());
            removeFluid(registry, HephPlusRegistry.moltenHallowed    .get(), HephPlusRegistry.moltenHallowed.asItem());
            removeFluid(registry, HephPlusRegistry.moltenKyber       .get(), HephPlusRegistry.moltenKyber.asItem());
            removeFluid(registry, HephPlusRegistry.moltenManganese   .get(), HephPlusRegistry.moltenManganese.asItem());
            removeFluid(registry, HephPlusRegistry.moltenMetallurgium.get(), HephPlusRegistry.moltenMetallurgium.asItem());
            removeFluid(registry, HephPlusRegistry.moltenMythril     .get(), HephPlusRegistry.moltenMythril.asItem());
            removeFluid(registry, HephPlusRegistry.moltenOrichalcum  .get(), HephPlusRegistry.moltenOrichalcum.asItem());
            removeFluid(registry, HephPlusRegistry.moltenPalladium   .get(), HephPlusRegistry.moltenPalladium.asItem());
            removeFluid(registry, HephPlusRegistry.moltenPrometheum  .get(), HephPlusRegistry.moltenPrometheum.asItem());
            removeFluid(registry, HephPlusRegistry.moltenQuadrillum  .get(), HephPlusRegistry.moltenQuadrillum.asItem());
            removeFluid(registry, HephPlusRegistry.moltenRunite      .get(), HephPlusRegistry.moltenRunite.asItem());
            removeFluid(registry, HephPlusRegistry.moltenStarPlatinum.get(), HephPlusRegistry.moltenStarPlatinum.asItem());
            removeFluid(registry, HephPlusRegistry.moltenStarrite    .get(), HephPlusRegistry.moltenStarrite.asItem());
            removeFluid(registry, HephPlusRegistry.moltenStormyx     .get(), HephPlusRegistry.moltenStormyx.asItem());
            removeFluid(registry, HephPlusRegistry.moltenUnobtainium .get(), HephPlusRegistry.moltenUnobtainium.asItem());
            registry.removeEntry(EntryStack.of(EntryType.deferred(HephaestusPlus.getResource("modifier_entry")), "legendary_banglum"));
        }
        // Hide Crooking and Smashing is FEN isn't loaded
        if (!FabricLoader.getInstance().isModLoaded("fabricaeexnihilo")) {
            registry.removeEntry(EntryStack.of(EntryType.deferred(HephaestusPlus.getResource("modifier_entry")), "crooking"));
            registry.removeEntry(EntryStack.of(EntryType.deferred(HephaestusPlus.getResource("modifier_entry")), "smashing"));
        }
    }

    private static void removeFluid(EntryRegistry manager, Fluid fluid, Item bucket) {
        manager.removeEntry(EntryStacks.of(FluidStack.create(fluid, FluidConstants.BUCKET)));
        manager.removeEntry(EntryStacks.of(bucket));
    }

}
