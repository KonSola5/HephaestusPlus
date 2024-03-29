package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.HephaestusPlus;
import konsola5.hephaestusplus.util.HephPlusUtil;
import konsola5.hephaestusplus.util.ToolEnergyCapability;
import konsola5.hephaestusplus.util.ToolEnergyCapability.EnergyModifierHook;
import lombok.Getter;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.*;
import team.reborn.energy.api.EnergyStorage;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <p>Modifier containing the standard battery. Extend to share this battery.</p>
 * <p>Modifier similar to {@link slimeknights.tconstruct.library.modifiers.impl.TankModifier}.</p>
 * <p>May be split into a separate mod if some mod author wants to use Energy on Hephaestus tools too.</p>
 */
@SuppressWarnings({"UnstableApiUsage", "deprecation"})
@ParametersAreNonnullByDefault
public class BatteryModifier extends Modifier {

    private static final String ENERGY_KEY = HephaestusPlus.makeTranslationKey("modifier", "battery.energy");
    private static final String TRANSFER_RATE_KEY = HephaestusPlus.makeTranslationKey("modifier", "battery.transfer_rate");
    private static final String UNIT_KEY = HephaestusPlus.makeTranslationKey("modifier", "battery.unit");
    private static final String UNIT_PER_TICK_KEY = HephaestusPlus.makeTranslationKey("modifier", "battery.unit_per_tick");

    private static final ResourceLocation OWNER = HephaestusPlus.getResource("battery_owner");
    private static final ResourceLocation CAPACITY = HephaestusPlus.getResource("battery_capacity");
    private static final ResourceLocation ENERGY = HephaestusPlus.getResource("energy");
    private static final ResourceLocation TRANSFER_RATE = HephaestusPlus.getResource("transfer_rate");

    private EnergyModifier battery;
    @Getter
    private final long capacity;
    @Getter
    private final long transferRate;

    /**
     * Initializes a Battery Modifier with fixed capacity and transfer rate.
     * @param capacity The battery capacity (in E).
     * @param transferRate The battery transfer rate (in E/t).
     */
    public BatteryModifier(long capacity, long transferRate) {
        this.capacity = capacity;
        this.transferRate = transferRate;
    }

