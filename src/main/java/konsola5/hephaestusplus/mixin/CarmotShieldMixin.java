package konsola5.hephaestusplus.mixin;

import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.armor.CarmotShield;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

@Mixin(CarmotShield.class)
public abstract class CarmotShieldMixin {
    @Shadow @Final private Player player;
    @Shadow public float shieldHealth;
    @Shadow public int cooldown;
    @Shadow public int renderTime;
    @Shadow public abstract boolean shouldRenderShield();

    @Shadow public abstract float getMaxHealth();

    @Shadow @Final public static int MAX_COOLDOWN;

    @Inject(method = "tickShield", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    public void buffShield(CallbackInfo ci) {
        if (player.level() == null) ci.cancel();
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        int level = ModifierUtil.getModifierLevel(stack, MoarModifierIds.carmot_synergy);

        // Prevent overshields
        if (shieldHealth > getMaxHealth()) {
            shieldHealth = getMaxHealth();
        }

        // Regenerate shield if not on cooldown
        if (shieldHealth < getMaxHealth()) {
            if (cooldown == 0) {
                shieldHealth = Mth.clamp((float) (shieldHealth += (0.1 * ((0.5 * level)+1))), 0f, getMaxHealth());
                renderTime = 40;
            } else {
                cooldown = Math.min(cooldown, MAX_COOLDOWN - (20 * level));
                cooldown--;
            }
        }

        if (shouldRenderShield()) {
            renderTime--;
            MythicMetals.CARMOT_SHIELD.sync(player);
        }

        // No shield, stop rendering
        if (getMaxHealth() == 0) {
            renderTime = 0;
            shieldHealth = 0;
        }
        ci.cancel();
    }
}
