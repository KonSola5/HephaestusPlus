package konsola5.hephaestusplus.registry;

import net.minecraft.resources.ResourceLocation;

import static konsola5.hephaestusplus.HephaestusPlus.MOD_ID;

public class HephPlusResourceLocations {
    // Resource Locations (for NBT)
    public static final ResourceLocation PROMETHEUM_REPAIRS = new ResourceLocation(MOD_ID, "prometheum_repairs");
    public static final ResourceLocation STORED_SOULS = new ResourceLocation(MOD_ID, "stored_souls");
    public static final ResourceLocation TOOL_OWNER = new ResourceLocation(MOD_ID, "tool_owner");

    public static final ResourceLocation VELOCITY_COOLDOWN = new ResourceLocation(MOD_ID, "velocity_cooldown");
    public static final ResourceLocation VELOCITY_ACTIVE = new ResourceLocation(MOD_ID, "velocity_active");

    public static ResourceLocation MINING_LEVEL_5 = new ResourceLocation("fabric:needs_tool_level_5");
}
