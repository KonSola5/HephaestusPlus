package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.MetallurgiumTier;
import konsola5.hephaestusplus.ids.MoarMaterialIds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static net.minecraft.world.item.Tiers.*;

public class HephaestusPlusMaterialStatsProvider extends AbstractMaterialStatsDataProvider {
    public HephaestusPlusMaterialStatsProvider(FabricDataOutput output, AbstractMaterialDataProvider materials) {
        super(output, materials);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Material Stats";
    }

    @Override
    protected void addMaterialStats() {
        // Hephaestus itself doesn't respect Netherite and Metallurgium tiers, but ditto.
       addMaterialStats(MoarMaterialIds.adamantite,
               new HeadMaterialStats(1024, 7.0f, NETHERITE, 5f),
               HandleMaterialStats.DEFAULT.withDurability(1f).withMiningSpeed(0.9f).withAttackDamage(1.2f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.aquarium,
               new HeadMaterialStats(455, 6.5f, IRON, 2f),
               HandleMaterialStats.DEFAULT.withDurability(0.9f).withMiningSpeed(1.1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.banglum,
               new HeadMaterialStats(260, 9f, IRON, 2f),
               HandleMaterialStats.DEFAULT.withDurability(0.6f).withMiningSpeed(1.3f).withAttackDamage(1f).withAttackSpeed(1.2f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.carmot,
               new HeadMaterialStats(1130, 10.5f, DIAMOND, 3f),
               HandleMaterialStats.DEFAULT.withDurability(0.9f).withMiningSpeed(1.2f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.celestium,
               new HeadMaterialStats(2470, 20.0f, MetallurgiumTier.instance, 6f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1.4f).withAttackDamage(1.2f).withAttackSpeed(1.3f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.durasteel,
               new HeadMaterialStats(820, 7.1f, DIAMOND, 3.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.25f).withMiningSpeed(1.05f).withAttackDamage(1.05f).withAttackSpeed(1.05f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.hallowed,
               new HeadMaterialStats(1984, 12.0f, NETHERITE, 5f),
               HandleMaterialStats.DEFAULT.withDurability(1f).withMiningSpeed(1f).withAttackDamage(1.1f).withAttackSpeed(0.85f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.kyber,
               new HeadMaterialStats(889, 7.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.metallurgium,
               new HeadMaterialStats(3000, 15f, MetallurgiumTier.instance, 8f),
               HandleMaterialStats.DEFAULT.withDurability(1.5f).withMiningSpeed(1.25f).withAttackDamage(1.25f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.mythril,
               new HeadMaterialStats(1564, 14.3f, NETHERITE, 3f),
               HandleMaterialStats.DEFAULT.withDurability(1f).withMiningSpeed(1.1f).withAttackDamage(1.1f).withAttackSpeed(1.1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.orichalcum,
               new HeadMaterialStats(2048, 6f, NETHERITE, 4f),
               HandleMaterialStats.DEFAULT.withDurability(1.3f).withMiningSpeed(0.8f).withAttackDamage(1f).withAttackSpeed(0.8f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.palladium,
               new HeadMaterialStats(1234, 8f, NETHERITE, 3.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.prometheum,
               new HeadMaterialStats(1472, 6.0f, DIAMOND, 4f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.quadrillum,
               new HeadMaterialStats(321, 5.5f, IRON, 2.7f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.runite,
               new HeadMaterialStats(1337, 8.9f, DIAMOND, 3.3f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.star_platinum,
               new HeadMaterialStats(1300, 9.0f, NETHERITE, 4f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.stormyx,
               new HeadMaterialStats(1305, 8.5f, DIAMOND, 3.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
    }

}
