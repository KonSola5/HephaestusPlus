package konsola5.hephaestusplus.mixin;

import konsola5.hephaestusplus.registry.HephPlusModifierRegistry;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import wraith.fabricaeexnihilo.modules.tools.HammerItem;

@Mixin(HammerItem.class)
public class IsHammerMixin {
    @Inject(method = "isHammer", at = @At("HEAD"), cancellable = true)
    private static void checkSmashing(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        int level = ModifierUtil.getModifierLevel(stack, HephPlusModifierRegistry.SMASHING.getId());
        if (level > 0) cir.setReturnValue(true);
    }
}