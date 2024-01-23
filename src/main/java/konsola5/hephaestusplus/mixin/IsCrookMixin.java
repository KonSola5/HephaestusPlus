package konsola5.hephaestusplus.mixin;

import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import wraith.fabricaeexnihilo.modules.tools.CrookItem;

@Mixin(CrookItem.class)
public class IsCrookMixin {
    @Inject(method = "isCrook", at = @At("HEAD"), cancellable = true)
    private static void checkCrooking(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        int level = ModifierUtil.getModifierLevel(stack, MoarModifierIds.crooking);
        if (level > 0) cir.setReturnValue(true);
    }
}