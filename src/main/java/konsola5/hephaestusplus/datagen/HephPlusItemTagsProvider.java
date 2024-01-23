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
import slimeknights.tconstruct.common.registration.CastItemObject;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.minecraft.tags.ItemTags.CLUSTER_MAX_HARVESTABLES;
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
        Consumer<CastItemObject> addCast = cast -> {
            this.tag(GOLD_CASTS).addOptional(BuiltInRegistries.ITEM.getKey(cast.get()));
            this.tag(SAND_CASTS).addOptional(BuiltInRegistries.ITEM.getKey(cast.getSand()));
            this.tag(RED_SAND_CASTS).addOptional(BuiltInRegistries.ITEM.getKey(cast.getRedSand()));
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

}
