package konsola5.hephaestusplus.modifiers;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.ResourceColorManager;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.BowAmmoModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.botania.api.mana.ManaItemHandler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Predicate;

@ParametersAreNonnullByDefault
public class ManashotModifier extends NoLevelsModifier implements BowAmmoModifierHook, ProjectileLaunchModifierHook {

    @Override
    protected void registerHooks(Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.BOW_AMMO, TinkerHooks.PROJECTILE_LAUNCH);
    }

    @Override
    public int getPriority() {
        return 61; // before Crystalshot
    }

    @Override
    public Component getDisplayName(IToolStackView tool, int level) {
        // color the display name for the variant
        String variant = tool.getPersistentData().getString(getId());
        if (!variant.isEmpty()) {
            String key = getTranslationKey();
            return Component.translatable(getTranslationKey())
                    .withStyle(style -> style.withColor(ResourceColorManager.getTextColor(key + "." + variant)));
        }
        return super.getDisplayName();
    }

    @Override
    public ItemStack findAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack standardAmmo, Predicate<ItemStack> ammoPredicate) {
        if (FabricLoader.getInstance().isModLoaded("botania")) {
            ItemStack toolStack = shooter.getMainHandItem();
            if (tool instanceof ToolStack) toolStack = ((ToolStack) tool).createStack();
            if (ManaItemHandler.instance().requestManaExactForTool(toolStack, (Player) shooter, 200, false)) {
                return new ItemStack(Items.ARROW, 64);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void shrinkAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack ammo, int needed) {
        if (FabricLoader.getInstance().isModLoaded("botania")) {
            ItemStack toolStack = shooter.getMainHandItem();
            if (tool instanceof ToolStack) toolStack = ((ToolStack) tool).createStack();
            ManaItemHandler.instance().requestManaExactForTool(toolStack, (Player) shooter, 200, true);
        }

    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
        if (arrow != null) {
            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }
    }
}