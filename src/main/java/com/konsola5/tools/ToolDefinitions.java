package com.konsola5.tools;

import com.konsola5.Registry;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;

public final class ToolDefinitions {
    // rock
    public static final ToolDefinition HAND_HAMMER = ToolDefinition.builder(Registry.handHammer).meleeHarvest().build();
    public static final ToolDefinition CROOK = ToolDefinition.builder(Registry.crook).meleeHarvest().build();
}