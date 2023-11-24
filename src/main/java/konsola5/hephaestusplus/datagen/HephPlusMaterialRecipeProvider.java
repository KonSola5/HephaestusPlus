package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.Registry;
import konsola5.hephaestusplus.ids.MoarMaterialIds;
import konsola5.hephaestusplus.recipecompat.HephPlusSmelteryCompat;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.item.tools.MythicToolMaterials;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;

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

        compatMeltingCasting(consumer, MoarMaterialIds.adamantite, Registry.moltenAdamantite, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.aquarium, Registry.moltenAquarium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.banglum, Registry.moltenBanglum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.carmot, Registry.moltenCarmot, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.celestium, Registry.moltenCelestium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.durasteel, Registry.moltenDurasteel, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.hallowed, Registry.moltenHallowed, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.kyber, Registry.moltenKyber, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.metallurgium, Registry.moltenMetallurgium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.mythril, Registry.moltenMythril, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.orichalcum, Registry.moltenOrichalcum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.palladium, Registry.moltenPalladium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.prometheum, Registry.moltenPrometheum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.quadrillum, Registry.moltenQuadrillum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.runite, Registry.moltenRunite, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.star_platinum, Registry.moltenStarPlatinum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.stormyx, Registry.moltenStormyx, folder);

        this.gemCasting(consumer, Registry.moltenStarrite, MythicItems.Mats.STARRITE, folder + "starrite/gem");
        this.gemMelting(consumer, Registry.moltenStarrite.get(), "starrite", true, 9,folder + "starrite/gem", true);
        ItemCastingRecipeBuilder.basinRecipe(MythicBlocks.STARRITE.getStorageBlock())
                .setFluidAndTime(Registry.moltenStarrite, false, FluidValues.LARGE_GEM_BLOCK)
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
        AlloyRecipeBuilder.alloy(Registry.moltenHallowed.get(), FluidValues.INGOT)
                .addInput(Registry.moltenAdamantite.getForgeTag(), FluidValues.INGOT)
                .addInput(Registry.moltenMythril.getForgeTag(), FluidValues.INGOT)
                .addInput(Registry.moltenOrichalcum.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(Registry.moltenHallowed.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("hallowed_ingots"),
                tagCondition("palladium_ingots"),
                tagCondition("unobtainium"),
                tagCondition("metallurgium_ingots"));
        AlloyRecipeBuilder.alloy(Registry.moltenMetallurgium.get(), FluidValues.INGOT)
                .addInput(Registry.moltenHallowed.getForgeTag(), FluidValues.INGOT)
                .addInput(Registry.moltenPalladium.getForgeTag(), FluidValues.INGOT)
                .addInput(Registry.moltenUnobtainium.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(Registry.moltenMetallurgium.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("manganese_ingots"),
                tagCondition("steel_ingots"));
        AlloyRecipeBuilder.alloy(TinkerFluids.moltenSteel.get(), FluidValues.INGOT)
                .addInput(Registry.moltenManganese.getForgeTag(), FluidValues.INGOT)
                .addInput(TinkerFluids.moltenIron.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(TinkerFluids.moltenSteel.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("manganese_ingots"),
                tagCondition("quadrillum_ingots"),
                tagCondition("durasteel_ingots"));
        AlloyRecipeBuilder.alloy(Registry.moltenDurasteel.get(), FluidValues.INGOT)
                .addInput(Registry.moltenManganese.getForgeTag(), FluidValues.INGOT)
                .addInput(Registry.moltenQuadrillum.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(Registry.moltenDurasteel.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("starrite"),
                tagCondition("platinum_ingots"),
                tagCondition("star_platinum"));
        AlloyRecipeBuilder.alloy(Registry.moltenStarPlatinum.get(), FluidValues.INGOT)
                .addInput(Registry.moltenStarrite.getForgeTag(), FluidValues.GEM)
                .addInput(TinkerFluids.moltenPlatinum.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(Registry.moltenStarPlatinum.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("star_platinum"),
                tagCondition("kyber_ingots"),
                tagCondition("carmot_ingots"),
                tagCondition("unobtainium"),
                tagCondition("celestium_ingots"));
        AlloyRecipeBuilder.alloy(Registry.moltenCelestium.get(), FluidValues.INGOT)
                .addInput(Registry.moltenStarPlatinum.getForgeTag(), FluidValues.INGOT)
                .addInput(Registry.moltenKyber.getForgeTag(), FluidValues.INGOT)
                .addInput(Registry.moltenCarmot.getForgeTag(), FluidValues.INGOT)
                .addInput(Registry.moltenUnobtainium.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(Registry.moltenCelestium.get()), alloyFolder));


    }

    @Override
    public String getName() {
        return "HephaestusPlus Material Recipes";
    }
}
