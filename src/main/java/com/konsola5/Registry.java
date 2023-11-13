package com.konsola5;

import com.konsola5.modifiers.BrandingModifier;
import com.konsola5.datagen.*;
import com.konsola5.modifiers.SolidModifier;
import com.konsola5.spritegen.HephaestusPlusMaterialSpriteProvider;
import com.konsola5.spritegen.HephaestusPlusPartSpriteProvider;
import com.konsola5.tools.ToolDefinitions;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.TinkerTabs;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.client.data.TinkerSpriteSourceGenerator;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.impl.SingleLevelModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import com.konsola5.modifiers.RegrowthModifier;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.data.material.MaterialRenderInfoProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static com.konsola5.HephaestusPlus.MOD_ID;

public class Registry {
    // Properties
    private static final Item.Properties PARTS_PROPS = new Item.Properties();
    private static final Item.Properties SMELTERY_PROPS = new Item.Properties();
    // Resource Locations (for NBT)
    public static final ResourceLocation PROMETHEUM_REPAIRS = new ResourceLocation(MOD_ID, "prometheum_repairs");
    // Deferred Registers
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
    public static final ItemDeferredRegisterExtension ADDON_ITEMS = new ItemDeferredRegisterExtension(MOD_ID);
    // Items
    public static final ItemObject<ToolPartItem> crookHead = ADDON_ITEMS.register("crook_head", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));

    public static final CastItemObject crookHeadCast = ADDON_ITEMS.registerCast("crook_head", SMELTERY_PROPS);
    // Modifiers

    public static StaticModifier<Modifier> REGROWTH = MODIFIERS.register("regrowth", RegrowthModifier::new);
    public static StaticModifier<SingleLevelModifier> SMASHING = MODIFIERS.register("smashing", SingleLevelModifier::new);

    public static StaticModifier<SingleLevelModifier> CROOKING = MODIFIERS.register("crooking", SingleLevelModifier::new);
    public static StaticModifier<Modifier> BRANDING = MODIFIERS.register("branding", BrandingModifier::new);
    // Tool properties
    public static StaticModifier<Modifier> CARMOT_FORTUNE = MODIFIERS.register("carmot_fortune", Modifier::new);
    public static StaticModifier<Modifier> SOLID = MODIFIERS.register("solid", SolidModifier::new);
    private static final Item.Properties TOOL = new FabricItemSettings().maxCount(1);
    // Hephaestus tools
    public static final ItemObject<ModifiableItem> handHammer = ADDON_ITEMS.register("hand_hammer", () -> new ModifiableItem(TOOL, ToolDefinitions.HAND_HAMMER, TinkerTabs.TAB_TOOLS));
    public static final ItemObject<ModifiableItem> crook = ADDON_ITEMS.register("crook", () -> new ModifiableItem(TOOL, ToolDefinitions.CROOK, TinkerTabs.TAB_TOOLS));

    public static void init() {
        MODIFIERS.register();
        ADDON_ITEMS.register();
    }

    public static void gatherData(FabricDataGenerator.Pack pack, ExistingFileHelper existingFileHelper) {

        pack.addProvider(HephaestusPlusToolRecipeProvider::new);
        pack.addProvider(HephaestusPlusStationSlotLayoutProvider::new);
        pack.addProvider(HephaestusPlusModifierRecipeProvider::new);
        pack.addProvider(HephaestusPlusToolDefinitionProvider::new);
        pack.addProvider(HephaestusPlusModifierProvider::new);

        HephaestusPlusMaterialDataProvider materials = pack.addProvider(HephaestusPlusMaterialDataProvider::new);
        pack.addProvider((output, registriesFuture) -> new HephaestusPlusMaterialStatsProvider(output, materials));
        pack.addProvider((output, registriesFuture) -> new HephaestusPlusMaterialTraitsProvider(output, materials));
        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
        HephaestusPlusMaterialSpriteProvider moreToolMats = new HephaestusPlusMaterialSpriteProvider();
        TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();
        HephaestusPlusPartSpriteProvider morePartSprites = new HephaestusPlusPartSpriteProvider();
        //pack.addProvider((output, registriesFuture) -> new MaterialRenderInfoProvider(output, materialSprites));
        //pack.addProvider((output, registriesFuture) -> new GeneratorPartTextureJsonGenerator(output, HephaestusPlus.MOD_ID, partSprites));
        pack.addProvider((output, registriesFuture) -> new HephaestusPlusMaterialRenderInfoProvider(output, moreToolMats));
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
