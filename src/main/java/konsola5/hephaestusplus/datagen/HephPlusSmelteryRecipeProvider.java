package konsola5.hephaestusplus.datagen;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import konsola5.hephaestusplus.registry.HephPlusItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.item.tools.MythicTools;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;

import java.util.function.Consumer;

public class HephPlusSmelteryRecipeProvider extends BaseRecipeProvider implements ISmelteryRecipeHelper {

    public HephPlusSmelteryRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addCastingRecipes(consumer);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Smeltery Recipes";
    }

    private void addCastingRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/casting/";
        String metalFolder = folder + "metal/";
        String alloyFolder = "smeltery/alloys/";

        MeltingRecipeBuilder.melting(Ingredient.of(HephPlusItemRegistry.carmotReinforcement), HephPlusFluidRegistry.moltenCarmot.get(), FluidValues.NUGGET * 3)
                .addByproduct(new FluidStack(TinkerFluids.moltenObsidian.get(), FluidValues.GLASS_PANE))
                .save(consumer, modResource(folder + "carmot/reinforcement"));

        // Mythic Metals alloying

        Consumer<FinishedRecipe> wrapped;

        wrapped = withCondition(consumer,
                tagCondition("adamantite_ingots"),
                tagCondition("mythril_ingots"),
                tagCondition("orichalcum_ingots"),
                tagCondition("hallowed_ingots"));
        AlloyRecipeBuilder.alloy(HephPlusFluidRegistry.moltenHallowed.get(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenAdamantite.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenMythril.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenOrichalcum.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusFluidRegistry.moltenHallowed.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("hallowed_ingots"),
                tagCondition("palladium_ingots"),
                tagCondition("unobtainium"),
                tagCondition("metallurgium_ingots"));
        AlloyRecipeBuilder.alloy(HephPlusFluidRegistry.moltenMetallurgium.get(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenHallowed.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenPalladium.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenUnobtainium.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusFluidRegistry.moltenMetallurgium.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("manganese_ingots"),
                tagCondition("steel_ingots"));
        AlloyRecipeBuilder.alloy(TinkerFluids.moltenSteel.get(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenManganese.getForgeTag(), FluidValues.INGOT)
                .addInput(TinkerFluids.moltenIron.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(TinkerFluids.moltenSteel.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("manganese_ingots"),
                tagCondition("quadrillum_ingots"),
                tagCondition("durasteel_ingots"));
        AlloyRecipeBuilder.alloy(HephPlusFluidRegistry.moltenDurasteel.get(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenManganese.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenQuadrillum.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusFluidRegistry.moltenDurasteel.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("starrite"),
                tagCondition("platinum_ingots"),
                tagCondition("star_platinum"));
        AlloyRecipeBuilder.alloy(HephPlusFluidRegistry.moltenStarPlatinum.get(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenStarrite.getForgeTag(), FluidValues.GEM)
                .addInput(TinkerFluids.moltenPlatinum.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusFluidRegistry.moltenStarPlatinum.get()), alloyFolder));

        wrapped = withCondition(consumer,
                tagCondition("star_platinum"),
                tagCondition("kyber_ingots"),
                tagCondition("carmot_ingots"),
                tagCondition("unobtainium"),
                tagCondition("celestium_ingots"));
        AlloyRecipeBuilder.alloy(HephPlusFluidRegistry.moltenCelestium.get(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenStarPlatinum.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenKyber.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenCarmot.getForgeTag(), FluidValues.INGOT)
                .addInput(HephPlusFluidRegistry.moltenUnobtainium.getForgeTag(), FluidValues.INGOT)
                .save(wrapped, prefix(BuiltInRegistries.FLUID.getKey(HephPlusFluidRegistry.moltenCelestium.get()), alloyFolder));

        // Melting Mythic Metals tools and armor - non-upgrade tools/armor

        Consumer<FinishedRecipe> whenMythicMetalsLoaded = withCondition(consumer,
                DefaultResourceConditions.allModsLoaded("mythicmetals")
        );

        // Adamantite

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.ADAMANTITE.getPickaxe(), MythicTools.ADAMANTITE.getAxe()),
                        HephPlusFluidRegistry.moltenAdamantite.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "adamantite/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.ADAMANTITE.getSword(), MythicTools.ADAMANTITE.getHoe()),
                        HephPlusFluidRegistry.moltenAdamantite.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "adamantite/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.ADAMANTITE.getShovel()),
                        HephPlusFluidRegistry.moltenAdamantite.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "adamantite/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.ADAMANTITE.getHelmet()),
                        HephPlusFluidRegistry.moltenAdamantite.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "adamantite/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.ADAMANTITE.getChestplate()),
                        HephPlusFluidRegistry.moltenAdamantite.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "adamantite/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.ADAMANTITE.getLeggings()),
                        HephPlusFluidRegistry.moltenAdamantite.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "adamantite/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.ADAMANTITE.getBoots()),
                        HephPlusFluidRegistry.moltenAdamantite.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "adamantite/boots"));

        // Aquarium

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.AQUARIUM.getPickaxe(), MythicTools.AQUARIUM.getAxe()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "aquarium/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.AQUARIUM.getSword(), MythicTools.AQUARIUM.getHoe()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "aquarium/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.AQUARIUM.getShovel()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "aquarium/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.AQUARIUM.getHelmet()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "aquarium/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.AQUARIUM.getChestplate()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "aquarium/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.AQUARIUM.getLeggings()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "aquarium/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.AQUARIUM.getBoots()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "aquarium/boots"));

        // Banglum

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.BANGLUM.getPickaxe(), MythicTools.BANGLUM.getAxe()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "banglum/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.BANGLUM.getSword(), MythicTools.BANGLUM.getHoe()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "banglum/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.BANGLUM.getShovel()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "banglum/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.BANGLUM.getHelmet()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "banglum/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.BANGLUM.getChestplate()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "banglum/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.BANGLUM.getLeggings()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "banglum/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.BANGLUM.getBoots()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "banglum/boots"));

        // Bronze

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.BRONZE.getPickaxe(), MythicTools.BRONZE.getAxe()),
                        TinkerFluids.moltenBronze.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "bronze/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.BRONZE.getSword(), MythicTools.BRONZE.getHoe()),
                        TinkerFluids.moltenBronze.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "bronze/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.BRONZE.getShovel()),
                        TinkerFluids.moltenBronze.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "bronze/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.BRONZE.getHelmet()),
                        TinkerFluids.moltenBronze.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "bronze/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.BRONZE.getChestplate()),
                        TinkerFluids.moltenBronze.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "bronze/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.BRONZE.getLeggings()),
                        TinkerFluids.moltenBronze.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "bronze/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.BRONZE.getBoots()),
                        TinkerFluids.moltenBronze.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "bronze/boots"));

        // Copper

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.COPPER.getPickaxe(), MythicTools.COPPER.getAxe()),
                        TinkerFluids.moltenCopper.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "copper/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.COPPER.getSword(), MythicTools.COPPER.getHoe()),
                        TinkerFluids.moltenCopper.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "copper/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.COPPER.getShovel()),
                        TinkerFluids.moltenCopper.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "copper/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.COPPER.getHelmet()),
                        TinkerFluids.moltenCopper.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "copper/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.COPPER.getChestplate()),
                        TinkerFluids.moltenCopper.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "copper/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.COPPER.getLeggings()),
                        TinkerFluids.moltenCopper.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "copper/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.COPPER.getBoots()),
                        TinkerFluids.moltenCopper.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "copper/boots"));

        // Durasteel

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.DURASTEEL.getPickaxe(), MythicTools.DURASTEEL.getAxe()),
                        HephPlusFluidRegistry.moltenDurasteel.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "durasteel/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.DURASTEEL.getSword(), MythicTools.DURASTEEL.getHoe()),
                        HephPlusFluidRegistry.moltenDurasteel.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "durasteel/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.DURASTEEL.getShovel()),
                        HephPlusFluidRegistry.moltenDurasteel.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "durasteel/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.DURASTEEL.getHelmet()),
                        HephPlusFluidRegistry.moltenDurasteel.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "durasteel/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.DURASTEEL.getChestplate()),
                        HephPlusFluidRegistry.moltenDurasteel.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "durasteel/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.DURASTEEL.getLeggings()),
                        HephPlusFluidRegistry.moltenDurasteel.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "durasteel/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.DURASTEEL.getBoots()),
                        HephPlusFluidRegistry.moltenDurasteel.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "durasteel/boots"));


        // Hallowed

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.HALLOWED.getPickaxe(), MythicTools.HALLOWED.getAxe()),
                        HephPlusFluidRegistry.moltenHallowed.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "hallowed/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.HALLOWED.getSword(), MythicTools.HALLOWED.getHoe()),
                        HephPlusFluidRegistry.moltenHallowed.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "hallowed/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.HALLOWED.getShovel()),
                        HephPlusFluidRegistry.moltenHallowed.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "hallowed/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.HALLOWED.getHelmet()),
                        HephPlusFluidRegistry.moltenHallowed.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "hallowed/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.HALLOWED.getChestplate()),
                        HephPlusFluidRegistry.moltenHallowed.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "hallowed/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.HALLOWED.getLeggings()),
                        HephPlusFluidRegistry.moltenHallowed.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "hallowed/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.HALLOWED.getBoots()),
                        HephPlusFluidRegistry.moltenHallowed.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "hallowed/boots"));

        // Kyber

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.KYBER.getPickaxe(), MythicTools.KYBER.getAxe()),
                        HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "kyber/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.KYBER.getSword(), MythicTools.KYBER.getHoe()),
                        HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "kyber/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.KYBER.getShovel()),
                        HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "kyber/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.KYBER.getHelmet()),
                        HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "kyber/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.KYBER.getChestplate()),
                        HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "kyber/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.KYBER.getLeggings()),
                        HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "kyber/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.KYBER.getBoots()),
                        HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "kyber/boots"));

        // Mythril

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.MYTHRIL.getPickaxe(), MythicTools.MYTHRIL.getAxe()),
                        HephPlusFluidRegistry.moltenMythril.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "mythril/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.MYTHRIL.getSword(), MythicTools.MYTHRIL.getHoe()),
                        HephPlusFluidRegistry.moltenMythril.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "mythril/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.MYTHRIL.getShovel()),
                        HephPlusFluidRegistry.moltenMythril.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "mythril/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.MYTHRIL.getHelmet()),
                        HephPlusFluidRegistry.moltenMythril.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "mythril/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.MYTHRIL.getChestplate()),
                        HephPlusFluidRegistry.moltenMythril.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "mythril/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.MYTHRIL.getLeggings()),
                        HephPlusFluidRegistry.moltenMythril.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "mythril/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.MYTHRIL.getBoots()),
                        HephPlusFluidRegistry.moltenMythril.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "mythril/boots"));

        // Orichalcum

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.ORICHALCUM.getPickaxe(), MythicTools.ORICHALCUM.getAxe()),
                        HephPlusFluidRegistry.moltenOrichalcum.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "orichalcum/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.ORICHALCUM.getSword(), MythicTools.ORICHALCUM.getHoe()),
                        HephPlusFluidRegistry.moltenOrichalcum.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "orichalcum/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.ORICHALCUM.getShovel()),
                        HephPlusFluidRegistry.moltenOrichalcum.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "orichalcum/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.ORICHALCUM.getHelmet()),
                        HephPlusFluidRegistry.moltenOrichalcum.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "orichalcum/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.ORICHALCUM.getChestplate()),
                        HephPlusFluidRegistry.moltenOrichalcum.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "orichalcum/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.ORICHALCUM.getLeggings()),
                        HephPlusFluidRegistry.moltenOrichalcum.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "orichalcum/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.ORICHALCUM.getBoots()),
                        HephPlusFluidRegistry.moltenOrichalcum.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "orichalcum/boots"));

        // Osmium

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.OSMIUM.getPickaxe(), MythicTools.OSMIUM.getAxe()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "osmium/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.OSMIUM.getSword(), MythicTools.OSMIUM.getHoe()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "osmium/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.OSMIUM.getShovel()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "osmium/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.OSMIUM.getHelmet()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "osmium/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.OSMIUM.getChestplate()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "osmium/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.OSMIUM.getLeggings()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "osmium/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.OSMIUM.getBoots()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "osmium/boots"));

        // Palladium

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.PALLADIUM.getPickaxe(), MythicTools.PALLADIUM.getAxe()),
                        HephPlusFluidRegistry.moltenPalladium.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "palladium/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.PALLADIUM.getSword(), MythicTools.PALLADIUM.getHoe()),
                        HephPlusFluidRegistry.moltenPalladium.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "palladium/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.PALLADIUM.getShovel()),
                        HephPlusFluidRegistry.moltenPalladium.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "palladium/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.PALLADIUM.getHelmet()),
                        HephPlusFluidRegistry.moltenPalladium.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "palladium/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.PALLADIUM.getChestplate()),
                        HephPlusFluidRegistry.moltenPalladium.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "palladium/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.PALLADIUM.getLeggings()),
                        HephPlusFluidRegistry.moltenPalladium.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "palladium/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.PALLADIUM.getBoots()),
                        HephPlusFluidRegistry.moltenPalladium.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "palladium/boots"));

        // Prometheum

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.PROMETHEUM.getPickaxe(), MythicTools.PROMETHEUM.getAxe()),
                        HephPlusFluidRegistry.moltenPrometheum.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "prometheum/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.PROMETHEUM.getSword(), MythicTools.PROMETHEUM.getHoe()),
                        HephPlusFluidRegistry.moltenPrometheum.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "prometheum/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.PROMETHEUM.getShovel()),
                        HephPlusFluidRegistry.moltenPrometheum.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "prometheum/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.PROMETHEUM.getHelmet()),
                        HephPlusFluidRegistry.moltenPrometheum.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "prometheum/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.PROMETHEUM.getChestplate()),
                        HephPlusFluidRegistry.moltenPrometheum.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "prometheum/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.PROMETHEUM.getLeggings()),
                        HephPlusFluidRegistry.moltenPrometheum.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "prometheum/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.PROMETHEUM.getBoots()),
                        HephPlusFluidRegistry.moltenPrometheum.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "prometheum/boots"));

        // Runite

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.RUNITE.getPickaxe(), MythicTools.RUNITE.getAxe()),
                        HephPlusFluidRegistry.moltenRunite.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "runite/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.RUNITE.getSword(), MythicTools.RUNITE.getHoe()),
                        HephPlusFluidRegistry.moltenRunite.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "runite/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.RUNITE.getShovel()),
                        HephPlusFluidRegistry.moltenRunite.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "runite/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.RUNITE.getHelmet()),
                        HephPlusFluidRegistry.moltenRunite.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "runite/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.RUNITE.getChestplate()),
                        HephPlusFluidRegistry.moltenRunite.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "runite/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.RUNITE.getLeggings()),
                        HephPlusFluidRegistry.moltenRunite.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "runite/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.RUNITE.getBoots()),
                        HephPlusFluidRegistry.moltenRunite.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "runite/boots"));

        // Silver

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.SILVER.getHelmet()),
                        TinkerFluids.moltenSilver.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "silver/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.SILVER.getChestplate()),
                        TinkerFluids.moltenSilver.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "silver/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.SILVER.getLeggings()),
                        TinkerFluids.moltenSilver.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "silver/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.SILVER.getBoots()),
                        TinkerFluids.moltenSilver.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "silver/boots"));

        // Star Platinum

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.STAR_PLATINUM.getPickaxe(), MythicTools.STAR_PLATINUM.getAxe()),
                        HephPlusFluidRegistry.moltenStarPlatinum.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "star_platinum/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.STAR_PLATINUM.getSword(), MythicTools.STAR_PLATINUM.getHoe()),
                        HephPlusFluidRegistry.moltenStarPlatinum.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "star_platinum/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.STAR_PLATINUM.getShovel()),
                        HephPlusFluidRegistry.moltenStarPlatinum.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "star_platinum/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STAR_PLATINUM.getHelmet()),
                        HephPlusFluidRegistry.moltenStarPlatinum.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "star_platinum/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STAR_PLATINUM.getChestplate()),
                        HephPlusFluidRegistry.moltenStarPlatinum.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "star_platinum/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STAR_PLATINUM.getLeggings()),
                        HephPlusFluidRegistry.moltenStarPlatinum.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "star_platinum/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STAR_PLATINUM.getBoots()),
                        HephPlusFluidRegistry.moltenStarPlatinum.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "star_platinum/boots"));

        // Steel

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.STEEL.getPickaxe(), MythicTools.STEEL.getAxe()),
                        TinkerFluids.moltenSteel.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "steel/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.STEEL.getSword(), MythicTools.STEEL.getHoe()),
                        TinkerFluids.moltenSteel.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "steel/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.STEEL.getShovel()),
                        TinkerFluids.moltenSteel.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "steel/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STEEL.getHelmet()),
                        TinkerFluids.moltenSteel.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "steel/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STEEL.getChestplate()),
                        TinkerFluids.moltenSteel.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "steel/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STEEL.getLeggings()),
                        TinkerFluids.moltenSteel.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "steel/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STEEL.getBoots()),
                        TinkerFluids.moltenSteel.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "steel/boots"));

        // Stormyx

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.STORMYX.getPickaxe(), MythicTools.STORMYX.getAxe()),
                        HephPlusFluidRegistry.moltenStormyx.get(), FluidValues.INGOT * 3)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "stormyx/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.STORMYX.getSword(), MythicTools.STORMYX.getHoe()),
                        HephPlusFluidRegistry.moltenStormyx.get(), FluidValues.INGOT * 2)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "stormyx/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.STORMYX.getShovel()),
                        HephPlusFluidRegistry.moltenStormyx.get(), FluidValues.INGOT)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "stormyx/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STORMYX.getHelmet()),
                        HephPlusFluidRegistry.moltenStormyx.get(), FluidValues.INGOT * 5)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "stormyx/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STORMYX.getChestplate()),
                        HephPlusFluidRegistry.moltenStormyx.get(), FluidValues.INGOT * 8)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "stormyx/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STORMYX.getLeggings()),
                        HephPlusFluidRegistry.moltenStormyx.get(), FluidValues.INGOT * 7)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "stormyx/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.STORMYX.getBoots()),
                        HephPlusFluidRegistry.moltenStormyx.get(), FluidValues.INGOT * 4)
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "stormyx/boots"));

        // Melting Mythic Metals tools and armor - upgrade tools/armor

        // Carmot

        // Takes the cheaper upgrade template material cost into consideration

        long[] upgrade1IngotGemSizes = {FluidValues.NUGGET, FluidValues.GEM_SHARD};
        long[] upgrade2IngotGemSizes = {FluidValues.NUGGET, FluidValues.NUGGET, FluidValues.GEM_SHARD};
        long[] upgrade2IngotSizes = {FluidValues.NUGGET, FluidValues.NUGGET};

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.CARMOT.getPickaxe(), MythicTools.CARMOT.getAxe()),
                        HephPlusFluidRegistry.moltenCarmot.get(), FluidValues.NUGGET * 15)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 3))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM))
                .save(whenMythicMetalsLoaded, modResource(folder + "carmot/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.CARMOT.getSword(), MythicTools.CARMOT.getHoe()),
                        HephPlusFluidRegistry.moltenCarmot.get(), FluidValues.NUGGET * 15)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 2))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM))
                .save(whenMythicMetalsLoaded, modResource(folder + "carmot/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.CARMOT.getShovel()),
                        HephPlusFluidRegistry.moltenCarmot.get(), FluidValues.NUGGET * 15)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM))
                .save(whenMythicMetalsLoaded, modResource(folder + "carmot/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.CARMOT.getHelmet()),
                        HephPlusFluidRegistry.moltenCarmot.get(), FluidValues.NUGGET * 15)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 5))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "carmot/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.CARMOT.getChestplate()),
                        HephPlusFluidRegistry.moltenCarmot.get(), FluidValues.NUGGET * 15)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 8))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "carmot/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.CARMOT.getLeggings()),
                        HephPlusFluidRegistry.moltenCarmot.get(), FluidValues.NUGGET * 15)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 7))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "carmot/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.CARMOT.getBoots()),
                        HephPlusFluidRegistry.moltenCarmot.get(), FluidValues.NUGGET * 15)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(HephPlusFluidRegistry.moltenKyber.get(), FluidValues.INGOT * 4))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "carmot/boots"));

        // Celestium

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.CELESTIUM.getPickaxe(), MythicTools.CELESTIUM.getAxe()),
                        HephPlusFluidRegistry.moltenCelestium.get(), FluidValues.INGOT)
                .setDamagable(upgrade1IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+3)))
                .save(whenMythicMetalsLoaded, modResource(folder + "celestium/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.CELESTIUM.getSword(), MythicTools.CELESTIUM.getHoe()),
                        HephPlusFluidRegistry.moltenCelestium.get(), FluidValues.INGOT)
                .setDamagable(upgrade1IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+2)))
                .save(whenMythicMetalsLoaded, modResource(folder + "celestium/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.CELESTIUM.getShovel()),
                        HephPlusFluidRegistry.moltenCelestium.get(), FluidValues.INGOT)
                .setDamagable(upgrade1IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+1)))
                .save(whenMythicMetalsLoaded, modResource(folder + "celestium/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.CELESTIUM.getHelmet()),
                        HephPlusFluidRegistry.moltenCelestium.get(), FluidValues.INGOT)
                .setDamagable(upgrade1IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+5)))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "celestium/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.CELESTIUM.getChestplate()),
                        HephPlusFluidRegistry.moltenCelestium.get(), FluidValues.INGOT)
                .setDamagable(upgrade1IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+8)))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "celestium/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.CELESTIUM.getLeggings()),
                        HephPlusFluidRegistry.moltenCelestium.get(), FluidValues.INGOT)
                .setDamagable(upgrade1IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+7)))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "celestium/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.CELESTIUM.getBoots()),
                        HephPlusFluidRegistry.moltenCelestium.get(), FluidValues.INGOT)
                .setDamagable(upgrade1IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+4)))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "celestium/boots"));

        // Metallurgium

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.METALLURGIUM.getPickaxe(), MythicTools.METALLURGIUM.getAxe()),
                        HephPlusFluidRegistry.moltenMetallurgium.get(), FluidValues.INGOT)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenNetherite.get(), FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+3)))
                .save(whenMythicMetalsLoaded, modResource(folder + "metallurgium/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.METALLURGIUM.getSword(), MythicTools.METALLURGIUM.getHoe()),
                        HephPlusFluidRegistry.moltenMetallurgium.get(), FluidValues.INGOT)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenNetherite.get(), FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+2)))
                .save(whenMythicMetalsLoaded, modResource(folder + "metallurgium/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.METALLURGIUM.getShovel()),
                        HephPlusFluidRegistry.moltenMetallurgium.get(), FluidValues.INGOT)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenNetherite.get(), FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+1)))
                .save(whenMythicMetalsLoaded, modResource(folder + "metallurgium/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.METALLURGIUM.getHelmet()),
                        HephPlusFluidRegistry.moltenMetallurgium.get(), FluidValues.INGOT)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenNetherite.get(), FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+5)))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "metallurgium/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.METALLURGIUM.getChestplate()),
                        HephPlusFluidRegistry.moltenMetallurgium.get(), FluidValues.INGOT)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenNetherite.get(), FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+8)))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "metallurgium/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.METALLURGIUM.getLeggings()),
                        HephPlusFluidRegistry.moltenMetallurgium.get(), FluidValues.INGOT)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenNetherite.get(), FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+7)))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "metallurgium/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.METALLURGIUM.getBoots()),
                        HephPlusFluidRegistry.moltenMetallurgium.get(), FluidValues.INGOT)
                .setDamagable(upgrade2IngotGemSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenNetherite.get(), FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenDiamond.get(), FluidValues.GEM * (7+4)))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "metallurgium/boots"));

        // Osmium Chainmail

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.OSMIUM_CHAINMAIL.getHelmet()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.NUGGET * 35)
                .setDamagable(upgrade2IngotSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.NUGGET * 22))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "osmium_chainmail/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.OSMIUM_CHAINMAIL.getChestplate()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.NUGGET * 35)
                .setDamagable(upgrade2IngotSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.INGOT * 4))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "osmium_chainmail/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.OSMIUM_CHAINMAIL.getLeggings()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.NUGGET * 35)
                .setDamagable(upgrade2IngotSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.NUGGET * 31))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "osmium_chainmail/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.OSMIUM_CHAINMAIL.getBoots()),
                        TinkerFluids.moltenOsmium.get(), FluidValues.NUGGET * 35)
                .setDamagable(upgrade2IngotSizes)
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.INGOT * 2))
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "osmium_chainmail/boots"));

        // Tidesinger

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.TIDESINGER.getPickaxe(), MythicTools.TIDESINGER.getAxe()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * (7+3))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "tidesinger/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.TIDESINGER.getSword(), MythicTools.TIDESINGER.getHoe()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * (7+2))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "tidesinger/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.TIDESINGER.getShovel()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * (7+1))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "tidesinger/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.TIDESINGER.getHelmet()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * (7+5))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "tidesinger/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.TIDESINGER.getChestplate()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * (7+8))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "tidesinger/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.TIDESINGER.getLeggings()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * (7+7))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "tidesinger/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.TIDESINGER.getBoots()),
                        HephPlusFluidRegistry.moltenAquarium.get(), FluidValues.INGOT * (7+4))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "tidesinger/boots"));

        // Legendary Banglum

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.LEGENDARY_BANGLUM.getPickaxe(), MythicTools.LEGENDARY_BANGLUM.getAxe()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * (8+3))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "legendary_banglum/axes"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.LEGENDARY_BANGLUM.getSword(), MythicTools.LEGENDARY_BANGLUM.getHoe()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * (8+2))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "legendary_banglum/weapon"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicTools.LEGENDARY_BANGLUM.getShovel()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * (8+1))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(folder + "legendary_banglum/shovel"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.LEGENDARY_BANGLUM.getHelmet()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * (8+5))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "legendary_banglum/helmet"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.LEGENDARY_BANGLUM.getChestplate()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * (8+8))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "legendary_banglum/chestplate"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.LEGENDARY_BANGLUM.getLeggings()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * (8+7))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "legendary_banglum/leggings"));

        MeltingRecipeBuilder.melting(Ingredient.of(MythicArmor.LEGENDARY_BANGLUM.getBoots()),
                        HephPlusFluidRegistry.moltenBanglum.get(), FluidValues.INGOT * (8+4))
                .setDamagable(FluidValues.NUGGET)
                .save(whenMythicMetalsLoaded, modResource(metalFolder + "legendary_banglum/boots"));
    }
}
