package konsola5.hephaestusplus.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static konsola5.hephaestusplus.registry.HephPlusResourceLocations.PROMETHEUM_REPAIRS;

@ParametersAreNonnullByDefault
public class RegrowthModifier extends Modifier implements ConditionalStatModifierHook, MeleeDamageModifierHook, ModifierRemovalHook, TooltipModifierHook {
    private static final int REPAIR_THRESHOLD = 1200;

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder
                .addHook(this, TinkerHooks.CONDITIONAL_STAT)
                .addHook(this, TinkerHooks.MELEE_DAMAGE)
                .addHook(this, TinkerHooks.REMOVE)
                .addHook(this, TinkerHooks.TOOLTIP);
    }

    public void incrementRepairs(ModDataNBT persistentData) {
        persistentData.putInt(PROMETHEUM_REPAIRS, persistentData.getInt(PROMETHEUM_REPAIRS) + 1);
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
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        return 0;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        IModDataView persistentData = tool.getPersistentData();
        int repairs = persistentData.getInt(PROMETHEUM_REPAIRS);
        if (repairs >= REPAIR_THRESHOLD) {
            TooltipModifierHook.addDamageBoost(tool, modifier, 1, tooltip);
        }
    }

    @Nullable
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(PROMETHEUM_REPAIRS);
        return null;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        IModDataView persistentData = tool.getPersistentData();
        int repairs = persistentData.getInt(PROMETHEUM_REPAIRS);
        int bonus = 0;
        if (repairs >= REPAIR_THRESHOLD) {
            bonus = 1;
        }
        return damage + bonus;
    }
}