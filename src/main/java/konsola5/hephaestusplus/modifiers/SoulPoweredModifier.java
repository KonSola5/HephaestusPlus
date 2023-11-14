package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.HephaestusPlus;
import konsola5.hephaestusplus.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.utils.TooltipKey;

import java.util.List;

public class SoulPoweredModifier extends Modifier {
    public void incrementSouls(ModDataNBT persistentData) {
        persistentData.putInt(Registry.STORED_SOULS, persistentData.getInt(Registry.STORED_SOULS) + 1);
    }

    private static final Component STORED_SOULS_TEXT = HephaestusPlus.makeTranslation("modifier", "soul_powered.stored_souls");

    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        ModDataNBT persistentData = tool.getPersistentData();
        LivingEntity target = context.getLivingTarget();
        if (target != null && target.isDeadOrDying()) {
            incrementSouls(persistentData);
        }
        return 0;
    }

    @Override
    public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
        IModDataView persistentData = tool.getPersistentData();
        int souls = persistentData.getInt(Registry.STORED_SOULS);
        if (souls > 0) {
            return damage + (float) (0.5 * Math.log(souls));
        }
        return damage;
    }

    public void onRemoved(IToolStackView tool) {
        tool.getPersistentData().remove(Registry.STORED_SOULS);
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        IModDataView persistentData = tool.getPersistentData();
        int souls = persistentData.getInt(Registry.STORED_SOULS);
        float bonus = (float) (0.5 * Math.log(souls));
        if (souls > 0) {
            tooltip.add(applyStyle(Component.empty().append(STORED_SOULS_TEXT).append(Component.literal(": " + souls))));
        }
        if (bonus > 0) {
            addDamageTooltip(tool, bonus, tooltip);
        }
    }
}
