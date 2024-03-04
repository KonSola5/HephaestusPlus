package konsola5.hephaestusplus.modifiers;

import io.github.fabricators_of_create.porting_lib.entity.events.PlayerEvents;
import konsola5.hephaestusplus.HephaestusPlus;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;
import java.util.List;

public class CosmicModifier extends Modifier implements ConditionalStatModifierHook {
    private static final Component MINING_SPEED = HephaestusPlus.makeTranslation("modifier", "cosmic.mining_speed");
    private static final Component VELOCITY = HephaestusPlus.makeTranslation("modifier", "cosmic.velocity");
    /**
     * Distance below sea level to get boost
     */
    private static final float BOOST_DISTANCE = 64f;
    /**
     * Blocks above 0 when debuff starts, and the range of debuff in the world
     */
    private static final float DEBUFF_RANGE = 128f;
    /**
     * Mining speed boost when at distance, gets larger when lower
     */
    private static final float MINING_BONUS = 6;
    /**
     * Velocity boost when at distance, gets larger when lower
     */
    private static final float VELOCITY_BONUS = 0.05f;

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT);
    }

    /**
     * Gets the boost for the given level and height, can go negative
     */
    private static float getBoost(Level world, float y, int level, float baseSpeed, float modifier, float bonus) {
        // grants 0 bonus at 64, 1x at BOOST_DISTANCE, 2x at 2*BOOST_DISTANCE
        // prevents the modifier from getting too explosive in tall worlds, clamp between -6 and 12
        if (y > BOOST_DISTANCE) {
            float scale = Mth.clamp((y - BOOST_DISTANCE) / BOOST_DISTANCE, 0, 2);
            return baseSpeed + (level * scale * bonus * modifier);
        }

        // start the debuff 64 blocks above the bottom, but for short worlds start it at Y = 32
        float baselineDebuff = Math.min(world.getMinBuildHeight() + DEBUFF_RANGE / 2, 32);
        if (y < baselineDebuff) {
            // range of 64 blocks for the regular debuff, anything above is full debuff
            if (y <= baselineDebuff - DEBUFF_RANGE) {
                return baseSpeed * 0.25f;
            }
            // formula goes from 100% at baseline to 25% at baseline-64
            return baseSpeed * (1 - ((baselineDebuff - y) * 2 / DEBUFF_RANGE * 0.75f));
        }

        // no boost, no debuff
        return baseSpeed;
    }

    @Override
    public void onBreakSpeed(@NotNull IToolStackView tool, int level, PlayerEvents.@NotNull BreakSpeed event, @NotNull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (!isEffective) {
            return;
        }
        event.setNewSpeed(getBoost(event.getPlayer().level(), event.getPos().getY(), level, event.getNewSpeed(), miningSpeedModifier * tool.getMultiplier(ToolStats.MINING_SPEED), MINING_BONUS));
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, @NotNull List<Component> tooltip, @NotNull TooltipKey key, @NotNull TooltipFlag tooltipFlag) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        if (harvest || tool.hasTag(TinkerTags.Items.RANGED)) {
            Component prefix = harvest ? MINING_SPEED : VELOCITY;
            float baseBoost = harvest ? MINING_BONUS : VELOCITY_BONUS;
            double boost;
            if (player != null && key == TooltipKey.SHIFT) {
                // passing in 1 means greater than 1 is a boost, and less than 1 is a percentage
                // the -1 means for percentage, the range is now 0 to -75%, and for flat boost it's properly 0 to baseBoost
                boost = getBoost(player.level(), (float) player.getY(), level, 1, 1f, baseBoost) - 1;
                if (boost < 0) {
                    // goes from 0 to -75%, don't show 0%
                    if (boost <= -0.01) {
                        addPercentTooltip(prefix, boost, tooltip);
                    }
                    return;
                }
            } else {
                boost = baseBoost * level;
            }
            if (boost >= 0.01) {
                addFlatBoost(prefix, boost * tool.getMultiplier(harvest ? ToolStats.MINING_SPEED : ToolStats.VELOCITY), tooltip);
            }
        }
    }

    @Override
    public float modifyStat(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull LivingEntity living, @NotNull FloatToolStat stat, float baseValue, float multiplier) {
        return 0;
    }
}

