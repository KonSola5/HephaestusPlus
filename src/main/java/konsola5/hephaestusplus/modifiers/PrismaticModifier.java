package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.HephaestusPlus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.TooltipKey;

import java.util.List;

public class PrismaticModifier extends Modifier {
    private static final Component PRISMATIC_DAMAGE = HephaestusPlus.makeTranslation("modifier", "prismatic.attack_damage");
    @Override
    public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();
        BlockPos pos = BlockPos.containing(attacker.position());
        int light = attacker.getCommandSenderWorld().getBrightness(LightLayer.BLOCK, pos);
        float bonus = (float) (1 + (light * level * 0.01));
        return damage * bonus;
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player != null) {
            BlockPos pos = BlockPos.containing(player.position());
            int light = player.getCommandSenderWorld().getBrightness(LightLayer.BLOCK, pos);
            float bonus = (float) (light * level * 0.01);
            addPercentTooltip(PRISMATIC_DAMAGE, bonus, tooltip);
        }
    }
}
