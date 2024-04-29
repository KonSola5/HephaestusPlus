package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.registry.HephPlusItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.registration.CastItemObject;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.minecraft.tags.ItemTags.CLUSTER_MAX_HARVESTABLES;
import static net.minecraft.tags.ItemTags.PIGLIN_LOVED;
import static slimeknights.tconstruct.common.TinkerTags.Items.*;
import static wraith.fabricaeexnihilo.modules.ModTags.CROOKS;
import static wraith.fabricaeexnihilo.modules.ModTags.HAMMERS;

public class HephPlusItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public HephPlusItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        addToolTags(HephPlusItemRegistry.crook, MULTIPART_TOOL, DURABILITY, HARVEST_PRIMARY, HARVEST, MELEE, HELD, AOE, CLUSTER_MAX_HARVESTABLES, CROOKS);
        addToolTags(HephPlusItemRegistry.handHammer, MULTIPART_TOOL, DURABILITY, HARVEST_PRIMARY, STONE_HARVEST, MELEE, HELD, AOE, CLUSTER_MAX_HARVESTABLES, HAMMERS);
        FabricTagBuilder goldCasts = this.tag(TinkerTags.Items.GOLD_CASTS);
        FabricTagBuilder sandCasts = this.tag(TinkerTags.Items.SAND_CASTS);
        FabricTagBuilder redSandCasts = this.tag(TinkerTags.Items.RED_SAND_CASTS);
        FabricTagBuilder singleUseCasts = this.tag(TinkerTags.Items.SINGLE_USE_CASTS);
        FabricTagBuilder multiUseCasts = this.tag(TinkerTags.Items.MULTI_USE_CASTS);
        Consumer<CastItemObject> addCast = cast -> {
            // tag based on material
            goldCasts.addOptional(BuiltInRegistries.ITEM.getKey(cast.get()));
            sandCasts.addOptional(BuiltInRegistries.ITEM.getKey(cast.getSand()));
            redSandCasts.addOptional(BuiltInRegistries.ITEM.getKey(cast.getRedSand()));
            // tag based on usage
            singleUseCasts.addOptionalTag(cast.getSingleUseTag());
            this.tag(cast.getSingleUseTag()).addOptional(BuiltInRegistries.ITEM.getKey(cast.getSand()));
            this.tag(cast.getSingleUseTag()).addOptional(BuiltInRegistries.ITEM.getKey(cast.getRedSand()));
            multiUseCasts.addOptionalTag(cast.getMultiUseTag());
            this.tag(cast.getMultiUseTag()).addOptional(BuiltInRegistries.ITEM.getKey(cast.get()));
        };

        addCast.accept(HephPlusItemRegistry.crookHeadCast);
    }


    @SafeVarargs
    private void addToolTags(ItemLike tool, TagKey<Item>... tags) {
        Item item = tool.asItem();
        for (TagKey<Item> tag : tags) {
            this.tag(tag).addOptional(BuiltInRegistries.ITEM.getKey(item));
        }
    }

    @Override
    public String getName() {
        return "HephaestusPlus Item Tags";
    }

    public FabricTagBuilder tag(TagKey<Item> tag) {
        return getOrCreateTagBuilder(tag);
    }

}
