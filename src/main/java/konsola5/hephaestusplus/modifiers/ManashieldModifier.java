package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.botania.api.mana.ManaItemHandler;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class ManashieldModifier extends Modifier {
    private static final int MANA_PER_DAMAGE_MIN = 60;

    @Override
    public int onDamageTool(@NotNull IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
        if (FabricLoader.getInstance().isModLoaded("botania") && holder instanceof Player) {
            if (getChance() == 1 || RANDOM.nextFloat() < getChance()) {
                int numParts = tool.getMaterials().getList().size();
                ItemStack toolStack = holder.getMainHandItem();
                if (tool instanceof ToolStack) toolStack = ((ToolStack) tool).createStack();
                while (amount > 0) {
                    if (ManaItemHandler.instance().requestManaExactForTool(toolStack, (Player) holder, manaCost(toolStack, numParts), true)) {
                        amount--;
                    }
                    else break;
                }
            }
        }
        return amount;
    }

    /**
     * @return Repair chance (ranged between 0 and 1)
     */
    public double getChance() {
        return 1;
    }

    private int manaCost(ItemStack toolStack, int numParts) {
        int manashieldLevel = ModifierUtil.getModifierLevel(toolStack, MoarModifierIds.garbage_collector);
        int crudeLevel = ModifierUtil.getModifierLevel(toolStack, MoarModifierIds.garbage_collector);

        if (manashieldLevel > 0) return MANA_PER_DAMAGE_MIN * (int) Math.pow(2, numParts - manashieldLevel - (crudeLevel / 2d));
        else return MANA_PER_DAMAGE_MIN;
    }
}
