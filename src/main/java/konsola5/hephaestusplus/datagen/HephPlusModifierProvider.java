package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.HephPlusRegistry;
import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.dynamic.ExtraModifier;
import slimeknights.tconstruct.library.modifiers.dynamic.StatBoostModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import static vazkii.botania.common.handler.PixieHandler.PIXIE_SPAWN_CHANCE;

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
                .multiplyBase(ToolStats.DURABILITY, 0.12f)
                .multiplyBase(ToolStats.ATTACK_DAMAGE, 0.04f)
                .multiplyBase(ToolStats.MINING_SPEED, 0.04f)
                .multiplyBase(ToolStats.VELOCITY, 0.03f)
                .multiplyBase(ToolStats.PROJECTILE_DAMAGE, 0.03f)
                .build());
        addModifier(MoarModifierIds.carmot_shield, StatBoostModifier.builder().attribute(
                "hephaestusplus.modifier.carmot_shield",
                HephPlusRegistry.CARMOT_SHIELD,
                AttributeModifier.Operation.ADDITION,
                2,
                EquipmentSlot.HEAD,
                EquipmentSlot.CHEST,
                EquipmentSlot.LEGS,
                EquipmentSlot.FEET
                )
                .display(ModifierLevelDisplay.NO_LEVELS).build());
        addModifier(MoarModifierIds.carmot_boost, StatBoostModifier.builder().attribute(
                        "hephaestusplus.modifier.carmot_boost",
                        HephPlusRegistry.CARMOT_SHIELD,
                        AttributeModifier.Operation.ADDITION,
                        1,
                        EquipmentSlot.HEAD,
                        EquipmentSlot.CHEST,
                        EquipmentSlot.LEGS,
                        EquipmentSlot.FEET
                )
                .display(ModifierLevelDisplay.DEFAULT).build());
        addModifier(MoarModifierIds.fairy_blessing, StatBoostModifier.builder().attribute(
                        "hephaestusplus.modifier.fairy_blessing",
                        PIXIE_SPAWN_CHANCE,
                        AttributeModifier.Operation.ADDITION,
                        0.03f,
                        EquipmentSlot.MAINHAND
                )
                .display(ModifierLevelDisplay.DEFAULT).build());
    }


}
