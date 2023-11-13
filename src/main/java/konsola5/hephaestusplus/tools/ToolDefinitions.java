package konsola5.hephaestusplus.tools;

import konsola5.hephaestusplus.Registry;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;

public final class ToolDefinitions {
    // rock
    public static final ToolDefinition HAND_HAMMER = ToolDefinition.builder(Registry.handHammer).meleeHarvest().build();
    public static final ToolDefinition CROOK = ToolDefinition.builder(Registry.crook).meleeHarvest().build();
}