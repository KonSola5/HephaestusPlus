package konsola5.hephaestusplus.mixin;

import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import vazkii.botania.common.item.equipment.tool.elementium.ElementiumPickaxeItem;

@Mixin(ElementiumPickaxeItem.class)
public abstract class ShouldFilterOutMixin {
    // The method bodies are ignored.
    @Shadow
    private static boolean isDisposable(ItemStack drop) {
        return false;
    }

    @Shadow
    private static boolean isSemiDisposable(ItemStack drop) {
        return false;
    }

    @Inject(method = "shouldFilterOut", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void modifierFilter(Entity e, ItemStack tool, ItemStack drop, CallbackInfoReturnable<Boolean> cir) {
        int level = ModifierUtil.getModifierLevel(tool, MoarModifierIds.garbage_collector);
        if (level > 0)
            cir.setReturnValue(!drop.isEmpty() && (isDisposable(drop) || isSemiDisposable(drop) && !e.isShiftKeyDown()));
    }

}
