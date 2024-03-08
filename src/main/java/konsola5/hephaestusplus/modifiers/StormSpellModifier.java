package konsola5.hephaestusplus.modifiers;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class StormSpellModifier extends Modifier {
    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();
        if (target != null && target.isAlive()) {
            var world = target.level();
            boolean isThunder = world.isThundering();
            if (RANDOM.nextFloat() < level * 0.02 * (isThunder ? 4 : 1)) {
                var lightning = EntityType.LIGHTNING_BOLT.create(world);
                if (lightning != null) {
                    lightning.copyPosition(target);
                    world.addFreshEntity(lightning);
                    target.hurt(world.damageSources().lightningBolt(), 4);
                }
            }
        }
        return 0;
    }
}
