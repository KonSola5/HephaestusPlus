package konsola5.hephaestusplus.mixin;

import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import nourl.mythicmetals.armor.CarmotShield;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

import static konsola5.hephaestusplus.registry.HephPlusAttributes.CARMOT_SHIELD;
import static nourl.mythicmetals.armor.CarmotShield.MAX_COOLDOWN;

@Debug(export = true)
@Mixin(value = CarmotShield.class, remap = false)
public abstract class CarmotShieldMixin {
    @Shadow
    @Final
    private Player player;

    @Shadow
    public int cooldown;

    @ModifyConstant(method = "tickShield", constant = @Constant(floatValue = 0.1f))
    private float increaseCarmotShieldRegen(float original) {
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        int level = ModifierUtil.getModifierLevel(stack, MoarModifierIds.carmot_synergy);
        return (float) (0.1 * ((0.5 * level) + 1));
    }

    @Inject(method = "tickShield", at = @At(value = "FIELD", target = "Lnourl/mythicmetals/armor/CarmotShield;cooldown:I", opcode = Opcodes.GETFIELD, ordinal = 1))
    private void modifyCooldown(CallbackInfo ci) {
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        int level = ModifierUtil.getModifierLevel(stack, MoarModifierIds.carmot_synergy);
        cooldown = Math.min(cooldown, MAX_COOLDOWN - (20 * level));
    }

    // Shut up MinecraftDevelopment, this works fine.
    @ModifyVariable(method = "getMaxHealth", at = @At("STORE"), name = "result")
    private int addShield(int result) {
        double shield = player.getAttributes().hasAttribute(CARMOT_SHIELD)
                ? player.getAttributeValue(CARMOT_SHIELD) : 0;
        return (int) shield;
    }

}
