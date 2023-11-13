//package com.konsola5.recipegen;
//
//import com.konsola5.Registry;
//import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.tags.TagKey;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.tags.ItemTags;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.level.ItemLike;
//import org.jetbrains.annotations.Nullable;
//import slimeknights.tconstruct.common.data.tags.ItemTagProvider;
//import slimeknights.tconstruct.tools.TinkerTools;
//
//import java.util.concurrent.CompletableFuture;
//
//import static net.minecraft.tags.ItemTags.CLUSTER_MAX_HARVESTABLES;
//import static slimeknights.tconstruct.common.TinkerTags.Items.*;
//import static wraith.fabricaeexnihilo.modules.ModTags.HAMMERS;
//
//public class HephaestusPlusItemTagProvider extends FabricTagProvider.ItemTagProvider {
//    public HephaestusPlusItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
//        super(output, completableFuture, blockTagProvider);
//    }
//
//    @Override
//    protected void addTags(HolderLookup.Provider arg) {
//        addToolTags(TinkerTools.pickaxe, MULTIPART_TOOL, DURABILITY, HARVEST_PRIMARY, STONE_HARVEST, MELEE, ONE_HANDED, AOE, CLUSTER_MAX_HARVESTABLES, HAMMERS);
//        this.tag(MULTIPART_TOOL).add()
//    }
//
//
//    @SafeVarargs
//    private void addToolTags(ItemLike tool, TagKey<Item>... tags) {
//        Item item = tool.asItem();
//        for (TagKey<Item> tag : tags) {
//            this.tag(tag).add();
//        }
//    }
//}
