package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import wraith.fabricaeexnihilo.modules.ModTags;

import java.util.function.Consumer;

public class HephPlusModifierRecipeProvider extends BaseRecipeProvider {
    public HephPlusModifierRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        addModifierRecipes(consumer);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Modifier Recipes";
    }

    private void addModifierRecipes(Consumer<FinishedRecipe> consumer) {
        String upgradeFolder = "tools/modifiers/upgrade/";
        String abilityFolder = "tools/modifiers/ability/";
        String slotlessFolder = "tools/modifiers/slotless/";
        String upgradeSalvage = "tools/modifiers/salvage/upgrade/";
        String abilitySalvage = "tools/modifiers/salvage/ability/";
        String defenseFolder = "tools/modifiers/defense/";
        String defenseSalvage = "tools/modifiers/salvage/defense/";
        String compatFolder = "tools/modifiers/compat/";
        String compatSalvage = "tools/modifiers/salvage/compat/";
        String worktableFolder = "tools/modifiers/worktable/";

        ModifierRecipeBuilder.modifier(MoarModifierIds.smashing)
                .setTools(ingredientFromTags(TinkerTags.Items.HARVEST_PRIMARY))
                .addInput(ModTags.HAMMERS)
                .setMaxLevel(1)
                .setSlots(SlotType.ABILITY, 1)
                .save(consumer, prefix(MoarModifierIds.smashing, abilityFolder));
        ModifierRecipeBuilder.modifier(MoarModifierIds.crooking)
                .setTools(ingredientFromTags(TinkerTags.Items.HARVEST_PRIMARY))
                .addInput(ModTags.CROOKS)
                .setMaxLevel(1)
                .setSlots(SlotType.ABILITY, 1)
                .save(consumer, prefix(MoarModifierIds.crooking, abilityFolder));
    }

    @SafeVarargs
    private static Ingredient ingredientFromTags(TagKey<Item>... tags) {
        Ingredient[] tagIngredients = new Ingredient[tags.length];
        for (int i = 0; i < tags.length; i++) {
            tagIngredients[i] = Ingredient.of(tags[i]);
        }
        return DefaultCustomIngredients.any(tagIngredients);
    }
}
