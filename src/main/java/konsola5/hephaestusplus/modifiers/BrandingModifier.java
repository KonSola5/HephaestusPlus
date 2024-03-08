package konsola5.hephaestusplus.modifiers;

import io.wispforest.owo.ops.WorldOps;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import nourl.mythicmetals.effects.MythicStatusEffects;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.ParametersAreNonnullByDefault;

import static nourl.mythicmetals.item.tools.PalladiumToolSet.MAX_HEAT;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class BrandingModifier extends Modifier {
    // Mostly copy-paste from Mytic Metals' PalladiumToolSet.java, translated to Mojang mappings.
    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        if (FabricLoader.getInstance().isModLoaded("mythicmetals")) {
            LivingEntity target = context.getLivingTarget();
            LivingEntity attacker = context.getAttacker();
            if (target != null && target.isAlive()) {
                if (!target.hasEffect(MythicStatusEffects.HEAT)) {
                    target.addEffect(new MobEffectInstance(MythicStatusEffects.HEAT, 100), attacker);
                } else {
                    var effect = target.getEffect(MythicStatusEffects.HEAT);
                    int amplifier = effect == null ? 0 : (target.getRandom().nextFloat() < 0.15 * level) ? effect.getAmplifier() + 1 : effect.getAmplifier();
                    if (amplifier >= MAX_HEAT) {
                        WorldOps.playSound(target.level(), target.position(), SoundEvents.GENERIC_BURN, SoundSource.PLAYERS);
                    }
                    target.forceAddEffect(new MobEffectInstance(MythicStatusEffects.HEAT, 100 + (20 * amplifier * amplifier), Math.min(amplifier, MAX_HEAT)), attacker);
                }
            }
        }
        return 0;
    }
}