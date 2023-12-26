package konsola5.hephaestusplus.recipecompat;

import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import net.minecraft.world.level.material.Fluid;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IByproduct;
import slimeknights.tconstruct.library.recipe.FluidValues;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * Standard ore byproducts for smeltery ores, this enum exists to simplify our builders to allow passing 3 args in varargs
 */
// @RequiredArgsConstructor also doesn't work...
public enum HephPlusByproducts implements IByproduct {
    // base metals
//    COPPER    (true, TinkerFluids.moltenCopper),
//    IRON      (true, TinkerFluids.moltenIron),
//    GOLD      (true, TinkerFluids.moltenGold),
//    SMALL_GOLD("gold", true, TinkerFluids.moltenGold, FluidValues.NUGGET * 3),
//    COBALT    (true, TinkerFluids.moltenCobalt),
//    // compat metals
//    TIN     (false, TinkerFluids.moltenTin),
    SILVER(false, TinkerFluids.moltenSilver),
    //    NICKEL  (false, TinkerFluids.moltenNickel),
//    LEAD    (false, TinkerFluids.moltenLead),
//    PLATINUM("platinum", false, TinkerFluids.moltenPlatinum, FluidValues.NUGGET * 3),
//    // gems
//    DIAMOND ("diamond",  true, TinkerFluids.moltenDiamond, FluidValues.GEM),
    AMETHYST("amethyst", true, TinkerFluids.moltenAmethyst, FluidValues.GEM),
    ORICHALCUM(false, HephPlusFluidRegistry.moltenOrichalcum),
    ADAMANTITE(false, HephPlusFluidRegistry.moltenAdamantite);
//    QUARTZ  ("quartz",   true, TinkerFluids.moltenQuartz, FluidValues.GEM);

    ;
    private final String name;
    private final boolean alwaysPresent;
    private final Supplier<? extends Fluid> fluidSupplier;
    private final long amount;

    HephPlusByproducts(boolean alwaysPresent, Supplier<? extends Fluid> fluidSupplier) {
        this.name = name().toLowerCase(Locale.US);
        this.alwaysPresent = alwaysPresent;
        this.fluidSupplier = fluidSupplier;
        this.amount = FluidValues.INGOT;
    }

    HephPlusByproducts(String name, boolean alwaysPresent, Supplier<? extends Fluid> fluidSupplier, long amount) {
        this.name = name;
        this.alwaysPresent = alwaysPresent;
        this.fluidSupplier = fluidSupplier;
        this.amount = amount;
    }

    @Override
    public Fluid getFluid() {
        return fluidSupplier.get();
    }

    // For some reason, @Getter annotation doesn't want to work here, so needed to make getters manually.
// Else, project won't compile.
    @Override
    public long getAmount() {
        return amount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAlwaysPresent() {
        return alwaysPresent;
    }
}