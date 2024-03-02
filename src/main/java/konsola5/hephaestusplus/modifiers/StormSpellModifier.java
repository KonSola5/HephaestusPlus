package konsola5.hephaestusplus.modifiers;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class StormSpellModifier extends Modifier implements MeleeHitModifierHook {

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(TinkerHooks.MELEE_HIT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();
        if (target != null && target.isAlive()) {
            var world = target.level();
            boolean isThunder = world.isThundering();
            if (RANDOM.nextFloat() < modifier.getLevel() * 0.02 * (isThunder ? 4 : 1)) {
                var lightning = EntityType.LIGHTNING_BOLT.create(world);
                if (lightning != null) {
                    lightning.copyPosition(target);
                    world.addFreshEntity(lightning);
                    target.hurt(world.damageSources().lightningBolt(), 4);
                }
            }
        }
    }
}
