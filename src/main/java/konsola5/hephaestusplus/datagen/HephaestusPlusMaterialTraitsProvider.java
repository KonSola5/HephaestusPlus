package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.Registry;
import konsola5.hephaestusplus.ids.MoarMaterialIds;
import konsola5.hephaestusplus.ids.MoarModifierIds;
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
        addDefaultTraits(MoarMaterialIds.adamantite,   ModifierIds.sharpness);
        addDefaultTraits(MoarMaterialIds.aquarium,     TinkerModifiers.hydraulic);
//        addDefaultTraits(MoarMaterialIds.banglum,      Registry.BANG_BANG);
        addDefaultTraits(MoarMaterialIds.banglum,      TinkerModifiers.cultivated);
        addDefaultTraits(MoarMaterialIds.carmot,       ModifierIds.fortune, ModifierIds.looting);
        addDefaultTraits(MoarMaterialIds.celestium,    MoarModifierIds.unobtainable);
        addDefaultTraits(MoarMaterialIds.durasteel,    MoarModifierIds.durable);
        addDefaultTraits(MoarMaterialIds.hallowed,     Registry.SOUL_POWERED);
        addDefaultTraits(MoarMaterialIds.kyber,        Registry.PRISMATIC);
        addDefaultTraits(MoarMaterialIds.metallurgium, MoarModifierIds.unobtainable);
        addDefaultTraits(MoarMaterialIds.mythril,      MoarModifierIds.magically_modifiable);
        addDefaultTraits(MoarMaterialIds.orichalcum,   Registry.SOLID);
        addDefaultTraits(MoarMaterialIds.palladium,    Registry.BRANDING);
        addDefaultTraits(MoarMaterialIds.prometheum,   Registry.REGROWTH);
        addDefaultTraits(MoarMaterialIds.quadrillum,   Registry.COSMIC);
        addDefaultTraits(MoarMaterialIds.runite,       Registry.FREEZING);
        addDefaultTraits(MoarMaterialIds.star_platinum,MoarModifierIds.stellar_swiftness);
        addDefaultTraits(MoarMaterialIds.stormyx,      Registry.STORM_SPELL);
    }
}
