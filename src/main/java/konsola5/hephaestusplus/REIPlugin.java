package konsola5.hephaestusplus;

import dev.architectury.fluid.FluidStack;
import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import konsola5.hephaestusplus.registry.HephPlusItemRegistry;
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
            removeFluid(registry, HephPlusFluidRegistry.moltenAdamantite  .get(), HephPlusFluidRegistry.moltenAdamantite.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenAquarium    .get(), HephPlusFluidRegistry.moltenAquarium.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenBanglum     .get(), HephPlusFluidRegistry.moltenBanglum.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenCarmot      .get(), HephPlusFluidRegistry.moltenCarmot.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenCelestium   .get(), HephPlusFluidRegistry.moltenCelestium.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenDurasteel   .get(), HephPlusFluidRegistry.moltenDurasteel.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenHallowed    .get(), HephPlusFluidRegistry.moltenHallowed.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenKyber       .get(), HephPlusFluidRegistry.moltenKyber.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenManganese   .get(), HephPlusFluidRegistry.moltenManganese.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenMetallurgium.get(), HephPlusFluidRegistry.moltenMetallurgium.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenMythril     .get(), HephPlusFluidRegistry.moltenMythril.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenOrichalcum  .get(), HephPlusFluidRegistry.moltenOrichalcum.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenPalladium   .get(), HephPlusFluidRegistry.moltenPalladium.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenPrometheum  .get(), HephPlusFluidRegistry.moltenPrometheum.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenQuadrillum  .get(), HephPlusFluidRegistry.moltenQuadrillum.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenRunite      .get(), HephPlusFluidRegistry.moltenRunite.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenStarPlatinum.get(), HephPlusFluidRegistry.moltenStarPlatinum.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenStarrite    .get(), HephPlusFluidRegistry.moltenStarrite.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenStormyx     .get(), HephPlusFluidRegistry.moltenStormyx.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenUnobtainium .get(), HephPlusFluidRegistry.moltenUnobtainium.asItem());
            registry.removeEntry(EntryStacks.of(HephPlusItemRegistry.carmotReinforcement));
            registry.removeEntry(EntryStack.of(EntryType.deferred(HephaestusPlus.getResource("modifier_entry")), "legendary_banglum"));
            registry.removeEntry(EntryStack.of(EntryType.deferred(HephaestusPlus.getResource("modifier_entry")), "carmot_shield"));
            registry.removeEntry(EntryStack.of(EntryType.deferred(HephaestusPlus.getResource("modifier_entry")), "carmot_boost"));
        }
        // Hide Crooking and Smashing is FEN isn't loaded
        if (!FabricLoader.getInstance().isModLoaded("fabricaeexnihilo")) {
            registry.removeEntry(EntryStack.of(EntryType.deferred(HephaestusPlus.getResource("modifier_entry")), "crooking"));
            registry.removeEntry(EntryStack.of(EntryType.deferred(HephaestusPlus.getResource("modifier_entry")), "smashing"));
        }
        // Hide fluids if Botania isn't loaded
        if (!FabricLoader.getInstance().isModLoaded("botania")) {
            removeFluid(registry, HephPlusFluidRegistry.moltenManasteel  .get(), HephPlusFluidRegistry.moltenManasteel.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenElementium .get(), HephPlusFluidRegistry.moltenElementium.asItem());
            removeFluid(registry, HephPlusFluidRegistry.moltenTerrasteel .get(), HephPlusFluidRegistry.moltenTerrasteel.asItem());
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    private static void removeFluid(EntryRegistry manager, Fluid fluid, Item bucket) {
        manager.removeEntry(EntryStacks.of(FluidStack.create(fluid, FluidConstants.BUCKET)));
        manager.removeEntry(EntryStacks.of(bucket));
    }

}
