//package konsola5.hephaestusplus.recipecompat;
//
//import konsola5.hephaestusplus.Registry;
//import lombok.Getter;
//import lombok.experimental.Accessors;
//import net.minecraft.world.item.Item;
//import slimeknights.mantle.registration.object.FluidObject;
//import slimeknights.mantle.util.SimpleFlowableFluid;
//import slimeknights.tconstruct.fluids.TinkerFluids;
//import slimeknights.tconstruct.smeltery.data.Byproduct;
//
//import java.util.Locale;
//
///** Enum holding all relevant smeltery compat. A copy-over from Hephaestus code */
//public enum HephPlusSmelteryCompat {
//    // ores
////    TIN     (TinkerFluids.moltenTin,      Byproduct.COPPER),
////    ALUMINUM(TinkerFluids.moltenAluminum, Byproduct.IRON),
////    LEAD    (TinkerFluids.moltenLead,     Byproduct.SILVER, Byproduct.GOLD),
////    SILVER  (TinkerFluids.moltenSilver,   Byproduct.LEAD, Byproduct.GOLD),
////    NICKEL  (TinkerFluids.moltenNickel,   Byproduct.PLATINUM, Byproduct.IRON),
////    ZINC    (TinkerFluids.moltenZinc,     Byproduct.TIN, Byproduct.COPPER),
////    PLATINUM(TinkerFluids.moltenPlatinum, Byproduct.GOLD),
////    TUNGSTEN(TinkerFluids.moltenTungsten, Byproduct.PLATINUM, Byproduct.GOLD),
////    OSMIUM  (TinkerFluids.moltenOsmium,   Byproduct.IRON),
////    URANIUM (TinkerFluids.moltenUranium,  Byproduct.LEAD, Byproduct.COPPER),
////    // alloys
////    BRONZE    (TinkerFluids.moltenBronze),
////    BRASS     (TinkerFluids.moltenBrass),
////    ELECTRUM  (TinkerFluids.moltenElectrum),
////    INVAR     (TinkerFluids.moltenInvar),
////    CONSTANTAN(TinkerFluids.moltenConstantan),
////    PEWTER    (TinkerFluids.moltenPewter),
////    STEEL     (TinkerFluids.moltenSteel),
////    // thermal alloys
////    ENDERIUM(TinkerFluids.moltenEnderium),
////    LUMIUM  (TinkerFluids.moltenLumium),
////    SIGNALUM(TinkerFluids.moltenSignalum),
////    // mekanism alloys, they use dust as the not refined version of refined obsidian, so skip
////    REFINED_GLOWSTONE(TinkerFluids.moltenRefinedGlowstone, false),
////    REFINED_OBSIDIAN (TinkerFluids.moltenRefinedObsidian, false);
//    ADAMANTITE   (Registry.moltenAdamantite),
//    AQUARIUM     (Registry.moltenAquarium),
//    BANGLUM      (Registry.moltenBanglum),
//    CARMOT       (Registry.moltenCarmot),
//    CELESTIUM    (Registry.moltenCelestium),
//    DURASTEEL    (Registry.moltenDurasteel),
//    HALLOWED     (Registry.moltenHallowed),
//    KYBER        (Registry.moltenKyber),
//    METALLURGIUM (Registry.moltenMetallurgium),
//    MYTHRIL      (Registry.moltenMythril),
//    ORICHALCUM   (Registry.moltenOrichalcum),
//    PALLADIUM    (Registry.moltenPalladium),
//    PROMETHEUM   (Registry.moltenPrometheum),
//    QUADRILLUM   (Registry.moltenQuadrillum),
//    RUNITE       (Registry.moltenRunite),
//    STAR_PLATINUM(Registry.moltenStarPlatinum),
//    STORMYX      (Registry.moltenStormyx);
//    @Getter
//    private final String name = this.name().toLowerCase(Locale.US);
//    private final FluidObject<? extends SimpleFlowableFluid> fluid;
//    @Getter
//    private final boolean isOre;
//    @Accessors(fluent = true)
//    @Getter
//    private final boolean hasDust;
//    @Getter
//    private final HephPlusByproducts[] byproducts;
//
//    HephPlusSmelteryCompat(FluidObject<? extends SimpleFlowableFluid> fluid, boolean hasDust) {
//        this.fluid = fluid;
//        this.isOre = false;
//        this.byproducts = new HephPlusByproducts[0];
//        this.hasDust = hasDust;
//    }
//
//    /** Byproducts means its an ore, no byproucts are alloys */
//    HephPlusSmelteryCompat(FluidObject<? extends SimpleFlowableFluid> fluid, HephPlusByproducts... byproducts) {
//        this.fluid = fluid;
//        this.isOre = byproducts.length > 0;
//        this.byproducts = byproducts;
//        this.hasDust = true;
//    }
//
//    /** Gets the fluid for this compat */
//    public FluidObject<?> getFluid() {
//        return fluid;
//    }
//
//    /** Gets teh bucket for this compat */
//    public Item getBucket() {
//        return fluid.asItem();
//    }
//}