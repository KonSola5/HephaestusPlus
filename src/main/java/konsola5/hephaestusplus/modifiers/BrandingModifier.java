package konsola5.hephaestusplus.modifiers;

import io.wispforest.owo.ops.WorldOps;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import nourl.mythicmetals.effects.MythicStatusEffects;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.ParametersAreNonnullByDefault;

import static nourl.mythicmetals.item.tools.PalladiumToolSet.MAX_HEAT;

@ParametersAreNonnullByDefault
public class BrandingModifier extends Modifier implements MeleeHitModifierHook {
    // Mostly copy-paste from Mytic Metals' PalladiumToolSet.java, translated to Mojang mappings.

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(TinkerHooks.MELEE_HIT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (FabricLoader.getInstance().isModLoaded("mythicmetals")) {
            LivingEntity target = context.getLivingTarget();
            LivingEntity attacker = context.getAttacker();
            if (target != null && target.isAlive()) {
                if (!target.hasEffect(MythicStatusEffects.HEAT)) {
                    target.addEffect(new MobEffectInstance(MythicStatusEffects.HEAT, 100), attacker);
                } else {
                    var effect = target.getEffect(MythicStatusEffects.HEAT);
                    int amplifier = effect == null ? 0 : (target.getRandom().nextFloat() < 0.15 * modifier.getLevel()) ? effect.getAmplifier() + 1 : effect.getAmplifier();
                    if (amplifier >= MAX_HEAT) {
                        WorldOps.playSound(target.level(), target.position(), SoundEvents.GENERIC_BURN, SoundSource.PLAYERS);
                    }
                    target.forceAddEffect(new MobEffectInstance(MythicStatusEffects.HEAT, 100 + (20 * amplifier * amplifier), Math.min(amplifier, MAX_HEAT)), attacker);
                }
            }
        }
    }
}