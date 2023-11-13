package com.konsola5.datagen;

import com.konsola5.Registry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

import java.util.function.Consumer;

public class HephaestusPlusToolRecipeProvider extends BaseRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {

    public HephaestusPlusToolRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        String folder = "tools/building/";

        toolBuilding(consumer, Registry.handHammer, folder);
        toolBuilding(consumer, Registry.crook, folder);

        this.addPartRecipes(consumer);
    }

    private void addPartRecipes(Consumer<FinishedRecipe> consumer) {
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";

        partRecipes(consumer, Registry.crookHead, Registry.crookHeadCast,2, partFolder, castFolder);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Tool Recipes";
    }
}
