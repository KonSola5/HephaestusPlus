package konsola5.hephaestusplus.tools;

import konsola5.hephaestusplus.HephPlusRegistry;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;

public final class ToolDefinitions {
    // rock
    public static final ToolDefinition HAND_HAMMER = ToolDefinition.builder(HephPlusRegistry.handHammer).meleeHarvest().build();
    public static final ToolDefinition CROOK = ToolDefinition.builder(HephPlusRegistry.crook).meleeHarvest().build();
}