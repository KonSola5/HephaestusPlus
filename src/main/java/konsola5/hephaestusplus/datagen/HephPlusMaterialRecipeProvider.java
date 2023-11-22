package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.Registry;
import konsola5.hephaestusplus.ids.MoarMaterialIds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;

import java.util.function.Consumer;

public class HephPlusMaterialRecipeProvider extends BaseRecipeProvider implements IMaterialRecipeHelper {

    public HephPlusMaterialRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";
        compatMeltingCasting(consumer, MoarMaterialIds.adamantite   , Registry.moltenAdamantite, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.aquarium     , Registry.moltenAquarium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.banglum      , Registry.moltenBanglum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.carmot       , Registry.moltenCarmot, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.celestium    , Registry.moltenCelestium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.durasteel    , Registry.moltenDurasteel, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.hallowed     , Registry.moltenHallowed, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.kyber        , Registry.moltenKyber, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.metallurgium , Registry.moltenMetallurgium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.mythril      , Registry.moltenMythril, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.orichalcum   , Registry.moltenOrichalcum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.palladium    , Registry.moltenPalladium, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.prometheum   , Registry.moltenPrometheum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.quadrillum   , Registry.moltenQuadrillum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.runite       , Registry.moltenRunite, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.star_platinum, Registry.moltenStarPlatinum, folder);
        compatMeltingCasting(consumer, MoarMaterialIds.stormyx      , Registry.moltenStormyx, folder);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Material Recipes";
    }
}
