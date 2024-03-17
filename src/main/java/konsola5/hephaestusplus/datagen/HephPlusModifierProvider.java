package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.ids.MoarModifierIds;
import konsola5.hephaestusplus.modifiers.dynamic.DynamicBatteryModifier;
import konsola5.hephaestusplus.modifiers.dynamic.ForceFieldModifier;
import konsola5.hephaestusplus.registry.HephPlusAttributes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.ModifierId;
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
        // TODO: Pull off the good old Extra Utils random chance of completely breaking the tool when using Mythril.
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
                        HephPlusAttributes.CARMOT_SHIELD,
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
                        HephPlusAttributes.CARMOT_SHIELD,
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
        addDynamicBattery(MoarModifierIds.red_cell_battery,      10_000L,      32);
        addDynamicBattery(MoarModifierIds.lithium_battery,       100_000L,     128);
        addDynamicBattery(MoarModifierIds.energy_crystal,        1_000_000L,   512);
        addDynamicBattery(MoarModifierIds.lapotron_crystal,      10_000_000L,  2048);
        addDynamicBattery(MoarModifierIds.lapotronic_energy_orb, 100_000_000L, 8192);

        addModifier(MoarModifierIds.force_field, ForceFieldModifier.builder().energyPerTry(10).denominator(3).build());
    }

    private void addDynamicBattery(ModifierId modifierId, long capacity, long transferRate) {
        addModifier(modifierId, DynamicBatteryModifier.builder()
                .capacity(capacity)
                .transferRate(transferRate)
                .build());
    }

}
