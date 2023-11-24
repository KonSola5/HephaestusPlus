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
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.plugin.rei.TConstructREIConstants;

public class REIPlugin implements REIClientPlugin {
    @Override
    public void registerEntries(EntryRegistry registry) {
        // Hide fluids and Legendary Banglum if Mythic Metals isn't loaded
        if (!FabricLoader.getInstance().isModLoaded("mythicmetals")) {
            removeFluid(registry, Registry.moltenAdamantite  .get(), Registry.moltenAdamantite.asItem());
            removeFluid(registry, Registry.moltenAquarium    .get(), Registry.moltenAquarium.asItem());
            removeFluid(registry, Registry.moltenBanglum     .get(), Registry.moltenBanglum.asItem());
            removeFluid(registry, Registry.moltenCarmot      .get(), Registry.moltenCarmot.asItem());
            removeFluid(registry, Registry.moltenCelestium   .get(), Registry.moltenCelestium.asItem());
            removeFluid(registry, Registry.moltenDurasteel   .get(), Registry.moltenDurasteel.asItem());
            removeFluid(registry, Registry.moltenHallowed    .get(), Registry.moltenHallowed.asItem());
            removeFluid(registry, Registry.moltenKyber       .get(), Registry.moltenKyber.asItem());
            removeFluid(registry, Registry.moltenManganese   .get(), Registry.moltenManganese.asItem());
            removeFluid(registry, Registry.moltenMetallurgium.get(), Registry.moltenMetallurgium.asItem());
            removeFluid(registry, Registry.moltenMythril     .get(), Registry.moltenMythril.asItem());
            removeFluid(registry, Registry.moltenOrichalcum  .get(), Registry.moltenOrichalcum.asItem());
            removeFluid(registry, Registry.moltenPalladium   .get(), Registry.moltenPalladium.asItem());
            removeFluid(registry, Registry.moltenPrometheum  .get(), Registry.moltenPrometheum.asItem());
            removeFluid(registry, Registry.moltenQuadrillum  .get(), Registry.moltenQuadrillum.asItem());
            removeFluid(registry, Registry.moltenRunite      .get(), Registry.moltenRunite.asItem());
            removeFluid(registry, Registry.moltenStarPlatinum.get(), Registry.moltenStarPlatinum.asItem());
            removeFluid(registry, Registry.moltenStarrite    .get(), Registry.moltenStarrite.asItem());
            removeFluid(registry, Registry.moltenStormyx     .get(), Registry.moltenStormyx.asItem());
            removeFluid(registry, Registry.moltenUnobtainium .get(), Registry.moltenUnobtainium.asItem());
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
