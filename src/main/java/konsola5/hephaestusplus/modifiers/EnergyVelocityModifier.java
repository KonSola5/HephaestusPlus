package konsola5.hephaestusplus.modifiers;

import io.github.fabricators_of_create.porting_lib.entity.events.PlayerEvents.BreakSpeed;
import konsola5.hephaestusplus.HephaestusPlus;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

import static konsola5.hephaestusplus.registry.HephPlusResourceLocations.*;
import static konsola5.hephaestusplus.util.HephPlusUtil.SECONDS;

@SuppressWarnings("deprecation")
public class EnergyVelocityModifier extends BatteryModifier implements GeneralInteractionModifierHook, ConditionalStatModifierHook {

    private final long ENERGY_PER_TICK = 10;
    private final double MINING_SPEED_BOOST = 0.4;

    private static final String NOT_ENOUGH_ENERGY = HephaestusPlus.makeTranslationKey("modifier", "energy_velocity.not_enough_energy");
    private static final String ON_COOLDOWN = HephaestusPlus.makeTranslationKey("modifier", "energy_velocity.on_cooldown");
    private static final String TRANSFER_RATE_TOO_SMALL = HephaestusPlus.makeTranslationKey("modifier", "energy_velocity.transfer_rate_too_small");
    private static final String COOLDOWN = HephaestusPlus.makeTranslationKey("modifier", "energy_velocity.cooldown");

    private static final Component ENERGY_VELOCITY = HephaestusPlus.makeTranslation("modifier", "energy_velocity.energy_velocity");

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.CHARGEABLE_INTERACT);
        super.registerHooks(hookBuilder);
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.DRAW_SPEED) {
            return (float) (baseValue * (1 + (MINING_SPEED_BOOST * modifier.getLevel())));
        }
        return baseValue;
    }

    @Override
    public void onBreakSpeed(IToolStackView tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (isEffective && !tool.isBroken() && tool.getPersistentData().getInt(VELOCITY_ACTIVE) > 0) {
            event.setNewSpeed((float) ((1 + (0.5 * level)) * event.getNewSpeed()));
        }
    }

    @Override
    public void onInventoryTick(IToolStackView tool, int level, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!world.isClientSide() && holder.getUseItem() != stack) {
            var persistentData = tool.getPersistentData();
            if (persistentData.getInt(VELOCITY_ACTIVE) > 0) {
                var newVelocityDuration = persistentData.getInt(VELOCITY_ACTIVE) - 1;
                persistentData.putInt(VELOCITY_ACTIVE, newVelocityDuration);
                extract(tool, (ENERGY_PER_TICK * (1L << level - 1)));
                if (newVelocityDuration == 0) {
                    world.playSound(null, holder, Sounds.DISCHARGE.getSound(), SoundSource.PLAYERS, 1.0f, 1.0f);
                    persistentData.putBoolean(SHINE, false);
                }
            }
            if (persistentData.getInt(VELOCITY_COOLDOWN) > 0) {
                persistentData.putInt(VELOCITY_COOLDOWN, persistentData.getInt(VELOCITY_COOLDOWN) - 1);
            }
        }
    }

    @Override
    public UseAnim getUseAction(IToolStackView tool, ModifierEntry modifier) {
        return GeneralInteractionModifierHook.super.getUseAction(tool, modifier);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        ModDataNBT persistentData = tool.getPersistentData();
        Level world = player.level();

        int DURATION = 10 * 20;
        int COOLDOWN = 30 * 20;

        long total_energy_used = ENERGY_PER_TICK * (1L << modifier.getLevel() - 1) * DURATION;

        if (getTransferRate(tool) < ENERGY_PER_TICK * (1L << modifier.getLevel() - 1)) {
            player.displayClientMessage(Component.translatable(TRANSFER_RATE_TOO_SMALL).withStyle(ChatFormatting.RED), true);
        } else if (persistentData.getInt(VELOCITY_COOLDOWN) > 0) {
            player.displayClientMessage(
                    Component.translatable(
                            ON_COOLDOWN,
                            getExactTime(persistentData.getInt(VELOCITY_COOLDOWN)),
                            I18n.get(SECONDS)
                    ).withStyle(ChatFormatting.RED), true);
        } else if (getEnergy(tool) < total_energy_used) {
            player.displayClientMessage(Component.translatable(NOT_ENOUGH_ENERGY).withStyle(ChatFormatting.RED), true);
        } else {
            persistentData.putInt(VELOCITY_COOLDOWN, COOLDOWN);
            persistentData.putInt(VELOCITY_ACTIVE, DURATION);
            persistentData.putBoolean(SHINE, true);

            world.playSound(player, player, Sounds.CHARGED.getSound(), SoundSource.PLAYERS, 1.0f, 1.0f);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        ModDataNBT persistentData = tool.getPersistentData();
        if (persistentData.getInt(VELOCITY_ACTIVE) > 0) {
            addPercentTooltip(ENERGY_VELOCITY, MINING_SPEED_BOOST * level, tooltip);
        }
        if (persistentData.getInt(VELOCITY_COOLDOWN) > 0) {
            tooltip.add(this.applyStyle(Component.translatable(COOLDOWN)
                    .append(String.valueOf(getRoundedTime(persistentData.getInt(VELOCITY_COOLDOWN))))
                    .append(" ")
                    .append(I18n.get(SECONDS))));
        }
    }

    @Override
    public void onRemoved(IToolStackView tool) {
        ModDataNBT persistentData = tool.getPersistentData();
        persistentData.remove(VELOCITY_COOLDOWN);
        persistentData.remove(VELOCITY_ACTIVE);
    }

    public static int getRoundedTime(int ticks) {
        return (ticks + 19) / 20;
    }

    public static double getExactTime (int ticks) {
        return (double) ticks / 20;
    }
}
