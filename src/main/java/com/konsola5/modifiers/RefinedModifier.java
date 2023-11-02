package com.konsola5.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class RefinedModifier extends Modifier {
    @Override
    public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
        ToolStats.DURABILITY.multiply(builder, 1 + 0.3 * level);
        ToolStats.ATTACK_DAMAGE.multiply(builder, 1 + 0.1 * level);
    }
}