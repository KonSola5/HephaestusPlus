package konsola5.hephaestusplus.modifiers;

import io.github.fabricators_of_create.porting_lib.entity.events.PlayerEvents.BreakSpeed;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static konsola5.hephaestusplus.registry.HephPlusResourceLocations.VELOCITY_ACTIVE;
import static konsola5.hephaestusplus.registry.HephPlusResourceLocations.VELOCITY_COOLDOWN;

@ParametersAreNonnullByDefault
public class EnergyVelocityModifier extends BatteryModifier implements GeneralInteractionModifierHook, ConditionalStatModifierHook {

    private final long ENERGY_PER_TICK = 5;

    public EnergyVelocityModifier() {
        super(0);
    }

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.CHARGEABLE_INTERACT);
        super.registerHooks(hookBuilder);
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.DRAW_SPEED) {
            return (float) (baseValue * (1 + (0.5 * modifier.getLevel())));
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
                extract(tool, (ENERGY_PER_TICK * (long) Math.pow(2, level - 1)));
                if (newVelocityDuration == 0) {
                    world.playSound(null, holder, Sounds.DISCHARGE.getSound(), SoundSource.PLAYERS, 1.0f, 1.0f);
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

    public ResourceLocation getShinyKey() {
        return IModifiable.SHINY;
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        ModDataNBT persistentData = tool.getPersistentData();
        Level world = player.level();

        int DURATION = 10 * 20;
        int COOLDOWN = 30 * 20;

        if (persistentData.getInt(VELOCITY_COOLDOWN) == 0 && getEnergy(tool) >= ENERGY_PER_TICK * Math.pow(2, modifier.getLevel() - 1) * DURATION) {
            persistentData.putInt(VELOCITY_COOLDOWN, COOLDOWN);
            persistentData.putInt(VELOCITY_ACTIVE, DURATION);
            world.playSound(player, player, Sounds.CHARGED.getSound(), SoundSource.PLAYERS, 1.0f, 1.0f);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
    }


}
