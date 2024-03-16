package konsola5.hephaestusplus.modifiers.dynamic;

import com.google.gson.JsonObject;
import konsola5.hephaestusplus.HephaestusPlus;
import konsola5.hephaestusplus.modifiers.BatteryModifier;
import lombok.Setter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.GenericLoaderRegistry.IGenericLoader;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

@SuppressWarnings("deprecation")
public final class ForceFieldModifier extends BatteryModifier {
    private static final Component FORCE_SHIELDED = HephaestusPlus.makeTranslation("modifier", "force_field.force_shielded");
    private final long energyPerTry;
    /**
     * The denominator used in a formula for deducing the energy usage.<br>
     * energyUsage = 10x<sup>(x/denominator)</sup><br>
     * where x is energyPerTry.
     */
    private final double denominator;

    private final double[] chances = {0, 0.3, 0.5, 0.7, 0.8, 0.85, 0.89, 0.92, 0.95, 0.98, 1};
    public ForceFieldModifier(long energyPerTry, double denominator) {
        super();
        this.energyPerTry = energyPerTry;
        this.denominator = denominator;
    }

    @Override
    public int onDamageTool(@NotNull IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
        if (holder instanceof Player) {
            // Fail if the modifier can't extract energy
            if (getEnergyUsage(level) <= getTransferRate() && !tool.isUnbreakable()) {
                // Always extract energy, regardless whether durability damage was avoided or not
                extract(tool, getEnergyUsage(level));
                // Rest of the logic like in ReinforcedModifier
                if (getChance(level) == 1) return 0;
                if (getChance(level) <= 0) return amount;
                int dealt = 0;
                for (int i = 0; i < amount; i++) {
                    if (RANDOM.nextFloat() >= getChance(level)) {
                        dealt++;
                    }
                }
                return dealt;
            }
        }
        return amount;
    }

    @Override
    public int getPriority() {
        return 105; // before Reinforced
    }

    private double getChance(int level) {
        if (level < chances.length) return chances[level];
        else return 1;
    }

    private long getEnergyUsage(int level) {
        return (long) Math.pow(energyPerTry * level, level / denominator);
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        addPercentTooltip(FORCE_SHIELDED, getChance(level), tooltip);
    }

    public static final IGenericLoader<ForceFieldModifier> LOADER = new IGenericLoader<>() {
        @Override
        public ForceFieldModifier deserialize(JsonObject json) {
            long energyPerTry = GsonHelper.getAsLong(json, "energy_per_try", 0);
            long denominator = GsonHelper.getAsLong(json, "denominator", 1);
            return new ForceFieldModifier(energyPerTry, denominator == 0 ? 1 : denominator);
        }

        @Override
        public void serialize(ForceFieldModifier object, JsonObject json) {
            json.addProperty("energy_per_try", object.energyPerTry);
            json.addProperty("denominator", object.denominator);
        }

        @Override
        public ForceFieldModifier fromNetwork(FriendlyByteBuf buffer) {
            long energyPerTry = buffer.readVarLong();
            double denominator = buffer.readDouble();
            return new ForceFieldModifier(energyPerTry, denominator == 0 ? 1 : denominator);
        }

        @Override
        public void toNetwork(ForceFieldModifier object, FriendlyByteBuf buffer) {
            buffer.writeVarLong(object.energyPerTry);
            buffer.writeDouble(object.denominator);
        }
    };

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public IGenericLoader<? extends Modifier> getLoader() {
        return LOADER;
    }

    @Setter
    public static class Builder {
        private long energyPerTry = 0;
        private double denominator = 1;

        public ForceFieldModifier build() {
            return new ForceFieldModifier(energyPerTry, denominator);
        }

        public Builder energyPerTry(long energyPerTry) {
            this.energyPerTry = energyPerTry;
            return this;
        }

        public Builder denominator(double denominator) {
            this.denominator = denominator;
            return this;
        }
    }
}
