package konsola5.hephaestusplus.util;

import konsola5.hephaestusplus.HephaestusPlus;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class HephPlusUtil {
    public static final String SECONDS = HephaestusPlus.MOD_ID + ".seconds";

    public static boolean checkPersistentFlag(ItemStack stack, ResourceLocation flag) {
        CompoundTag nbt = stack.getTag();
        if (nbt != null && nbt.contains(ToolStack.TAG_PERSISTENT_MOD_DATA, Tag.TAG_COMPOUND)) {
            return nbt.getCompound(ToolStack.TAG_PERSISTENT_MOD_DATA).getBoolean(flag.toString());
        }
        return false;
    }
}
