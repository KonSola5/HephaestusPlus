package konsola5.hephaestusplus.recipecompat;

import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.util.SimpleFlowableFluid;

import java.util.Locale;

/**
 * Enum holding all relevant smeltery compat. A copy-over from Hephaestus code
 */
public enum HephPlusSmelteryCompat {
    ADAMANTITE(HephPlusFluidRegistry.moltenAdamantite, HephPlusByproducts.ORICHALCUM),
    AQUARIUM(HephPlusFluidRegistry.moltenAquarium, true),
    BANGLUM(HephPlusFluidRegistry.moltenBanglum, true),
    CARMOT(HephPlusFluidRegistry.moltenCarmot, true),
    CELESTIUM(HephPlusFluidRegistry.moltenCelestium),
    DURASTEEL(HephPlusFluidRegistry.moltenDurasteel),
    HALLOWED(HephPlusFluidRegistry.moltenHallowed),
    KYBER(HephPlusFluidRegistry.moltenKyber, HephPlusByproducts.AMETHYST),
    MANGANESE(HephPlusFluidRegistry.moltenManganese, true),
    METALLURGIUM(HephPlusFluidRegistry.moltenMetallurgium),
    MYTHRIL(HephPlusFluidRegistry.moltenMythril, true),
    ORICHALCUM(HephPlusFluidRegistry.moltenOrichalcum, HephPlusByproducts.ADAMANTITE),
    PALLADIUM(HephPlusFluidRegistry.moltenPalladium, true),
    PROMETHEUM(HephPlusFluidRegistry.moltenPrometheum, true),
    QUADRILLUM(HephPlusFluidRegistry.moltenQuadrillum, true),
    RUNITE(HephPlusFluidRegistry.moltenRunite, HephPlusByproducts.SILVER),
    STAR_PLATINUM(HephPlusFluidRegistry.moltenStarPlatinum),
    STORMYX(HephPlusFluidRegistry.moltenStormyx, true),
    UNOBTAINIUM(HephPlusFluidRegistry.moltenUnobtainium, true),

    MANASTEEL(HephPlusFluidRegistry.moltenManasteel),
    ELEMENTIUM(HephPlusFluidRegistry.moltenElementium),
    TERRASTEEL(HephPlusFluidRegistry.moltenTerrasteel),
    ;

    private final String name = this.name().toLowerCase(Locale.US);
    private final FluidObject<? extends SimpleFlowableFluid> fluid;

    private final boolean isOre;
    private final boolean hasDust;

    private final HephPlusByproducts[] byproducts;

    /**
     * Byproducts means it's an ore, no byproducts are alloys
     */
    HephPlusSmelteryCompat(FluidObject<? extends SimpleFlowableFluid> fluid, HephPlusByproducts... byproducts) {
        this.fluid = fluid;
        this.isOre = byproducts.length > 0;
        this.byproducts = byproducts;
        this.hasDust = true;
    }

    HephPlusSmelteryCompat(FluidObject<? extends SimpleFlowableFluid> fluid, boolean isOre) {
        this.fluid = fluid;
        this.isOre = isOre;
        this.byproducts = new HephPlusByproducts[0];
        this.hasDust = true;
    }

    /**
     * Gets the fluid for this compat
     */
    public FluidObject<?> getFluid() {
        return fluid;
    }

    /**
     * Gets the bucket for this compat
     */
    public Item getBucket() {
        return fluid.asItem();
    }

    public String getName() {
        return name;
    }

    public boolean isOre() {
        return isOre;
    }

    public boolean isHasDust() {
        return hasDust;
    }

    public HephPlusByproducts[] getByproducts() {
        return byproducts;
    }

    public boolean hasDust() {
        return hasDust;
    }
}