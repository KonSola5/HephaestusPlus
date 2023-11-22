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
        addCompatMetalMaterial(MoarMaterialIds.adamantite,   3, ORDER_COMPAT); // Solid
        addCompatMetalMaterial(MoarMaterialIds.aquarium,     3, ORDER_COMPAT); // Aqua Affinity
        addCompatMetalMaterial(MoarMaterialIds.banglum,      3, ORDER_COMPAT); // Bang-Bang
        addCompatMetalMaterial(MoarMaterialIds.carmot,       3, ORDER_COMPAT); // Fortune, Looting V
        addCompatMetalMaterial(MoarMaterialIds.celestium,    3, ORDER_COMPAT); // Celestial
        addCompatMetalMaterial(MoarMaterialIds.durasteel,    3, ORDER_COMPAT); // Durable
        addCompatMetalMaterial(MoarMaterialIds.hallowed,     3, ORDER_COMPAT); // Soul Powered (more dmg when killing mobs)
        addCompatMetalMaterial(MoarMaterialIds.kyber,        3, ORDER_COMPAT); // Prismatic (more dmg in light)
        addCompatMetalMaterial(MoarMaterialIds.metallurgium, 3, ORDER_COMPAT); //
        addCompatMetalMaterial(MoarMaterialIds.mythril,      3, ORDER_COMPAT); // Magically Modifiable
        addCompatMetalMaterial(MoarMaterialIds.orichalcum,   3, ORDER_COMPAT); //
        addCompatMetalMaterial(MoarMaterialIds.palladium,    3, ORDER_COMPAT); // Branding V
        addCompatMetalMaterial(MoarMaterialIds.prometheum,   3, ORDER_COMPAT); // Regrowth V
        addCompatMetalMaterial(MoarMaterialIds.quadrillum,   3, ORDER_COMPAT); // Cosmic (inverse of Dwarven)
        addCompatMetalMaterial(MoarMaterialIds.runite,       3, ORDER_COMPAT); // Freezing
        addCompatMetalMaterial(MoarMaterialIds.star_platinum,3, ORDER_COMPAT); // Stellar Swiftness
        addCompatMetalMaterial(MoarMaterialIds.stormyx,      3, ORDER_COMPAT); // Zeus' Wrath
    }
}
