package konsola5.hephaestusplus;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import konsola5.hephaestusplus.datagen.*;
import konsola5.hephaestusplus.modifiers.*;
import konsola5.hephaestusplus.spritegen.HephaestusPlusMaterialSpriteProvider;
import konsola5.hephaestusplus.spritegen.HephaestusPlusPartSpriteProvider;
import konsola5.hephaestusplus.tools.ToolDefinitions;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import slimeknights.mantle.fluid.attributes.FluidAttributes;
import slimeknights.mantle.registration.ModelFluidAttributes;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.mantle.util.SimpleFlowableFluid;
import slimeknights.tconstruct.common.TinkerTabs;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.client.data.TinkerSpriteSourceGenerator;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.impl.SingleLevelModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class Registry {
    // Properties
    private static final Item.Properties PARTS_PROPS = new Item.Properties();
    private static final Item.Properties SMELTERY_PROPS = new Item.Properties();
    // Resource Locations (for NBT)
    public static final ResourceLocation PROMETHEUM_REPAIRS = new ResourceLocation(HephaestusPlus.MOD_ID, "prometheum_repairs");
    public static final ResourceLocation STORED_SOULS = new ResourceLocation(HephaestusPlus.MOD_ID, "stored_souls");
    public static final ResourceLocation TOOL_OWNER = new ResourceLocation(HephaestusPlus.MOD_ID, "tool_owner");
    // Deferred Registers
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(HephaestusPlus.MOD_ID);
    public static final ItemDeferredRegisterExtension ADDON_ITEMS = new ItemDeferredRegisterExtension(HephaestusPlus.MOD_ID);

    private static FluidAttributes.Builder hotBuilder() {
        return ModelFluidAttributes.builder().density(2000).viscosity(10000).temperature(1000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
    }

    protected static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(HephaestusPlus.MOD_ID);
    // Items

    public static ItemObject<ToolPartItem> crookHead;
    public static CastItemObject crookHeadCast;

    static {
        if (FabricLoader.getInstance().isModLoaded("fabricaeexnihilo")) {
            crookHead = ADDON_ITEMS.register("crook_head", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
            crookHeadCast = ADDON_ITEMS.registerCast("crook_head", SMELTERY_PROPS);
        } else {
            crookHead = null;
            crookHeadCast = null;
        }
    }

    // Modifiers


    public static StaticModifier<SingleLevelModifier> SMASHING = MODIFIERS.register("smashing", SingleLevelModifier::new);

    public static StaticModifier<SingleLevelModifier> CROOKING = MODIFIERS.register("crooking", SingleLevelModifier::new);
    public static StaticModifier<Modifier> REGROWTH = MODIFIERS.register("regrowth", RegrowthModifier::new);
    public static StaticModifier<Modifier> BANG_BANG = MODIFIERS.register("bang_bang", BangBangModifier::new);
    public static StaticModifier<Modifier> BRANDING = MODIFIERS.register("branding", BrandingModifier::new);
    // Tool properties
    public static StaticModifier<Modifier> CARMOT_SYNERGY = MODIFIERS.register("carmot_synergy", Modifier::new);
    public static StaticModifier<Modifier> SOLID = MODIFIERS.register("solid", SolidModifier::new);
    public static StaticModifier<Modifier> COSMIC = MODIFIERS.register("cosmic", CosmicModifier::new);
    public static StaticModifier<Modifier> PRISMATIC = MODIFIERS.register("prismatic", PrismaticModifier::new);
    public static StaticModifier<Modifier> STORM_SPELL = MODIFIERS.register("storm_spell", StormSpellModifier::new);
    public static StaticModifier<Modifier> SOUL_POWERED = MODIFIERS.register("soul_powered", SoulPoweredModifier::new);
    public static StaticModifier<Modifier> FREEZING = MODIFIERS.register("freezing", FreezingModifier::new);
    public static StaticModifier<Modifier> LEGENDARY_BANGLUM = MODIFIERS.register("legendary_banglum", LegendaryBanglumModifier::new);
    public static StaticModifier<NoLevelsModifier> UNOBTAINABLE = MODIFIERS.register("unobtainable", UnobtainableModifier::new);
    private static final Item.Properties TOOL = new FabricItemSettings().maxCount(1);
    // Hephaestus tools
    public static ItemObject<ModifiableItem> handHammer;
    public static ItemObject<ModifiableItem> crook;

    static {
        if (FabricLoader.getInstance().isModLoaded("fabricaeexnihilo")) {
            handHammer = ADDON_ITEMS.register("hand_hammer", () -> new ModifiableItem(TOOL, ToolDefinitions.HAND_HAMMER, TinkerTabs.TAB_TOOLS));
            crook = ADDON_ITEMS.register("crook", () -> new ModifiableItem(TOOL, ToolDefinitions.CROOK, TinkerTabs.TAB_TOOLS));
        } else {
            handHammer = null;
            crook = null;
        }
    }

    // HephaestusPlus fluids
    /*
        - Adamantite
        - Aquarium
        - Banglum
        - Carmot
        - Celestium
        - Durasteel
        - Hallowed
        - Kyber
        - Metallurgium
        - Mythril
        - Orichalcum
        - Palladium
        - Prometheum
        - Quadrillum
        - Runite
        - Star_platinum
        - Stormyx
    */
    public static final FluidObject<SimpleFlowableFluid> moltenAdamantite = FLUIDS.register(
            "molten_adamantite",
            hotBuilder().temperature(1000),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenAquarium = FLUIDS.register(
            "molten_aquarium",
            hotBuilder().temperature(1000),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenBanglum = FLUIDS.register(
            "molten_banglum",
            hotBuilder().temperature(1070),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenCarmot = FLUIDS.register(
            "molten_carmot",
            hotBuilder().temperature(1270),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenCelestium = FLUIDS.register(
            "molten_celestium",
            hotBuilder().temperature(1700),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenDurasteel = FLUIDS.register(
            "molten_durasteel",
            hotBuilder().temperature(1100),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenHallowed = FLUIDS.register(
            "molten_hallowed",
            hotBuilder().temperature(1500),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenKyber = FLUIDS.register(
            "molten_kyber",
            hotBuilder().temperature(1060),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenManganese = FLUIDS.register(
            "molten_manganese",
            hotBuilder().temperature(800),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenMetallurgium = FLUIDS.register(
            "molten_metallurgium",
            hotBuilder().temperature(1770),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenMythril = FLUIDS.register(
            "molten_mythril",
            hotBuilder().temperature(1200),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenOrichalcum = FLUIDS.register(
            "molten_orichalcum",
            hotBuilder().temperature(1050),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenPalladium = FLUIDS.register(
            "molten_palladium",
            hotBuilder().temperature(1200),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenPrometheum = FLUIDS.register(
            "molten_prometheum",
            hotBuilder().temperature(1100),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenQuadrillum = FLUIDS.register(
            "molten_quadrillum",
            hotBuilder().temperature(850),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenRunite = FLUIDS.register(
            "molten_runite",
            hotBuilder().temperature(1270),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenStarrite = FLUIDS.register(
            "molten_starrite",
            hotBuilder().temperature(1200),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenStarPlatinum = FLUIDS.register(
            "molten_star_platinum",
            hotBuilder().temperature(1600),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenStormyx = FLUIDS.register(
            "molten_stormyx",
            hotBuilder().temperature(1200),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);
    public static final FluidObject<SimpleFlowableFluid> moltenUnobtainium = FLUIDS.register(
            "molten_unobtainium",
            hotBuilder().temperature(1700),
            properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
            12);

    public static void init() {
        MODIFIERS.register();
        ADDON_ITEMS.register();
        FLUIDS.register();
    }

    public static void gatherData(FabricDataGenerator.Pack pack, ExistingFileHelper existingFileHelper) {

        pack.addProvider(HephPlusFluidTagsProvider::new);

        pack.addProvider(HephPlusToolRecipeProvider::new);
        pack.addProvider(HephPlusMaterialRecipeProvider::new);
        pack.addProvider(HephPlusStationSlotLayoutProvider::new);
        pack.addProvider(HephPlusModifierRecipeProvider::new);
        pack.addProvider(HephPlusToolDefinitionProvider::new);
        pack.addProvider(HephPlusModifierProvider::new);

        HephPlusMaterialDataProvider materials = pack.addProvider(HephPlusMaterialDataProvider::new);
        pack.addProvider((output, registriesFuture) -> new HephPlusMaterialStatsProvider(output, materials));
        pack.addProvider((output, registriesFuture) -> new HephPlusMaterialTraitsProvider(output, materials));
        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
        HephaestusPlusMaterialSpriteProvider moreToolMats = new HephaestusPlusMaterialSpriteProvider();
        TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();
        HephaestusPlusPartSpriteProvider morePartSprites = new HephaestusPlusPartSpriteProvider();
        //pack.addProvider((output, registriesFuture) -> new MaterialRenderInfoProvider(output, materialSprites));
        //pack.addProvider((output, registriesFuture) -> new GeneratorPartTextureJsonGenerator(output, HephaestusPlus.MOD_ID, partSprites));
        pack.addProvider((output, registriesFuture) -> new HephPlusMaterialRenderInfoProvider(output, moreToolMats));
        pack.addProvider((output, registriesFuture) -> new GeneratorPartTextureJsonGenerator(output, HephaestusPlus.MOD_ID, morePartSprites));
        //Tinkers' materials for HephaestusPlus parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, morePartSprites, materialSprites));
        //HephaestusPlus materials for Tinkers' parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, partSprites, moreToolMats));
        //HephaestusPlus materials for HephaestusPlus parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, morePartSprites, moreToolMats));
        pack.addProvider((output, registriesFuture) -> new TinkerSpriteSourceGenerator(output, existingFileHelper));
    }

}
