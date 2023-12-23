package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.HephaestusPlus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.mantle.recipe.data.IRecipeHelper;

import java.util.function.Consumer;

/**
 * Shared logic for each module's recipe provider
 */
public abstract class BaseRecipeProvider extends FabricRecipeProvider implements IRecipeHelper {
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
