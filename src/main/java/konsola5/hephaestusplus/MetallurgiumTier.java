package konsola5.hephaestusplus;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class MetallurgiumTier implements Tier {

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
}