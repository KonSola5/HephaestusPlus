package konsola5.hephaestusplus.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.TooltipKey;
import slimeknights.tconstruct.library.utils.Util;
import slimeknights.tconstruct.tools.TinkerModifiers;

import javax.annotation.Nullable;
import java.util.List;

public class SolidModifier extends Modifier {
    public static int damageSolid(int amount, double percentage) {

        // 100% protection? all damage blocked
        if (percentage >= 1) {
            return 0;
        }
        // 0% protection? nothing blocked
        if (percentage <= 0) {
            return amount;
        }

        int dealt = 0;
        for (int i = 0; i < amount; i++) {
            if (RANDOM.nextFloat() >= percentage) {
                dealt++;
            }
        }
        return dealt;
    }

    public static double getPercentage(IToolStackView tool, int level) {
        int durability = tool.getCurrentDurability();
        int damage = tool.getDamage();
        int total = durability + damage;

        double n = (double) level / 2;

        double usage = 100 * (double) tool.getCurrentDurability()/((double)tool.getCurrentDurability()+(double)tool.getDamage());
        // Curved scaling towards lower durabilities on lvl 1, Linear scaling on lvl 2, higher levels bias scaling towards higher durabilities
        return Math.max(0.01*(50-Math.pow(Math.pow(2,1/n)*0.05*usage*Math.pow(0.2,(n-2)/n),n)),0);
    }

    @Override
    public int onDamageTool(IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
        return damageSolid(amount, getPercentage(tool, level));
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        double solid;
        if (tool.getModifierLevel(TinkerModifiers.unbreakable.get()) > 0) {
            solid = 1; // Shouldn't happen, since the maximum percentage will be 50%
        } else {
            solid = getPercentage(tool, level);
        }
        if (solid >= 0.01) {
            tooltip.add(applyStyle(Component.literal(Util.PERCENT_FORMAT.format(solid) + " ").append(makeDisplayName())));
        }
    }
}
