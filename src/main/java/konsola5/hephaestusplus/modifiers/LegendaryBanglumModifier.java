package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.ids.MoarMaterialIds;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class LegendaryBanglumModifier extends NoLevelsModifier implements ToolStatsModifierHook, MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(TinkerHooks.TOOL_STATS);
        hookBuilder.addHook(TinkerHooks.MELEE_HIT);
    }

    @Override
    public void addToolStats(ToolRebuildContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        var materialVariantList = context.getMaterials().getList();
        boolean hasBanglum = false;
        for (var material : materialVariantList) {
            if (material.matches(MoarMaterialIds.banglum)) {
                hasBanglum = true;
                break;
            }
        }
        if (hasBanglum) {
            ToolStats.DURABILITY.add(builder, 500);
        }
    }

    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        if (!context.getAttacker().getItemInHand(context.getHand()).isEmpty()) {
            return (float) (1.5 * knockback);
        }
        return knockback;
    }
}
