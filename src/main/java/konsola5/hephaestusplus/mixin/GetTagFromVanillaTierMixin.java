package konsola5.hephaestusplus.mixin;

import io.github.fabricators_of_create.porting_lib.util.TagUtil;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TagUtil.class)
public class GetTagFromVanillaTierMixin {

    /**
     * @author KonSola5
     * @reason Why, PortingLib, why didn't you use the Fabric's tier convention!
     * Here, I'll do it myself.
     */
    @Overwrite(remap = false)
    public static TagKey<Block> getTagFromVanillaTier(Tiers tier) {
        int level = tier.getLevel();
        if (level > 0) return MiningLevelManager.getBlockTag(level);
        return null;
    }
}
