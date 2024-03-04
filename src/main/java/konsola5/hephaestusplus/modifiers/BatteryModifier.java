package konsola5.hephaestusplus.modifiers;

import konsola5.hephaestusplus.HephaestusPlus;
import konsola5.hephaestusplus.util.ToolEnergyCapability;
import konsola5.hephaestusplus.util.ToolEnergyCapability.EnergyModifierHook;
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
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ValidateModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.*;
import team.reborn.energy.api.EnergyStorage;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <p>Modifier containing the standard battery. Extend to share this battery.</p>
 * <p>Modifier similar to {@link slimeknights.tconstruct.library.modifiers.impl.TankModifier}</p>.
 */
@SuppressWarnings("UnstableApiUsage")
@ParametersAreNonnullByDefault
public class BatteryModifier extends Modifier implements VolatileDataModifierHook, TooltipModifierHook, ValidateModifierHook, ModifierRemovalHook {

    private static final String ENERGY_KEY = HephaestusPlus.makeTranslationKey("modifier", "battery.energy");
    private static final String CAPACITY_KEY = HephaestusPlus.makeTranslationKey("modifier", "battery.capacity");
    private static final String UNIT_KEY = HephaestusPlus.makeTranslationKey("modifier", "battery.unit");

    private static final ResourceLocation OWNER = HephaestusPlus.getResource("battery_owner");
    private static final ResourceLocation CAPACITY = HephaestusPlus.getResource("battery_capacity");
    private static final ResourceLocation ENERGY = HephaestusPlus.getResource("energy");

    private EnergyModifier battery;
    private final long capacity;

    public BatteryModifier(long capacity) {
        this.capacity = capacity;
    }

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        battery = new EnergyModifier();
        hookBuilder
                .addHook(battery, ToolEnergyCapability.HOOK)
                .addHook(TinkerHooks.VOLATILE_DATA)
                .addHook(TinkerHooks.TOOLTIP)
                .addHook(TinkerHooks.VALIDATE)
                .addHook(TinkerHooks.REMOVE);
        super.registerHooks(hookBuilder);
    }

    @Override
    public void addVolatileData(ToolRebuildContext context, ModifierEntry modifier, ModDataNBT volatileData) {
        ResourceLocation ownerKey = getOwnerKey();
        if (ownerKey != null && !volatileData.contains(ownerKey, Tag.TAG_STRING)) {
            volatileData.putString(ownerKey, this.getId().toString());
        }
        ToolEnergyCapability.addBatteries(context, this, volatileData, battery);
        if (capacity > 0) {
            addCapacity(volatileData, capacity * modifier.getLevel());
        }
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (isOwner(tool)) {
            long current = getEnergy(tool);
            tooltip.add(Component.translatable(ENERGY_KEY, current, I18n.get(UNIT_KEY), getCapacity(tool), I18n.get(UNIT_KEY)));
        }
    }

    @Override
    public Component validate(IToolStackView tool, ModifierEntry modifier) {
        if (modifier.getLevel() > 0 && isOwner(tool)) {
            long current = getEnergy(tool);
            if (current != 0) {
                long capacity = getCapacity(tool);
                if (current > capacity) {
                    setCurrentEnergy(tool, capacity);
                }
            }
        }
        return null;
    }

    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        ModDataNBT persistentData = tool.getPersistentData();
        // if no one claims the tank, it either belonged to us or another removed modifier, so clean up data
        if (!persistentData.contains(OWNER, Tag.TAG_STRING)) {
            persistentData.remove(getEnergyKey());
        }
        return null;
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

    /** Gets the capacity of the tank */
    public long getCapacity(IModDataView volatileData) {
        return volatileData.get(getCapacityKey(), CompoundTag::getLong);
    }

    /** Gets the capacity of the tank */
    public long getCapacity(IToolStackView tool) {
        return tool.getVolatileData().get(getCapacityKey(), CompoundTag::getLong);
    }

    public void addCapacity(ModDataNBT volatileNBT, long amount) {
        ResourceLocation key = getCapacityKey();
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

    public long insert(ContainerItemContext context, IToolStackView tool, long currentEnergy, long energyToInsert, TransactionContext tx) {
        long capacity = getCapacity(tool);
        return setCurrentEnergy(context, tool, Math.min(currentEnergy + energyToInsert, capacity), tx);
    }

    public long insert(IToolStackView tool, long currentEnergy, long energyToInsert) {
        long capacity = getCapacity(tool);
        return setCurrentEnergy(tool, Math.min(currentEnergy + energyToInsert, capacity));
    }

    public long insert(IToolStackView tool, long energyToInsert) {
        long capacity = getCapacity(tool);
        return setCurrentEnergy(tool, Math.min(getEnergy(tool) + energyToInsert, capacity));
    }

    public long extract(ContainerItemContext context, IToolStackView tool, long currentEnergy, long energyToExtract, TransactionContext tx) {
        return setCurrentEnergy(context, tool, Math.max(currentEnergy - energyToExtract, 0), tx);
    }

    public long extract(IToolStackView tool, long currentEnergy, long energyToExtract) {
        return setCurrentEnergy(tool, Math.max(currentEnergy - energyToExtract, 0));
    }

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

        @Override
        public long insert(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx) {
            if (isOwner(tool)) {
                long current = getEnergy(tool);
                long remaining = getCapacity(tool) - current;
                if (remaining <= 0) {return 0;}
                long inserted = Math.min(remaining, maxAmount);
                if (inserted > 0) {
                    BatteryModifier.this.insert(context, tool, current, inserted, tx);
                }
                return inserted;
            }
            return 0;
        }

        @Override
        public long extract(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx) {
            if (isOwner(tool)) {
                long current = getEnergy(tool);
                if (current == 0) {return 0;}
                long extracted = Math.min(current, maxAmount);
                BatteryModifier.this.extract(context, tool, current, extracted, tx);
                return extracted;
            }
            return 0;
        }
    }
}