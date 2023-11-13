package com.konsola5.datagen;

import com.konsola5.ids.MoarMaterialIds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static net.minecraft.world.item.Tiers.DIAMOND;

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
       addMaterialStats(MoarMaterialIds.adamantite,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.aquarium,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.banglum,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.carmot,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.celestium,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.durasteel,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.hallowed,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.kyber,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.metallurgium,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.mythril,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.orichalcum,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.palladium,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.prometheum,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.quadrillum,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.runite,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.star_platinum,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);
       addMaterialStats(MoarMaterialIds.stormyx,
               new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
               HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f).withAttackSpeed(1f),
               ExtraMaterialStats.DEFAULT);

//        addMaterialStats(MaterialIds.prometheum,
//                new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
//                HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f),
//                ExtraMaterialStats.DEFAULT);
//        addMaterialStats(MaterialIds.palladium,
//                new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
//                HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f),
//                ExtraMaterialStats.DEFAULT);
//        addMaterialStats(MaterialIds.carmot,
//                new HeadMaterialStats(800, 5.0f, DIAMOND, 2.5f),
//                HandleMaterialStats.DEFAULT.withDurability(1.10f).withMiningSpeed(1f).withAttackDamage(1f),
//                ExtraMaterialStats.DEFAULT);
    }

}
