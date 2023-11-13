package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;

import slimeknights.tconstruct.library.utils.TooltipKey;

import java.util.List;

public class RegrowthModifier extends Modifier implements ConditionalStatModifierHook {
    private static final int REPAIR_THRESHOLD = 1200;

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT);
    }

    public void incrementRepairs(ModDataNBT persistentData) {
        persistentData.putInt(Registry.PROMETHEUM_REPAIRS, persistentData.getInt(Registry.PROMETHEUM_REPAIRS) + 1);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, int level, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!world.isClientSide() && holder.tickCount % 20 == 0 && holder.getUseItem() != stack && tool.getDamage() != 0) {
            if (RANDOM.nextFloat() < (level * 0.05)) { // 5% chance per second to restore 1 durability
                ToolDamageUtil.repair(tool, 1);
                ModDataNBT persistentData = tool.getPersistentData(); // volatileData is read-only, that's why persistentData is used
                incrementRepairs(persistentData);
            }
        }
    }

    @Override
    public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
        IModDataView persistentData = tool.getPersistentData();
        int repairs = persistentData.getInt(Registry.PROMETHEUM_REPAIRS);
        int bonus = 0;
        if (repairs >= REPAIR_THRESHOLD) {
            bonus = 1;
        }
        return damage + bonus;
    }

    @Override
    public void onRemoved(IToolStackView tool) {
        tool.getPersistentData().remove(Registry.PROMETHEUM_REPAIRS);
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        IModDataView persistentData = tool.getPersistentData();
        int repairs = persistentData.getInt(Registry.PROMETHEUM_REPAIRS);
        if (repairs >= REPAIR_THRESHOLD) {
            addDamageTooltip(tool, 1, tooltip);
        }
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        return 0;
    }
}