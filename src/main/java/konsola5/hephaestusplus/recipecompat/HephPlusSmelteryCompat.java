package konsola5.hephaestusplus.recipecompat;

import konsola5.hephaestusplus.Registry;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.util.SimpleFlowableFluid;

import java.util.Locale;

/**
 * Enum holding all relevant smeltery compat. A copy-over from Hephaestus code
 */
public enum HephPlusSmelteryCompat {
    ADAMANTITE(Registry.moltenAdamantite, HephPlusByproducts.ORICHALCUM),
    AQUARIUM(Registry.moltenAquarium, true),
    BANGLUM(Registry.moltenBanglum, true),
    CARMOT(Registry.moltenCarmot, true),
    CELESTIUM(Registry.moltenCelestium),
    DURASTEEL(Registry.moltenDurasteel),
    HALLOWED(Registry.moltenHallowed),
    KYBER(Registry.moltenKyber, HephPlusByproducts.AMETHYST),
    MANGANESE(Registry.moltenManganese, true),
    METALLURGIUM(Registry.moltenMetallurgium),
    MYTHRIL(Registry.moltenMythril, true),
    ORICHALCUM(Registry.moltenOrichalcum, HephPlusByproducts.ADAMANTITE),
    PALLADIUM(Registry.moltenPalladium, true),
    PROMETHEUM(Registry.moltenPrometheum, true),
    QUADRILLUM(Registry.moltenQuadrillum, true),
    RUNITE(Registry.moltenRunite, HephPlusByproducts.SILVER),
    STARRITE(Registry.moltenStarrite, true),
    STAR_PLATINUM(Registry.moltenStarPlatinum),
    STORMYX(Registry.moltenStormyx, true),
    UNOBTAINIUM(Registry.moltenUnobtainium, true);

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