package konsola5.hephaestusplus.tools;

import konsola5.hephaestusplus.registry.HephPlusItemRegistry;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;

public final class ToolDefinitions {
    // rock
    public static final ToolDefinition HAND_HAMMER = ToolDefinition.builder(HephPlusItemRegistry.handHammer).meleeHarvest().build();
    public static final ToolDefinition CROOK = ToolDefinition.builder(HephPlusItemRegistry.crook).meleeHarvest().build();
}