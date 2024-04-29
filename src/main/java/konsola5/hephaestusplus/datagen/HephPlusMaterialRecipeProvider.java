package konsola5.hephaestusplus.datagen;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import konsola5.hephaestusplus.ids.MoarMaterialIds;
import konsola5.hephaestusplus.recipecompat.HephPlusSmelteryCompat;
import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.item.tools.MythicTools;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
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
        metalMaterialRecipe(consumer, MoarMaterialIds.adamantite,    folder, "adamantite",    true);
        metalMaterialRecipe(consumer, MoarMaterialIds.aquarium,      folder, "aquarium",      true);
        metalMaterialRecipe(consumer, MoarMaterialIds.banglum,       folder, "banglum",       true);
        metalMaterialRecipe(consumer, MoarMaterialIds.carmot,        folder, "carmot",        true);
        metalMaterialRecipe(consumer, MoarMaterialIds.celestium,     folder, "celestium",     true);
        metalMaterialRecipe(consumer, MoarMaterialIds.durasteel,     folder, "durasteel",     true);
        metalMaterialRecipe(consumer, MoarMaterialIds.hallowed,      folder, "hallowed",      true);
        metalMaterialRecipe(consumer, MoarMaterialIds.kyber,         folder, "kyber",         true);
        metalMaterialRecipe(consumer, MoarMaterialIds.metallurgium,  folder, "metallurgium",  true);
        metalMaterialRecipe(consumer, MoarMaterialIds.mythril,       folder, "mythril",       true);
        metalMaterialRecipe(consumer, MoarMaterialIds.orichalcum,    folder, "orichalcum",    true);
        metalMaterialRecipe(consumer, MoarMaterialIds.palladium,     folder, "palladium",     true);
        metalMaterialRecipe(consumer, MoarMaterialIds.prometheum,    folder, "prometheum",    true);
        metalMaterialRecipe(consumer, MoarMaterialIds.quadrillum,    folder, "quadrillum",    true);
        metalMaterialRecipe(consumer, MoarMaterialIds.runite,        folder, "runite",        true);
        metalMaterialRecipe(consumer, MoarMaterialIds.star_platinum, folder, "star_platinum", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.stormyx,       folder, "stormyx",       true);

        compatMeltingCasting(consumer, MoarMaterialIds.adamantite,    HephPlusFluidRegistry.moltenAdamantite,   folder);
        compatMeltingCasting(consumer, MoarMaterialIds.aquarium,      HephPlusFluidRegistry.moltenAquarium,     folder);
        compatMeltingCasting(consumer, MoarMaterialIds.banglum,       HephPlusFluidRegistry.moltenBanglum,      folder);
        compatMeltingCasting(consumer, MoarMaterialIds.carmot,        HephPlusFluidRegistry.moltenCarmot,       folder);
        compatMeltingCasting(consumer, MoarMaterialIds.celestium,     HephPlusFluidRegistry.moltenCelestium,    folder);
        compatMeltingCasting(consumer, MoarMaterialIds.durasteel,     HephPlusFluidRegistry.moltenDurasteel,    folder);
        compatMeltingCasting(consumer, MoarMaterialIds.hallowed,      HephPlusFluidRegistry.moltenHallowed,     folder);
        compatMeltingCasting(consumer, MoarMaterialIds.kyber,         HephPlusFluidRegistry.moltenKyber,        folder);
        compatMeltingCasting(consumer, MoarMaterialIds.metallurgium,  HephPlusFluidRegistry.moltenMetallurgium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.mythril,       HephPlusFluidRegistry.moltenMythril,      folder);
        compatMeltingCasting(consumer, MoarMaterialIds.orichalcum,    HephPlusFluidRegistry.moltenOrichalcum,   folder);
        compatMeltingCasting(consumer, MoarMaterialIds.palladium,     HephPlusFluidRegistry.moltenPalladium,    folder);
        compatMeltingCasting(consumer, MoarMaterialIds.prometheum,    HephPlusFluidRegistry.moltenPrometheum,   folder);
        compatMeltingCasting(consumer, MoarMaterialIds.quadrillum,    HephPlusFluidRegistry.moltenQuadrillum,   folder);
        compatMeltingCasting(consumer, MoarMaterialIds.runite,        HephPlusFluidRegistry.moltenRunite,       folder);
        compatMeltingCasting(consumer, MoarMaterialIds.star_platinum, HephPlusFluidRegistry.moltenStarPlatinum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.stormyx,       HephPlusFluidRegistry.moltenStormyx,      folder);

        // Botania

        metalMaterialRecipe(consumer, MoarMaterialIds.manasteel,  folder, "manasteel",  true);
        metalMaterialRecipe(consumer, MoarMaterialIds.elementium, folder, "elementium", true);
        metalMaterialRecipe(consumer, MoarMaterialIds.terrasteel, folder, "terrasteel", true);

        materialRecipe(consumer, MoarMaterialIds.manastring, Ingredient.of(BotaniaItems.manaString), 1, 4, folder + "string");

        compatMeltingCasting(consumer, MoarMaterialIds.manasteel,  HephPlusFluidRegistry.moltenManasteel,  folder);
        compatMeltingCasting(consumer, MoarMaterialIds.elementium, HephPlusFluidRegistry.moltenElementium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.terrasteel, HephPlusFluidRegistry.moltenTerrasteel, folder);

        // Spectrum

        // WIP

        this.gemCasting(consumer, HephPlusFluidRegistry.moltenStarrite, MythicItems.Mats.STARRITE, folder + "starrite/gem");
        this.gemMelting(consumer, HephPlusFluidRegistry.moltenStarrite.get(), "starrite", true, 9, folder + "starrite/gem", true);
        ItemCastingRecipeBuilder.basinRecipe(MythicBlocks.STARRITE.getStorageBlock())
                .setFluidAndTime(HephPlusFluidRegistry.moltenStarrite, false, FluidValues.LARGE_GEM_BLOCK)
                .save(consumer, modResource(folder + "starrite/block"));


        for (HephPlusSmelteryCompat compat : HephPlusSmelteryCompat.values()) {
            this.metalMelting(consumer, compat.getFluid().get(), compat.getName(), compat.isOre(), compat.hasDust(), metalFolder, true, compat.getByproducts());
            this.metalTagCasting(consumer, compat.getFluid(), compat.getName(), metalFolder, false);
        }


    }

    @Override
    public String getName() {
        return "HephaestusPlus Material Recipes";
    }
}
