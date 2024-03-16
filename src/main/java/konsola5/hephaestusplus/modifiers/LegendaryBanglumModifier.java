package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.ids.MoarMaterialIds;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

@SuppressWarnings("deprecation")
public class LegendaryBanglumModifier extends NoLevelsModifier {
    @Override
    public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
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
    public float beforeEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        if (!context.getAttacker().getItemInHand(context.getHand()).isEmpty()) {
            return (float) (1.5 * knockback);
        }
        return knockback;
    }
}
