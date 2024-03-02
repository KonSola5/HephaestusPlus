package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.HephaestusPlus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class PrismaticModifier extends Modifier implements MeleeDamageModifierHook, TooltipModifierHook {
    private static final Component PRISMATIC_DAMAGE = HephaestusPlus.makeTranslation("modifier", "prismatic.attack_damage");

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder
                .addHook(TinkerHooks.MELEE_DAMAGE)
                .addHook(TinkerHooks.TOOLTIP);
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player != null) {
            BlockPos pos = BlockPos.containing(player.position());
            int light = player.getCommandSenderWorld().getBrightness(LightLayer.BLOCK, pos);
            float bonus = (float) (light * modifier.getLevel() * 0.01);
            if (bonus > 0) {
                TooltipModifierHook.addPercentBoost(modifier.getModifier(), PRISMATIC_DAMAGE, bonus, tooltip);
            }
        }
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();
        BlockPos pos = BlockPos.containing(attacker.position());
        int light = attacker.getCommandSenderWorld().getBrightness(LightLayer.BLOCK, pos);
        float bonus = (float) (1 + (light * modifier.getLevel() * 0.01));
        return damage * bonus;
    }
}
