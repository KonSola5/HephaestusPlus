package konsola5.hephaestusplus.mixin;

import io.github.fabricators_of_create.porting_lib.util.TierSortingRegistry;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TierSortingRegistry.class)
public class IsCorrectTierVanillaMixin {

    /**
     * @author KonSola5
     * @reason Same thing here - You had an occasion to use the Fabric Mining Tier Manager, but nope.
     * Here, I'll do it myself.
     */
    @Overwrite(remap = false)
    private static boolean isCorrectTierVanilla(Tier tier, BlockState state) {
        // A copy-paste from Fabric's MiningToolItemMixin.java.
        int level = tier.getLevel();
        if (level < MiningLevelManager.getRequiredMiningLevel(state)) {
            return false;
        }
        else return level >= 1 || !state.is(BlockTags.NEEDS_STONE_TOOL);
    }
}
