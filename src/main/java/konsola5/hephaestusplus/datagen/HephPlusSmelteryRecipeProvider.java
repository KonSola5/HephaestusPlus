package konsola5.hephaestusplus.datagen;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import konsola5.hephaestusplus.registry.HephPlusItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
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

        MeltingRecipeBuilder.melting(Ingredient.of(HephPlusItemRegistry.carmotReinforcement), HephPlusFluidRegistry.moltenCarmot.get(), FluidValues.NUGGET * 3)
                .addByproduct(new FluidStack(TinkerFluids.moltenObsidian.get(), FluidValues.GLASS_PANE))
                .save(consumer, modResource(folder + "carmot/reinforcement"));
    }
}
