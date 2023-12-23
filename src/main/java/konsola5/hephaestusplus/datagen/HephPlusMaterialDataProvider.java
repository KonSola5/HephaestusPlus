package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.ids.MoarMaterialIds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class HephPlusMaterialDataProvider extends AbstractMaterialDataProvider {

    public HephPlusMaterialDataProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Materials";
    }

    @Override
    protected void addMaterials() {
        // Mythic Metals
        addCompatMetalMaterial(MoarMaterialIds.adamantite, 2, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.aquarium, 2, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.banglum, 2, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.carmot, 3, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.celestium, 4, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.durasteel, 3, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.hallowed, 4, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.kyber, 2, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.metallurgium, 4, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.mythril, 3, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.orichalcum, 3, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.palladium, 3, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.prometheum, 3, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.quadrillum, 2, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.runite, 3, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.star_platinum, 4, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.stormyx, 3, ORDER_COMPAT);
        // Botania
        addCompatMaterial(MoarMaterialIds.livingwood, 1, ORDER_COMPAT, "livingwood_logs", true);
        addCompatMaterial(MoarMaterialIds.livingrock, 1, ORDER_COMPAT, "livingrock", true);
        addCompatMaterial(MoarMaterialIds.manastring, 2, ORDER_COMPAT, "mana_string", true);
        addCompatMetalMaterial(MoarMaterialIds.manasteel, 2, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.elementium, 3, ORDER_COMPAT);
        addCompatMetalMaterial(MoarMaterialIds.terrasteel, 4, ORDER_COMPAT);
    }
}
