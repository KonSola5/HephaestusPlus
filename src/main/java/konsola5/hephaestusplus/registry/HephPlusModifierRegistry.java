package konsola5.hephaestusplus.registry;

import konsola5.hephaestusplus.HephaestusPlus;
import konsola5.hephaestusplus.modifiers.*;
import konsola5.hephaestusplus.modifiers.dynamic.DynamicBatteryModifier;
import konsola5.hephaestusplus.modifiers.dynamic.ForceFieldModifier;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierManager;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.impl.SingleLevelModifier;
import slimeknights.tconstruct.library.modifiers.util.DynamicModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static konsola5.hephaestusplus.HephaestusPlus.MOD_ID;

public class HephPlusModifierRegistry {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);

    public static final StaticModifier<SingleLevelModifier> SMASHING = MODIFIERS.register("smashing", SingleLevelModifier::new);
    public static final StaticModifier<SingleLevelModifier> CROOKING = MODIFIERS.register("crooking", SingleLevelModifier::new);
    public static final StaticModifier<Modifier> REGROWTH = MODIFIERS.register("regrowth", RegrowthModifier::new);
    public static final StaticModifier<Modifier> BANG_BANG = MODIFIERS.register("bang_bang", BangBangModifier::new);
    public static final StaticModifier<Modifier> BRANDING = MODIFIERS.register("branding", BrandingModifier::new);
    // Tool properties
    public static final StaticModifier<Modifier> CARMOT_SYNERGY = MODIFIERS.register("carmot_synergy", Modifier::new);
    public static final DynamicModifier<Modifier> CARMOT_SHIELD_MODIFIER = MODIFIERS.registerDynamic("carmot_shield", Modifier.class);
    public static final DynamicModifier<Modifier> CARMOT_BOOST = MODIFIERS.registerDynamic("carmot_boost", Modifier.class);
    public static final StaticModifier<Modifier> SOLID = MODIFIERS.register("solid", SolidModifier::new);
    public static final StaticModifier<Modifier> COSMIC = MODIFIERS.register("cosmic", CosmicModifier::new);
    public static final StaticModifier<Modifier> PRISMATIC = MODIFIERS.register("prismatic", PrismaticModifier::new);
    public static final StaticModifier<Modifier> STORM_SPELL = MODIFIERS.register("storm_spell", StormSpellModifier::new);
    public static final StaticModifier<Modifier> SOUL_POWERED = MODIFIERS.register("soul_powered", SoulPoweredModifier::new);
    public static final StaticModifier<Modifier> FREEZING = MODIFIERS.register("freezing", FreezingModifier::new);
    public static final StaticModifier<Modifier> LEGENDARY_BANGLUM = MODIFIERS.register("legendary_banglum", LegendaryBanglumModifier::new);
    public static final StaticModifier<NoLevelsModifier> UNOBTAINABLE = MODIFIERS.register("unobtainable", UnobtainableModifier::new);
    public static final StaticModifier<Modifier> MANASHIELD = MODIFIERS.register("manashield", ManashieldModifier::new);
    public static final StaticModifier<Modifier> MANASHOT = MODIFIERS.register("manashot", ManashotModifier::new);
    public static final StaticModifier<Modifier> CRUDE_MANASHIELD = MODIFIERS.register("crude_manashield", CrudeManashieldModifier::new);
    public static final StaticModifier<SingleLevelModifier> GARBAGE_COLLECTOR = MODIFIERS.register("garbage_collector", SingleLevelModifier::new);
    public static final DynamicModifier<Modifier> FAIRY_BLESSING = MODIFIERS.registerDynamic("fairy_blessing", Modifier.class);
    public static final StaticModifier<Modifier> TERRAFIRMA = MODIFIERS.register("terrafirma", TerrafirmaModifier::new);

//    public static final StaticModifier<BatteryModifier> BATTERY = MODIFIERS.register("battery", () -> new BatteryModifier(10000, 128));
//    public static final StaticModifier<BatteryModifier> BIG_BATTERY = MODIFIERS.register("big_battery", () -> new BatteryModifier(100000, 512));
//    public static final StaticModifier<BatteryModifier> HUGE_BATTERY = MODIFIERS.register("huge_battery", () -> new BatteryModifier(1000000, 2048));

    public static final StaticModifier<EnergyVelocityModifier> ENERGY_VELOCITY = MODIFIERS.register("energy_velocity", EnergyVelocityModifier::new);

    static void registerSerializers() {
        ModifierManager.MODIFIER_LOADERS.register(HephaestusPlus.getResource("battery"), DynamicBatteryModifier.LOADER);
        ModifierManager.MODIFIER_LOADERS.register(HephaestusPlus.getResource("force_field"), ForceFieldModifier.LOADER);
    }

    public static void register() {
        MODIFIERS.register();
        registerSerializers();
    }
}
