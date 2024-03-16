package konsola5.hephaestusplus.util;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.capability.CompoundIndexHookIterator;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Iterator;

@SuppressWarnings("UnstableApiUsage")
abstract class EnergyModifierHookIterator<I> extends CompoundIndexHookIterator<ToolEnergyCapability.EnergyModifierHook, I> {
    /**
     * Entry from {@link #findHook(IToolStackView, int)}, will be set during or before iteration
     */
    protected ModifierEntry indexEntry = null;

    @Override
    protected int getSize(IToolStackView tool, ToolEnergyCapability.EnergyModifierHook hook) {
        return hook.getBatteries(tool, indexEntry.getModifier());
    }

    /**
     * Fills the tank with the given resource
     *
     * @param tool      Tool to fill
     * @param maxAmount Amount to fill
     * @param tx        Whether to simulate or execute
     * @return Amount filled
     */
    protected long insert(ContainerItemContext context, IToolStackView tool, long maxAmount, TransactionContext tx) {
        int totalFilled = 0;
        Iterator<I> iterator = getIterator(tool); // Iterates through modifiers
        while (iterator.hasNext()) {
            long filled = getHook(iterator.next()).insert(context, tool, indexEntry, maxAmount, tx);
            if (filled > 0) {
                if (filled >= maxAmount) {
                    return totalFilled + filled;
                }
                totalFilled += filled;
                maxAmount -= filled;
            }
        }
        return totalFilled;
    }

    /**
     * Drains the tool of the specified resource
     *
     * @param tool      Tool to drain
     * @param maxAmount Amount to drain
     * @param tx        Whether to simulate or execute
     * @return Drained resource
     */
    public long extract(ContainerItemContext context, IToolStackView tool, long maxAmount, TransactionContext tx) {
        long drainedSoFar = 0;
        Iterator<I> iterator = getIterator(tool);
        while (iterator.hasNext()) {
            // try draining each modifier
            long drained = getHook(iterator.next()).extract(context, tool, indexEntry, maxAmount, tx);
            if (drained != 0) {
                // if we managed to drain something, add it into our current drained stack, and decrease the amount we still want to drain
                if (drainedSoFar <= 0) {
                    if (drained >= maxAmount) {
                        return drained;
                    } else {
                        drainedSoFar = drained;
                    }
                } else {
                    drainedSoFar += drained;
                }
                // if we drained everything desired, return
                maxAmount -= drained;
                if (maxAmount <= 0) {
                    return drainedSoFar;
                }
            }
        }
        return drainedSoFar;
    }
}

