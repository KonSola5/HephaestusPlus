package com.konsola5.datagen;

import com.konsola5.Registry;
import com.konsola5.ids.MoarMaterialIds;
import com.konsola5.ids.MoarModifierIds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;

public class HephaestusPlusMaterialTraitsProvider extends AbstractMaterialTraitDataProvider {
    public HephaestusPlusMaterialTraitsProvider(FabricDataOutput output, AbstractMaterialDataProvider materials) {
        super(output, materials);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Material Traits";
    }

    @Override
    protected void addMaterialTraits() {
        addDefaultTraits(MoarMaterialIds.adamantite,   Registry.SOLID);
        addDefaultTraits(MoarMaterialIds.aquarium,     TinkerModifiers.hydraulic);
//        addDefaultTraits(MoarMaterialIds.banglum,      Registry.BANG_BANG);
        addDefaultTraits(MoarMaterialIds.banglum,      TinkerModifiers.cultivated);
        addDefaultTraits(MoarMaterialIds.carmot,       ModifierIds.fortune, ModifierIds.looting);
//        addDefaultTraits(MoarMaterialIds.celestium,    Registry.CELESTIAL);
        addDefaultTraits(MoarMaterialIds.celestium,    TinkerModifiers.cultivated);
        addDefaultTraits(MoarMaterialIds.durasteel,    MoarModifierIds.durable);
//        addDefaultTraits(MoarMaterialIds.hallowed,     Registry.SOUL_POWER);
        addDefaultTraits(MoarMaterialIds.hallowed,     TinkerModifiers.cultivated);
//        addDefaultTraits(MoarMaterialIds.kyber,        Registry.PRISMATIC);
        addDefaultTraits(MoarMaterialIds.kyber,        TinkerModifiers.cultivated);
//        addDefaultTraits(MoarMaterialIds.metallurgium, Registry.);
        addDefaultTraits(MoarMaterialIds.metallurgium, TinkerModifiers.cultivated);
        addDefaultTraits(MoarMaterialIds.mythril,      MoarModifierIds.magically_modifiable);
//        addDefaultTraits(MoarMaterialIds.orichalcum,   Registry.);
        addDefaultTraits(MoarMaterialIds.orichalcum,   TinkerModifiers.cultivated);
        addDefaultTraits(MoarMaterialIds.palladium,    Registry.BRANDING);
        addDefaultTraits(MoarMaterialIds.prometheum,   Registry.REGROWTH);
//        addDefaultTraits(MoarMaterialIds.quadrillum,   Registry.COSMIC);
        addDefaultTraits(MoarMaterialIds.quadrillum,   TinkerModifiers.cultivated   );
//        addDefaultTraits(MoarMaterialIds.runite,       Registry.FREEZING);
        addDefaultTraits(MoarMaterialIds.runite,       TinkerModifiers.cultivated);
        addDefaultTraits(MoarMaterialIds.star_platinum,MoarModifierIds.stellar_swiftness);
//        addDefaultTraits(MoarMaterialIds.stormyx,      Registry.ZEUS_WRATH);
        addDefaultTraits(MoarMaterialIds.stormyx,      TinkerModifiers.cultivated);
    }
}
