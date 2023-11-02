package com.konsola5.recipegen;

import com.konsola5.HephaestusPlus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.tconstruct.library.data.recipe.IRecipeHelper;

import java.util.function.Consumer;

@SuppressWarnings("deprecation")
/**
 * Shared logic for each module's recipe provider
 */
public abstract class BaseRecipeProvider extends FabricRecipeProvider implements /*IConditionBuilder,*/ IRecipeHelper {
    public BaseRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public abstract void buildRecipes(Consumer<FinishedRecipe> consumer);

    @Override
    public abstract String getName();

    @Override
    public String getModId() {
        return HephaestusPlus.MOD_ID;
    }
}
