package konsola5.hephaestusplus.mixin;

import konsola5.hephaestusplus.HephPlusRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    /**
     * Registers the Carmot Shield attribute.
     */
    @Inject(at = @At("RETURN"), method = "createAttributes")
    private static void addCarmotShieldAttribute(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(HephPlusRegistry.CARMOT_SHIELD);
    }
}
