package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.HephPlusRegistry;
import konsola5.hephaestusplus.ids.MoarMaterialIds;
import konsola5.hephaestusplus.recipecompat.HephPlusSmelteryCompat;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.MythicItems;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import vazkii.botania.common.item.BotaniaItems;

import java.util.function.Consumer;

public class HephPlusMaterialRecipeProvider extends BaseRecipeProvider implements IMaterialRecipeHelper, ISmelteryRecipeHelper {

    public HephPlusMaterialRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";
        String metalFolder = folder + "metal/";
        String alloyFolder = "smeltery/alloys/";
        // This generates .json necessary for repairing custom materials
        // Mythic Metals
        metalMaterialRecipe(consumer, MoarMaterialIds.adamantite, folder, "adamantite", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.aquarium, folder, "aquarium", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.banglum, folder, "banglum", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.carmot, folder, "carmot", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.celestium, folder, "celestium", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.durasteel, folder, "durasteel", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.hallowed, folder, "hallowed", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.kyber, folder, "kyber", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.metallurgium, folder, "metallurgium", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.mythril, folder, "mythril", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.orichalcum, folder, "orichalcum", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.palladium, folder, "palladium", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.prometheum, folder, "prometheum", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.quadrillum, folder, "quadrillum", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.runite, folder, "runite", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.star_platinum, folder, "star_platinum", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.stormyx, folder, "stormyx", true);

        compatMeltingCasting(consumer, MoarMaterialIds.adamantite, HephPlusRegistry.moltenAdamantite, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.aquarium, HephPlusRegistry.moltenAquarium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.banglum, HephPlusRegistry.moltenBanglum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.carmot, HephPlusRegistry.moltenCarmot, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.celestium, HephPlusRegistry.moltenCelestium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.durasteel, HephPlusRegistry.moltenDurasteel, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.hallowed, HephPlusRegistry.moltenHallowed, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.kyber, HephPlusRegistry.moltenKyber, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.metallurgium, HephPlusRegistry.moltenMetallurgium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.mythril, HephPlusRegistry.moltenMythril, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.orichalcum, HephPlusRegistry.moltenOrichalcum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.palladium, HephPlusRegistry.moltenPalladium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.prometheum, HephPlusRegistry.moltenPrometheum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.quadrillum, HephPlusRegistry.moltenQuadrillum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.runite, HephPlusRegistry.moltenRunite, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.star_platinum, HephPlusRegistry.moltenStarPlatinum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.stormyx, HephPlusRegistry.moltenStormyx, folder);

        // Botania

        metalMaterialRecipe(consumer, MoarMaterialIds.manasteel, folder, "manasteel", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.elementium, folder, "elementium", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.terrasteel, folder, "terrasteel", true);

        materialRecipe(consumer, MoarMaterialIds.manastring, Ingredient.of(BotaniaItems.manaString), 1, 4, folder + "string");

        compatMeltingCasting(consumer, MoarMaterialIds.manasteel, HephPlusRegistry.moltenManasteel, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.elementium, HephPlusRegistry.moltenElementium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.terrasteel, HephPlusRegistry.moltenTerrasteel, folder);

        this.gemCasting(consumer, HephPlusRegistry.moltenStarrite, MythicItems.Mats.STARRITE, folder + "starrite/gem");
        this.gemMelting(consumer, HephPlusRegistry.moltenStarrite.get(), "starrite", true, 9,folder + "starrite/gem", true);
        ItemCastingRecipeBuilder.basinRecipe(MythicBlocks.STARRITE.getStorageBlock())
                .setFluidAndTime(HephPlusRegistry.moltenStarrite, false, FluidValues.LARGE_GEM_BLOCK)
                .save(consumer, modResource(folder + "starrite/block"));


        for (HephPlusSmelteryCompat compat : HephPlusSmelteryCompat.values()) {
            this.metalMelting(consumer, compat.getFluid().get(), compat.getName(), compat.isOre(), compat.hasDust(), metalFolder, true, compat.getByproducts());
            this.metalTagCasting(consumer, compat.getFluid(), compat.getName(), metalFolder, false);
        }

        Consumer<FinishedRecipe> wrapped;

        wrapped = withCondition(consumer,
                tagCondition("adamantite_ingots"),
                tagCondition("mythril_ingots"),
                tagCondition("orichalcum_ingots"),
                tagCondition("hallowed_ingots"));
        AlloyRecipeBuilder.alloy(HephPlusRegistry.moltenHallowed.get(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenAdamantite.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenMythril.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenOrichalcum.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusRegistry.moltenHallowed.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("hallowed_ingots"),
                tagCondition("palladium_ingots"),
                tagCondition("unobtainium"),
                tagCondition("metallurgium_ingots"));
        AlloyRecipeBuilder.alloy(HephPlusRegistry.moltenMetallurgium.get(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenHallowed.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenPalladium.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenUnobtainium.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusRegistry.moltenMetallurgium.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("manganese_ingots"),
                tagCondition("steel_ingots"));
        AlloyRecipeBuilder.alloy(TinkerFluids.moltenSteel.get(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenManganese.getForgeTag(), FluidValues.INGOT)
                .addInput(TinkerFluids.moltenIron.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(TinkerFluids.moltenSteel.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("manganese_ingots"),
                tagCondition("quadrillum_ingots"),
                tagCondition("durasteel_ingots"));
        AlloyRecipeBuilder.alloy(HephPlusRegistry.moltenDurasteel.get(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenManganese.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenQuadrillum.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusRegistry.moltenDurasteel.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("starrite"),
                tagCondition("platinum_ingots"),
                tagCondition("star_platinum"));
        AlloyRecipeBuilder.alloy(HephPlusRegistry.moltenStarPlatinum.get(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenStarrite.getForgeTag(), FluidValues.GEM)
                .addInput(TinkerFluids.moltenPlatinum.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusRegistry.moltenStarPlatinum.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("star_platinum"),
                tagCondition("kyber_ingots"),
                tagCondition("carmot_ingots"),
                tagCondition("unobtainium"),
                tagCondition("celestium_ingots"));
        AlloyRecipeBuilder.alloy(HephPlusRegistry.moltenCelestium.get(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenStarPlatinum.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenKyber.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenCarmot.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusRegistry.moltenUnobtainium.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusRegistry.moltenCelestium.get()), alloyFolder));

    }

    @Override
    public String getName() {
        return "HephaestusPlus Material Recipes";
    }
}
