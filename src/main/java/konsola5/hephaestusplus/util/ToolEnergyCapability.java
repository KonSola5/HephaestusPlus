package konsola5.hephaestusplus.util;

import konsola5.hephaestusplus.HephaestusPlus;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHook;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.tools.capability.ToolCapabilityProvider.IToolCapabilityProvider;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import team.reborn.energy.api.EnergyStorage;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * Logic to make a tool an energy handler
 */
@SuppressWarnings("UnstableApiUsage")
public class ToolEnergyCapability extends EnergyModifierHookIterator<ModifierEntry> implements EnergyStorage {

    public static final ResourceLocation TOTAL_BATTERIES = HephaestusPlus.getResource("total_batteries");

    public static final ModifierHook<EnergyModifierHook> HOOK =
            ModifierHooks.register(HephaestusPlus.getResource("energy"), EnergyModifierHook.class, new EnergyModifierHook() {
                @Override
                public int getBatteries(IToolContext tool, Modifier modifier) {
                    return 0;
                }

                @Override
                public EnergyStorage getSlot(IToolStackView tool, ModifierEntry modifier, int battery) {
                    return EnergyStorage.EMPTY;
                }

                @Override
                public long getBatteryCapacity(IToolStackView tool, ModifierEntry modifier, int battery) {
                    return 0;
                }

                @Override
                public long insert(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx) {
                    return 0;
                }

                @Override
                public long extract(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx) {
                    return 0;
                }
            }, EnergyModifierHookMerger::new);

    private final ContainerItemContext container;

    private final Supplier<? extends IToolStackView> tool;

    public ToolEnergyCapability(ContainerItemContext container, Supplier<? extends IToolStackView> tool) {
        this.container = container;
        this.tool = tool;
    }

    public int getSlotCount() {
        return tool.get().getVolatileData().getInt(TOTAL_BATTERIES);
    }

    @Override
    protected Iterator<ModifierEntry> getIterator(IToolStackView tool) {
        return tool.getModifierList().iterator();
    }

    @Override
    protected EnergyModifierHook getHook(ModifierEntry entry) {
        indexEntry = entry;
        return entry.getHook(HOOK);
    }

    public EnergyStorage getSlot(int battery) {
        IToolStackView tool = this.tool.get();
        EnergyModifierHook hook = findHook(tool, battery);
        if (hook != null) {
            return hook.getSlot(tool, indexEntry, battery - startIndex);
        }
        return EnergyStorage.EMPTY;
    }

    // EnergyStorage //
    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        return insert(container, tool.get(), maxAmount, transaction);
    }

    // EnergyStorage //
    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        return extract(container, tool.get(), maxAmount, transaction);
    }

    @Override
    public long getAmount() {
        IToolStackView tool = this.tool.get();
        EnergyModifierHook hook = findHook(tool, 0);
        if (hook != null) {
            return hook.getSlot(tool, indexEntry, startIndex).getAmount();
        }
        return 0;
    }

    @Override
    public long getCapacity() {
        IToolStackView tool = this.tool.get();
        EnergyModifierHook hook = findHook(tool, 0);
        if (hook != null) {
            return hook.getBatteryCapacity(tool, indexEntry, startIndex);
        }
        return 0;
    }

    public static void addBatteries(IToolContext tool, Modifier modifier, ModDataNBT volatileData, EnergyModifierHook hook) {
        volatileData.putInt(TOTAL_BATTERIES, hook.getBatteries(tool, modifier) + volatileData.getInt(TOTAL_BATTERIES));
    }

    public interface EnergyModifierHook {
        default int getBatteries(IToolContext tool, Modifier modifier) {
            return 1;
        }

        EnergyStorage getSlot(IToolStackView tool, ModifierEntry modifier, int slot);

        default long getCurrentEnergy(IToolStackView tool, ModifierEntry modifier, int battery) {
            return 0;
        }

        default long getBatteryCapacity(IToolStackView tool, ModifierEntry modifier, int battery) {
            return 0;
        }

        long insert(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx);

        long extract(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx);
    }

    @ParametersAreNonnullByDefault
    private static class EnergyModifierHookMerger extends EnergyModifierHookIterator<EnergyModifierHook> implements EnergyModifierHook {
        private final Collection<EnergyModifierHook> modules;

        private EnergyModifierHookMerger(Collection<EnergyModifierHook> modules) {
            this.modules = modules;
        }

        @Override
        protected Iterator<EnergyModifierHook> getIterator(IToolStackView tool) {
            return modules.iterator();
        }

        @Override
        protected EnergyModifierHook getHook(EnergyModifierHook entry) {
            return entry;
        }

        @Nullable
        protected EnergyModifierHook findHook(IToolStackView tool, ModifierEntry modifier, int index) {
            indexEntry = modifier;
            return this.findHook(tool, index);
        }

        @Override
        public int getBatteries(IToolContext tool, Modifier modifier) {
            int sum = 0;
            for (EnergyModifierHook module : modules) {
                sum += module.getBatteries(tool, modifier);
            }
            return sum;
        }

        @Override
        public EnergyStorage getSlot(IToolStackView tool, ModifierEntry modifier, int battery) {
            EnergyModifierHook hook = findHook(tool, modifier, battery);
            if (hook != null) {
                return hook.getSlot(tool, modifier, battery - startIndex);
            }
            return EnergyStorage.EMPTY;
        }

        @Override
        public long getBatteryCapacity(IToolStackView tool, ModifierEntry modifier, int battery) {
            EnergyModifierHook hook = findHook(tool, modifier, battery);
            if (hook != null) {
                return hook.getBatteryCapacity(tool, modifier, battery - startIndex);
            }
            return 0;
        }

        @Override
        public long insert(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx) {
            indexEntry = modifier;
            return insert(context, tool, maxAmount, tx);
        }

        @Override
        public long extract(ContainerItemContext context, IToolStackView tool, ModifierEntry modifier, long maxAmount, TransactionContext tx) {
            indexEntry = modifier;
            return extract(context, tool, maxAmount, tx);
        }

    }

    public static class Provider implements IToolCapabilityProvider {
        public Provider(ContainerItemContext stack, Supplier<? extends IToolStackView> toolStack) {

        }
    }

}