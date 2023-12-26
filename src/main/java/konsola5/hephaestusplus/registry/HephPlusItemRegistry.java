package konsola5.hephaestusplus.registry;

import konsola5.hephaestusplus.tools.ToolDefinitions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.TinkerTabs;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static konsola5.hephaestusplus.HephaestusPlus.MOD_ID;

public class HephPlusItemRegistry {
    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(MOD_ID);
    public static final Item.Properties TOOL = new FabricItemSettings().maxCount(1);
    // Properties
    public static final Item.Properties PARTS_PROPS = new Item.Properties();
    public static final Item.Properties SMELTERY_PROPS = new Item.Properties();
    private static final Item.Properties ITEM_PROPS = new Item.Properties();
    public static final ItemObject<Item> carmotReinforcement = ITEMS.register("carmot_reinforcement", ITEM_PROPS);
    // Hephaestus tools
    public static ItemObject<ModifiableItem> handHammer;
    public static ItemObject<ModifiableItem> crook;
    public static ItemObject<ToolPartItem> crookHead;
    public static CastItemObject crookHeadCast;

    static {
        if (FabricLoader.getInstance().isModLoaded("fabricaeexnihilo")) {
            HephPlusItemRegistry.handHammer = HephPlusItemRegistry.ITEMS.register("hand_hammer", () -> new ModifiableItem(HephPlusItemRegistry.TOOL, ToolDefinitions.HAND_HAMMER, TinkerTabs.TAB_TOOLS));
            HephPlusItemRegistry.crook = HephPlusItemRegistry.ITEMS.register("crook", () -> new ModifiableItem(HephPlusItemRegistry.TOOL, ToolDefinitions.CROOK, TinkerTabs.TAB_TOOLS));
        } else {
            HephPlusItemRegistry.handHammer = null;
            HephPlusItemRegistry.crook = null;
        }
    }

    static {
        if (FabricLoader.getInstance().isModLoaded("fabricaeexnihilo")) {
            HephPlusItemRegistry.crookHead = HephPlusItemRegistry.ITEMS.register("crook_head", () -> new ToolPartItem(HephPlusItemRegistry.PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
            HephPlusItemRegistry.crookHeadCast = HephPlusItemRegistry.ITEMS.registerCast("crook_head", HephPlusItemRegistry.SMELTERY_PROPS);
        } else {
            HephPlusItemRegistry.crookHead = null;
            HephPlusItemRegistry.crookHeadCast = null;
        }
    }

    public static void register() {
        ITEMS.register();
    }
}
