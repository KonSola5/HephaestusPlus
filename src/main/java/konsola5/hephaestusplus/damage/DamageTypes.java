package konsola5.hephaestusplus.damage;

import konsola5.hephaestusplus.HephaestusPlus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public interface DamageTypes {
    ResourceKey<DamageType> UNOBTAINIUM_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE,
            new ResourceLocation(HephaestusPlus.MOD_ID, "unobtainium_damage")
    );

}
