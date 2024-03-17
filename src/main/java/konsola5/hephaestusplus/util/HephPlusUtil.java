package konsola5.hephaestusplus.util;

import konsola5.hephaestusplus.HephaestusPlus;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.IntStream;

public class HephPlusUtil {
    public static final String SECONDS = HephaestusPlus.MOD_ID + ".seconds";
    public static final String ENERGY = HephaestusPlus.MOD_ID + ".energy";
    public static final String ENERGY_PER_TICK = HephaestusPlus.MOD_ID + ".energy_per_tick";

    private static final char[] magnitude = new char[] { 'k', 'M', 'G', 'T' };

    private static final long[] POWERS_OF_ONE_THOUSAND = IntStream.range(1, 4)
            .mapToLong(i -> (long) Math.pow(1000, i)).toArray();

    private static final long[] MAGNITUDE_THRESHOLDS = new long[] {10_000L, 1_000_000L, 1_000_000_000L, 1_000_000_000_000L};

    public static boolean checkPersistentFlag(ItemStack stack, ResourceLocation flag) {
        CompoundTag nbt = stack.getTag();
        if (nbt != null && nbt.contains(ToolStack.TAG_PERSISTENT_MOD_DATA, Tag.TAG_COMPOUND)) {
            return nbt.getCompound(ToolStack.TAG_PERSISTENT_MOD_DATA).getBoolean(flag.toString());
        }
        return false;
    }

    public static String getNumberWithMagnitude(double value, String units) {
        String toReturn = "";
        int chosenMagnitude = -1; // -1 = no magnitude

        for (long x : MAGNITUDE_THRESHOLDS) {
            if (x <= value) {
                chosenMagnitude++;
            } else {
                break;
            }
        }

        if (chosenMagnitude > POWERS_OF_ONE_THOUSAND.length) {
            toReturn += "∞";
        } else {
            if (chosenMagnitude != -1) {
                value /= POWERS_OF_ONE_THOUSAND[chosenMagnitude]; // Get the value to be joint with the magnitude
            }
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ROOT); // Always use dot
            NumberFormat formatter = new DecimalFormat("##.##", symbols); // Round to 2 decimal places

            toReturn += formatter.format(value);

            if (chosenMagnitude != -1) {
                toReturn += magnitude[chosenMagnitude];
            }
        }

        if (!units.isEmpty()) {
            toReturn += " " + units;
        }

        return toReturn;
    }


}
