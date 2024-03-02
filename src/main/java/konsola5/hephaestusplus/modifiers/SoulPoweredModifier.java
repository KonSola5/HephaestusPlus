package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.HephaestusPlus;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static konsola5.hephaestusplus.registry.HephPlusResourceLocations.STORED_SOULS;

@ParametersAreNonnullByDefault
public class SoulPoweredModifier extends Modifier implements MeleeHitModifierHook, MeleeDamageModifierHook, ModifierRemovalHook, TooltipModifierHook {

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(TinkerHooks.MELEE_HIT);
    }

    public void incrementSouls(ModDataNBT persistentData) {
        persistentData.putInt(STORED_SOULS, persistentData.getInt(STORED_SOULS) + 1);
    }

    private static final Component STORED_SOULS_TEXT = HephaestusPlus.makeTranslation("modifier", "soul_powered.stored_souls");

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, slimeknights.mantle.client.TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        IModDataView persistentData = tool.getPersistentData();
        int souls = persistentData.getInt(STORED_SOULS);
        float bonus = (float) (0.5 * Math.log(souls));
        if (souls > 0) {
            tooltip.add(applyStyle(Component.empty().append(STORED_SOULS_TEXT).append(Component.literal(": " + souls))));
        }
        if (bonus > 0) {
            TooltipModifierHook.addDamageBoost(tool, modifier, bonus, tooltip);
        }
    }

    @Nullable
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(STORED_SOULS);
        return null;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        IModDataView persistentData = tool.getPersistentData();
        int souls = persistentData.getInt(STORED_SOULS);
        if (souls > 0) {
            return damage + (float) (0.5 * Math.log(souls));
        }
        return damage;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        ModDataNBT persistentData = tool.getPersistentData();
        LivingEntity target = context.getLivingTarget();
        if (target != null && target.isDeadOrDying()) {
            incrementSouls(persistentData);
        }
    }
}
