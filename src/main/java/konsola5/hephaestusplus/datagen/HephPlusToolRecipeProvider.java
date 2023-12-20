package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.HephPlusRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

import java.util.function.Consumer;

public class HephPlusToolRecipeProvider extends BaseRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {

    public HephPlusToolRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        String folder = "tools/building/";

        toolBuilding(consumer, HephPlusRegistry.handHammer, folder);
        toolBuilding(consumer, HephPlusRegistry.crook, folder);

        this.addPartRecipes(consumer);
    }

    private void addPartRecipes(Consumer<FinishedRecipe> consumer) {
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";

        partRecipes(consumer, HephPlusRegistry.crookHead, HephPlusRegistry.crookHeadCast, 2, partFolder, castFolder);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Tool Recipes";
    }
}
