package konsola5.hephaestusplus.recipecompat;

import konsola5.hephaestusplus.HephPlusRegistry;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.util.SimpleFlowableFluid;

import java.util.Locale;

/**
 * Enum holding all relevant smeltery compat. A copy-over from Hephaestus code
 */
public enum HephPlusSmelteryCompat {
    ADAMANTITE(HephPlusRegistry.moltenAdamantite, HephPlusByproducts.ORICHALCUM),
    AQUARIUM(HephPlusRegistry.moltenAquarium, true),
    BANGLUM(HephPlusRegistry.moltenBanglum, true),
    CARMOT(HephPlusRegistry.moltenCarmot, true),
    CELESTIUM(HephPlusRegistry.moltenCelestium),
    DURASTEEL(HephPlusRegistry.moltenDurasteel),
    HALLOWED(HephPlusRegistry.moltenHallowed),
    KYBER(HephPlusRegistry.moltenKyber, HephPlusByproducts.AMETHYST),
    MANGANESE(HephPlusRegistry.moltenManganese, true),
    METALLURGIUM(HephPlusRegistry.moltenMetallurgium),
    MYTHRIL(HephPlusRegistry.moltenMythril, true),
    ORICHALCUM(HephPlusRegistry.moltenOrichalcum, HephPlusByproducts.ADAMANTITE),
    PALLADIUM(HephPlusRegistry.moltenPalladium, true),
    PROMETHEUM(HephPlusRegistry.moltenPrometheum, true),
    QUADRILLUM(HephPlusRegistry.moltenQuadrillum, true),
    RUNITE(HephPlusRegistry.moltenRunite, HephPlusByproducts.SILVER),
    STAR_PLATINUM(HephPlusRegistry.moltenStarPlatinum),
    STORMYX(HephPlusRegistry.moltenStormyx, true),
    UNOBTAINIUM(HephPlusRegistry.moltenUnobtainium, true);

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