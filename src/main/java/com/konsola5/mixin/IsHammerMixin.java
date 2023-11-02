package com.konsola5.mixin;

import com.konsola5.Registry;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import wraith.fabricaeexnihilo.modules.ModTags;
import wraith.fabricaeexnihilo.modules.tools.HammerItem;

@Mixin(HammerItem.class)
public class IsHammerMixin {
	@Inject(method = "isHammer", at = @At("RETURN"), cancellable = true)
	private static void checkSmashing(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		int level = ModifierUtil.getModifierLevel(stack, Registry.SMASHING.getId());
		cir.setReturnValue(level > 0 || stack.getItem() instanceof HammerItem || stack.is(ModTags.HAMMERS));
	}
}