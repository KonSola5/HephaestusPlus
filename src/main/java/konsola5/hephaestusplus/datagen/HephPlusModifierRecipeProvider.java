package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.HephaestusPlus;
import konsola5.hephaestusplus.ids.MoarMaterialIds;
import konsola5.hephaestusplus.ids.MoarModifierIds;
import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import konsola5.hephaestusplus.registry.HephPlusItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import nourl.mythicmetals.item.MythicItems;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.ModifierMatch;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.adding.SwappableModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.tools.TinkerModifiers;
import techreborn.init.TRContent;
import wraith.fabricaeexnihilo.modules.ModTags;

import java.util.function.Consumer;

public class HephPlusModifierRecipeProvider extends BaseRecipeProvider {
    public HephPlusModifierRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        addModifierRecipes(consumer);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Modifier Recipes";
    }

    private void addModifierRecipes(Consumer<FinishedRecipe> consumer) {
        String modifiersFolder = "tools/modifiers/";
        String upgradeFolder = "tools/modifiers/upgrade/";
        String abilityFolder = "tools/modifiers/ability/";
        String slotlessFolder = "tools/modifiers/slotless/";
        String upgradeSalvage = "tools/modifiers/salvage/upgrade/";
        String abilitySalvage = "tools/modifiers/salvage/ability/";
        String defenseFolder = "tools/modifiers/defense/";
        String defenseSalvage = "tools/modifiers/salvage/defense/";
        String compatFolder = "tools/modifiers/compat/";
        String compatSalvage = "tools/modifiers/salvage/compat/";
        String worktableFolder = "tools/modifiers/worktable/";

        Consumer<FinishedRecipe> whenFENLoaded = withCondition(consumer,
                DefaultResourceConditions.allModsLoaded("fabricaeexnihilo")
        );
        Consumer<FinishedRecipe> whenMythicMetalsLoaded = withCondition(consumer,
                DefaultResourceConditions.allModsLoaded("mythicmetals")
        );
        ModifierRecipeBuilder.modifier(MoarModifierIds.smashing)
                .setTools(ingredientFromTags(TinkerTags.Items.HARVEST_PRIMARY))
                .addInput(ModTags.HAMMERS)
                .setMaxLevel(1)
                .setSlots(SlotType.ABILITY, 1)
                .save(whenFENLoaded, prefix(MoarModifierIds.smashing, abilityFolder));
        ModifierRecipeBuilder.modifier(MoarModifierIds.crooking)
                .setTools(ingredientFromTags(TinkerTags.Items.HARVEST_PRIMARY))
                .addInput(ModTags.CROOKS)
                .setMaxLevel(1)
                .setSlots(SlotType.ABILITY, 1)
                .save(whenFENLoaded, prefix(MoarModifierIds.crooking, abilityFolder));
        ModifierRecipeBuilder.modifier(MoarModifierIds.legendary_banglum)
                .setTools(ingredientFromTags(TinkerTags.Items.HARVEST_PRIMARY))
                .addInput(MythicItems.Mats.BANGLUM_CHUNK)
                .setMaxLevel(1)
                .setSlots(SlotType.ABILITY, 1)
                .save(whenMythicMetalsLoaded, prefix(MoarModifierIds.legendary_banglum, abilityFolder));
        ModifierRecipeBuilder.modifier(MoarModifierIds.carmot_shield)
                .setTools(ingredientFromTags(TinkerTags.Items.ARMOR))
                .addInput(MythicItems.Templates.CARMOT_SMITHING_TEMPLATE)
                .setMaxLevel(1)
                .setSlots(SlotType.ABILITY, 1)
                .save(whenMythicMetalsLoaded, prefix(MoarModifierIds.carmot_shield, abilityFolder));

        ItemCastingRecipeBuilder.tableRecipe(HephPlusItemRegistry.carmotReinforcement)
                .setFluidAndTime(HephPlusFluidRegistry.moltenCarmot, true, FluidValues.NUGGET * 3)
                .setCast(TinkerCommons.obsidianPane, true)
                .save(whenMythicMetalsLoaded, prefix(HephPlusItemRegistry.carmotReinforcement.getRegistryName(), modifiersFolder));

        ModifierRecipeBuilder.modifier(MoarModifierIds.carmot_boost)
                .setTools(ingredientFromTags(TinkerTags.Items.ARMOR))
                .addInput(HephPlusItemRegistry.carmotReinforcement, 20)
                .setRequirements(ModifierMatch.entry(MoarModifierIds.carmot_shield))
                .setRequirementsError(makeRequirementsError("carmot_shield_required"))
                .setMaxLevel(5)
                .setSlots(SlotType.DEFENSE, 1)
                .save(whenMythicMetalsLoaded, prefix(MoarModifierIds.carmot_boost, defenseFolder));

        // Embellishments
        buildEmbellishment(MoarMaterialIds.adamantite   ,"adamantite_ingots"    ,consumer);
        buildEmbellishment(MoarMaterialIds.aquarium     ,"aquarium_ingots"      ,consumer);
        buildEmbellishment(MoarMaterialIds.banglum      ,"banglum_ingots"       ,consumer);
        buildEmbellishment(MoarMaterialIds.carmot       ,"carmot_ingots"        ,consumer);
        buildEmbellishment(MoarMaterialIds.celestium    ,"celestium_ingots"     ,consumer);
        buildEmbellishment(MoarMaterialIds.durasteel    ,"durasteel_ingots"     ,consumer);
        buildEmbellishment(MoarMaterialIds.hallowed     ,"hallowed_ingots"      ,consumer);
        buildEmbellishment(MoarMaterialIds.kyber        ,"kyber_ingots"         ,consumer);
        buildEmbellishment(MoarMaterialIds.metallurgium ,"metallurgium_ingots"  ,consumer);
        buildEmbellishment(MoarMaterialIds.mythril      ,"mythril_ingots"       ,consumer);
        buildEmbellishment(MoarMaterialIds.orichalcum   ,"orichalcum_ingots"    ,consumer);
        buildEmbellishment(MoarMaterialIds.palladium    ,"palladium_ingots"     ,consumer);
        buildEmbellishment(MoarMaterialIds.prometheum   ,"prometheum_ingots"    ,consumer);
        buildEmbellishment(MoarMaterialIds.quadrillum   ,"quadrillum_ingots"    ,consumer);
        buildEmbellishment(MoarMaterialIds.runite       ,"runite_ingots"        ,consumer);
        buildEmbellishment(MoarMaterialIds.star_platinum,"star_platinum_ingots" ,consumer);
        buildEmbellishment(MoarMaterialIds.manasteel    ,"manasteel_ingots"     ,consumer);
        buildEmbellishment(MoarMaterialIds.elementium   ,"elementium_ingots"    ,consumer);
        buildEmbellishment(MoarMaterialIds.terrasteel   ,"terrasteel_ingots"    ,consumer);

        Consumer<FinishedRecipe> whenTRLoaded = withCondition(consumer,
                DefaultResourceConditions.allModsLoaded("techreborn")
        );

        addBattery(MoarModifierIds.red_cell_battery,      TRContent.RED_CELL_BATTERY,    whenTRLoaded, upgradeFolder);
        addBattery(MoarModifierIds.lithium_battery,       TRContent.LITHIUM_ION_BATTERY, whenTRLoaded, upgradeFolder);
        addBattery(MoarModifierIds.energy_crystal,        TRContent.ENERGY_CRYSTAL,      whenTRLoaded, upgradeFolder);
        addBattery(MoarModifierIds.lapotron_crystal,      TRContent.LAPOTRON_CRYSTAL,    whenTRLoaded, upgradeFolder);
        addBattery(MoarModifierIds.lapotronic_energy_orb, TRContent.LAPOTRONIC_ORB,      whenTRLoaded, upgradeFolder);
    }

    @SafeVarargs
    private static Ingredient ingredientFromTags(TagKey<Item>... tags) {
        Ingredient[] tagIngredients = new Ingredient[tags.length];
        for (int i = 0; i < tags.length; i++) {
            tagIngredients[i] = Ingredient.of(tags[i]);
        }
        return DefaultCustomIngredients.any(tagIngredients);
    }

    private static String makeRequirementsError(String recipe) {
        return HephaestusPlus.makeTranslationKey("recipe", "modifier." + recipe);
    }

    private void buildEmbellishment(MaterialVariantId material, String tag, Consumer<FinishedRecipe> consumer) {
        Ingredient ingot = Ingredient.of(getItemTag("c", tag));
        consumer = withCondition(consumer, tagCondition(tag));
        SwappableModifierRecipeBuilder.modifier(TinkerModifiers.embellishment, material.toString())
                .setTools(TinkerTags.Items.EMBELLISHMENT_METAL)
                .addInput(ingot).addInput(ingot).addInput(ingot)
                .save(consumer, wrap(TinkerModifiers.embellishment.getId(), "tools/modifiers/slotless/", "_" + material.getLocation('_').getPath()));
    }

    private void addBattery(ModifierId modifier, Item item, Consumer<FinishedRecipe> condition, String folder){
        ModifierRecipeBuilder.modifier(modifier)
                .setTools(ingredientFromTags(TinkerTags.Items.MODIFIABLE))
                .addInput(item)
                .setSlots(SlotType.UPGRADE, 1)
                .save(condition, prefix(modifier, folder));
    }

}
