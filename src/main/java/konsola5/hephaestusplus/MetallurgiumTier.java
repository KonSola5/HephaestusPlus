package konsola5.hephaestusplus;

import io.github.fabricators_of_create.porting_lib.extensions.extensions.TierExtensions;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class MetallurgiumTier implements Tier, TierExtensions {

    public static Tier instance = new MetallurgiumTier();

    @Override
    public int getUses() {
        return 3000;
    }

    @Override
    public float getSpeed() {
        return 15f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 8f;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 30;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Nullable
    @Override
    public TagKey<Block> getTag() {
        return MiningLevelManager.getBlockTag(getLevel());
    }
}