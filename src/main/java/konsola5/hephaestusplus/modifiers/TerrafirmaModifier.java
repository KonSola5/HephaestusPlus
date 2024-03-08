package konsola5.hephaestusplus.modifiers;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import vazkii.botania.api.mana.ManaItemHandler;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TerrafirmaModifier extends Modifier {
    // A copy-paste from Botania's Terra armor set, modified to fit the tool better.
    @Override
    public void onInventoryTick(@NotNull IToolStackView tool, int level, @NotNull Level world, @NotNull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, @NotNull ItemStack stack) {
        if (FabricLoader.getInstance().isModLoaded("botania") && !world.isClientSide() && holder instanceof Player player) {
            int food = player.getFoodData().getFoodLevel();
            if (food > 0 && food < 18 && player.isHurt() && player.tickCount % Math.max(20, (100 - 20 * level)) == 0 && isSelected) {
                 if (ManaItemHandler.instance().requestManaExact(stack, player, 60, true)) player.heal(1F);
            }
        }
    }
}