    /**
     * Initializes a Battery Modifier with 0 E capacity and 0 E/t transfer rate.<br>
     * Great for modifiers extending this modifier that just want to utilize Energy from batteries.
     */
    public BatteryModifier() {
        this(0, 0);
    }

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        battery = new EnergyModifier();
        hookBuilder.addHook(battery, ToolEnergyCapability.HOOK);
    }

    @Override
    public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
        ResourceLocation ownerKey = getOwnerKey();
        if (ownerKey != null && !volatileData.contains(ownerKey, Tag.TAG_STRING)) {
            volatileData.putString(ownerKey, this.getId().toString());
        }
        ToolEnergyCapability.addBatteries(context, this, volatileData, battery);
        if (capacity > 0) {
            addCapacity(volatileData, capacity * level);
        }
        if (transferRate > 0) {
            addTransferRate(volatileData, transferRate * level);
        }
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (isOwner(tool)) {
            long currentEnergy = getEnergy(tool);
            long currentTransferRate = getTransferRate(tool);
            tooltip.add(Component.translatable(ENERGY_KEY,
                    HephPlusUtil.getNumberWithMagnitude(currentEnergy, I18n.get(HephPlusUtil.ENERGY)),
                    HephPlusUtil.getNumberWithMagnitude(getCapacity(tool), I18n.get(HephPlusUtil.ENERGY))));
            tooltip.add(Component.translatable(TRANSFER_RATE_KEY,
                    HephPlusUtil.getNumberWithMagnitude(currentTransferRate, I18n.get(HephPlusUtil.ENERGY_PER_TICK))));
        }
    }

    @Override
    public ValidatedResult validate(IToolStackView tool, int level) {
        if (level > 0 && isOwner(tool)) {
            long current = getEnergy(tool);
            if (current != 0) {
                long capacity = getCapacity(tool);
                if (current > capacity) {
                    setCurrentEnergy(tool, capacity);
                }
            }
        }

        return ValidatedResult.PASS;
    }

    @Override
    public void onRemoved(IToolStackView tool) {
        ModDataNBT persistentData = tool.getPersistentData();
        // if no one claims the battery, it either belonged to us or another removed modifier, so clean up data
        if (!persistentData.contains(OWNER, Tag.TAG_STRING)) {
            persistentData.remove(getEnergyKey());
        }
    }

    @Nullable
    public ResourceLocation getOwnerKey() {
        return OWNER;
    }

    public ResourceLocation getCapacityKey() {
        return CAPACITY;
    }

    public ResourceLocation getEnergyKey() {
        return ENERGY;
    }

    public ResourceLocation getTransferRateKey() {
        return TRANSFER_RATE;
    }

    public boolean isOwner(IModDataView volatileData) {
        ResourceLocation key = getOwnerKey();
        if (key == null) {
            return true;
        }
        return this.getId().toString().equals(volatileData.getString(key));
    }

    public boolean isOwner(IToolStackView tool) {
        return isOwner(tool.getVolatileData());
    }

    /**
     * Gets the capacity of the battery
     */
    public long getCapacity(IModDataView volatileData) {
        return volatileData.get(getCapacityKey(), CompoundTag::getLong);
    }

    /**
     * Gets the capacity of the battery
     */
    public long getCapacity(IToolStackView tool) {
        return tool.getVolatileData().get(getCapacityKey(), CompoundTag::getLong);
    }

    /**
     * Gets the transfer rate of the battery
     */
    public long getTransferRate(IModDataView volatileData) {
        return volatileData.get(getTransferRateKey(), CompoundTag::getLong);
    }

    /**
     * Gets the transfer rate of the battery
     */
    public long getTransferRate(IToolStackView tool) {
        return tool.getVolatileData().get(getTransferRateKey(), CompoundTag::getLong);
    }

    public void addCapacity(ModDataNBT volatileNBT, long amount) {
        ResourceLocation key = getCapacityKey();
        if (volatileNBT.contains(key, Tag.TAG_ANY_NUMERIC)) {
            amount += volatileNBT.get(key, CompoundTag::getLong);
        }
        volatileNBT.putLong(key, amount);
    }

    public void addTransferRate(ModDataNBT volatileNBT, long amount) {
        ResourceLocation key = getTransferRateKey();
        if (volatileNBT.contains(key, Tag.TAG_ANY_NUMERIC)) {
            amount += volatileNBT.get(key, CompoundTag::getLong);
        }
        volatileNBT.putLong(key, amount);
    }

    public long getEnergy(IToolStackView tool) {
        return tool.getPersistentData().get(getEnergyKey(), CompoundTag::getLong);
    }

    public long setCurrentEnergy(ContainerItemContext context, IToolStackView tool, long energy, TransactionContext tx) {
        long capacity = getCapacity(tool);
        if (energy > capacity) {
            energy = capacity;
        }
        ItemStack newStack = context.getItemVariant().toStack();
        ToolStack toolStackCopy = ToolStack.copyFrom(newStack);
        toolStackCopy.getPersistentData().putLong(getEnergyKey(), energy);
        newStack.setTag(toolStackCopy.getNbt());

        if (context.exchange(ItemVariant.of(newStack), 1, tx) != 1) {
            HephaestusPlus.LOGGER.error("Failed to set current energy for the Battery modifier!");
        }
        return energy;
    }

    public long setCurrentEnergy(IToolStackView tool, long energy) {
        long capacity = getCapacity(tool);
        if (energy > capacity) {
            energy = capacity;
        }
        tool.getPersistentData().putLong(getEnergyKey(), energy);
        return energy;
    }

    /**
     * Inserts energy into the tool.
     * @param context Current container item.
     * @param tool The tool that the modifier is applied on.
     * @param currentEnergy Current amount of energy stored in the tool.
     * @param energyToInsert Energy to insert.
     * @param tx Current transaction.
     * @return The amount of energy inserted.
     */
    public long insert(ContainerItemContext context, IToolStackView tool, long currentEnergy, long energyToInsert, TransactionContext tx) {
        long capacity = getCapacity(tool);
        return setCurrentEnergy(context, tool, Math.min(currentEnergy + energyToInsert, capacity), tx);
    }

    /**
     * Inserts energy into the tool.
     * @param tool The tool that the modifier is applied on.
     * @param currentEnergy Current amount of energy stored in the tool.
     * @param energyToInsert Energy to insert.
     * @return The amount of energy inserted.
     */
    public long insert(IToolStackView tool, long currentEnergy, long energyToInsert) {
        long capacity = getCapacity(tool);
        return setCurrentEnergy(tool, Math.min(currentEnergy + energyToInsert, capacity));
    }

    /**
     * Inserts energy into the tool.
     * @param tool The tool that the modifier is applied on.
     * @param energyToInsert Energy to insert.
     * @return The amount of energy inserted.
     */
    public long insert(IToolStackView tool, long energyToInsert) {
        long capacity = getCapacity(tool);
        return setCurrentEnergy(tool, Math.min(getEnergy(tool) + energyToInsert, capacity));
    }

    /**
     * Extracts energy from the tool.
     * @param context Current container item.
     * @param tool The tool that the modifier is applied on.
     * @param currentEnergy Current amount of energy stored in the tool.
     * @param energyToExtract Energy to extract.
     * @param tx Current transaction.
     * @return The amount of energy extracted.
     */
    public long extract(ContainerItemContext context, IToolStackView tool, long currentEnergy, long energyToExtract, TransactionContext tx) {
        return setCurrentEnergy(context, tool, Math.max(currentEnergy - energyToExtract, 0), tx);
    }

    /**
     * Extracts energy from the tool.
     * @param tool The tool that the modifier is applied on.
     * @param currentEnergy Current amount of energy stored in the tool.
     * @param energyToExtract Energy to extract.
     * @return The amount of energy extracted.
     */
    public long extract(IToolStackView tool, long currentEnergy, long energyToExtract) {
        return setCurrentEnergy(tool, Math.max(currentEnergy - energyToExtract, 0));
    }

    /**
     * Extracts energy from the tool.
     * @param tool The tool that the modifier is applied on.
     * @param energyToExtract Energy to extract.
     * @return The amount of energy extracted.
     */
    public long extract(IToolStackView tool, long energyToExtract) {
        return setCurrentEnergy(tool, Math.max(getEnergy(tool) - energyToExtract, 0));
    }

    @SuppressWarnings("UnstableApiUsage")
    public class EnergyModifier implements EnergyModifierHook {

        @Override
        public int getBatteries(IToolContext tool, Modifier modifier) {
            return isOwner(tool.getVolatileData()) ? 1 : 0;
        }

        @Override
        public EnergyStorage getSlot(IToolStackView tool, ModifierEntry modifier, int slot) {
            throw new RuntimeException("TODO: Not supported yet");
        }

        @Override
        public long getCurrentEnergy(IToolStackView tool, ModifierEntry modifier, int battery) {
            return isOwner(tool) ? getEnergy(tool) : 0;
        }

        @Override
        public long getBatteryCapacity(IToolStackView tool, ModifierEntry modifier, int battery) {
            return isOwner(tool) ? getCapacity(tool) : 0;
        }

        /**
         * Inserts energy into the tool.
         * @param context Current container item.
         * @param tool The tool that the modifier is applied on.
         * @param modifier Current modifier.
         * @param maxAmount Energy to insert.
         * @param tx Current transaction.
         * @return The amount of energy inserted.
         */
        @Override
        public long insert(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx) {
            if (isOwner(tool)) {
                long current = getEnergy(tool);
                long remaining = getCapacity(tool) - current;
                if (remaining <= 0) {
                    return 0;
                }
                long inserted = Math.min(getTransferRate(tool), Math.min(remaining, maxAmount));
                if (inserted > 0) {
                    BatteryModifier.this.insert(context, tool, current, inserted, tx);
                }
                return inserted;
            }
            return 0;
        }

        /**
         * Extracts energy from the tool.
         * @param context Current container item.
         * @param tool The tool that the modifier is applied on.
         * @param modifier Current modifier.
         * @param maxAmount Energy to extract.
         * @param tx Current transaction.
         * @return The amount of energy extracted.
         */
        @Override
        public long extract(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx) {
            if (isOwner(tool)) {
                long current = getEnergy(tool);
                if (current == 0) {
                    return 0;
                }
                long extracted = Math.min(getTransferRate(tool), Math.min(current, maxAmount));
                BatteryModifier.this.extract(context, tool, current, extracted, tx);
                return extracted;
            }
            return 0;
        }
    }
}