package konsola5.hephaestusplus.damage;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

public class DamageSources {
    private final Registry<DamageType> damageTypes;

    private final DamageSource unobtainium_damage;


    public DamageSources(RegistryAccess pRegistry) {
        this.damageTypes = pRegistry.registryOrThrow(Registries.DAMAGE_TYPE);
        this.unobtainium_damage = this.source(DamageTypes.UNOBTAINIUM_DAMAGE);
    }

    public DamageSource source(ResourceKey<DamageType> pDamageTypeKey) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(pDamageTypeKey));
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pEntity) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(pDamageTypeKey), pEntity);
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pCausingEntity, @Nullable Entity pDirectEntity) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(pDamageTypeKey), pCausingEntity, pDirectEntity);
    }

    public DamageSource getUnobtainiumDamage() {
        return this.unobtainium_damage;
    }
}
