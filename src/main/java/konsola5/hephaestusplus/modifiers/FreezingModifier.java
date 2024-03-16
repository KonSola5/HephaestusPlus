package konsola5.hephaestusplus.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

@SuppressWarnings("deprecation")
public class FreezingModifier extends Modifier {
    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        if (RANDOM.nextFloat() < 0.1 * level) {
            LivingEntity target = context.getLivingTarget();
            LivingEntity attacker = context.getAttacker();
            if (target != null && target.isAlive()) {
                if (!target.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                    target.forceAddEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 19), attacker);
                }
            }
        }
        return 0;
    }
}
