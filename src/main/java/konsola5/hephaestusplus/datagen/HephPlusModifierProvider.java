package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.dynamic.ExtraModifier;
import slimeknights.tconstruct.library.modifiers.dynamic.StatBoostModifier;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class HephPlusModifierProvider extends AbstractModifierProvider {
    public HephPlusModifierProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Simple Modifiers";
    }

    @Override
    protected void addModifiers() {
        addModifier(MoarModifierIds.magically_modifiable, ExtraModifier.builder(SlotType.UPGRADE).alwaysShow().build());
        addModifier(MoarModifierIds.stellar_swiftness, StatBoostModifier.builder()
                .multiplyBase(ToolStats.ATTACK_SPEED, 0.15f)
                .build());
        addModifier(MoarModifierIds.durable, StatBoostModifier.builder()
                .multiplyBase(ToolStats.DURABILITY,        0.12f)
                .multiplyBase(ToolStats.ATTACK_DAMAGE,     0.04f)
                .multiplyBase(ToolStats.MINING_SPEED,      0.04f)
                .multiplyBase(ToolStats.VELOCITY,          0.03f)
                .multiplyBase(ToolStats.PROJECTILE_DAMAGE, 0.03f)
                .build());
    }


}
