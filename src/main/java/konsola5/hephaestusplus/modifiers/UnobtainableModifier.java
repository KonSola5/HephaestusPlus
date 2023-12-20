package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.HephaestusPlus;
import konsola5.hephaestusplus.damage.DamageSources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.mantle.client.TooltipKey;

import java.util.List;
import java.util.UUID;

import static konsola5.hephaestusplus.HephPlusRegistry.TOOL_OWNER;

public class UnobtainableModifier extends NoLevelsModifier {
    // Inspired by Relic binding from Botania (by Vazkii) (uses some code from Botania, heavily modified)
    // Thanks 800020h for the idea!
    private static final Component TOOL_NO_OWNER = HephaestusPlus.makeTranslation("modifier", "unobtainable.tool_no_owner");
    private static final Component TOOL_OWNED_BY = HephaestusPlus.makeTranslation("modifier", "unobtainable.tool_owned_by");
    private static final Component NOT_YOUR_TOOL = HephaestusPlus.makeTranslation("modifier", "unobtainable.not_your_tool");
    private static final Component YOUR_TOOL = HephaestusPlus.makeTranslation("modifier", "unobtainable.your_tool");

    private static LivingEntity holder = null;


    public UUID getHolderUUID(IToolStackView tool) {
        if (tool.getPersistentData().get(TOOL_OWNER) != null) {
            try {
                return UUID.fromString(tool.getPersistentData().getString(TOOL_OWNER));
            } catch (IllegalArgumentException ex) { // Bad UUID in tag
                tool.getPersistentData().remove(TOOL_OWNER);
            }
        }
        return null;
    }

    public void bindTool(IToolStackView tool, UUID uuid) {
        ModDataNBT persistentData = tool.getPersistentData();
        persistentData.putString(TOOL_OWNER, uuid.toString());
    }

    @Override
    public void onInventoryTick(@NotNull IToolStackView tool, int level, @NotNull Level world, @NotNull LivingEntity tool_holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, @NotNull ItemStack stack) {
        holder = tool_holder;
        if (getHolderUUID(tool) == null) {
            bindTool(tool, holder.getUUID());
        }
        if (!holder.getUUID().equals(getHolderUUID(tool)) && holder.tickCount % 10 == 0) {
            DamageSource unobtainiumDamage = new DamageSources(holder.level().registryAccess()).getUnobtainiumDamage();
            holder.hurt(unobtainiumDamage, 2);
        }
    }

    @Override
    public Component getDisplayName(IToolStackView tool, int level) {
        return getDisplayName().copy()
                .append(": ").append(addOwnerInfo(tool));
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, @NotNull List<Component> tooltip, @NotNull TooltipKey tooltipKey, @NotNull TooltipFlag tooltipFlag) {

        if (player != null) {
            if (tool.getPersistentData().get(TOOL_OWNER) == null) {
                tooltip.add(TOOL_NO_OWNER);
            } else {
                tooltip.add(Component.empty().append(TOOL_OWNED_BY).append(": ").append(player.getName()));
            }
        }
    }

    private Component addOwnerInfo(IToolStackView tool) {
        if (holder != null) {
            if (tool.getPersistentData().get(TOOL_OWNER) == null) {
                return(TOOL_NO_OWNER);
            } else {
                if (!holder.getUUID().equals(getHolderUUID(tool))) {
                    return (NOT_YOUR_TOOL);
                } else {
                    return (YOUR_TOOL);
                }
            }
        }
        return Component.empty();
    }
}
