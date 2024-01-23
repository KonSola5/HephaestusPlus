package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.registry.HephPlusItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
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
        Consumer<FinishedRecipe> whenFENLoaded = withCondition(consumer,
                DefaultResourceConditions.allModsLoaded("fabricaeexnihilo")
        );
        String folder = "tools/building/";

        toolBuilding(consumer, HephPlusItemRegistry.handHammer, folder);
        toolBuilding(consumer, HephPlusItemRegistry.crook, folder);

        this.addPartRecipes(whenFENLoaded);
    }

    private void addPartRecipes(Consumer<FinishedRecipe> consumer) {
        Consumer<FinishedRecipe> whenFENLoaded = withCondition(consumer,
                DefaultResourceConditions.allModsLoaded("fabricaeexnihilo")
        );
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";

        partRecipes(whenFENLoaded, HephPlusItemRegistry.crookHead, HephPlusItemRegistry.crookHeadCast, 2, partFolder, castFolder);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Tool Recipes";
    }
}
