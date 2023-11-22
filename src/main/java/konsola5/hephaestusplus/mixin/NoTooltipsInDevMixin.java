package konsola5.hephaestusplus.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.FabricEvents;

@Mixin(FabricEvents.class)
public class NoTooltipsInDevMixin {
    @Inject(method = "init", at = @At(
            value = "INVOKE",
            target = "Lslimeknights/tconstruct/tools/logic/InteractionHandler;init()V",
            shift = At.Shift.AFTER),
            cancellable = true, remap = false)
    private static void injected(CallbackInfo ci) {
        boolean TOOLTIPS = true;
        if (!TOOLTIPS) ci.cancel();
    }
}
