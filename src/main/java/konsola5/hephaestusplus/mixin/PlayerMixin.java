package konsola5.hephaestusplus.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import konsola5.hephaestusplus.registry.HephPlusAttributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = Player.class, priority = 1001)
@Debug(export = true)
public class PlayerMixin {
    /**
     * Registers the Carmot Shield attribute.
     * Priority 1001, since Porting Lib doesn't use @ModifyReturnValue.
     */
    @ModifyReturnValue(method = "createAttributes", at = @At("RETURN"))
    private static AttributeSupplier.Builder hephaestusPlus$addCarmotShieldAttribute(AttributeSupplier.Builder original) {
        return original.add(HephPlusAttributes.CARMOT_SHIELD);
    }
}
