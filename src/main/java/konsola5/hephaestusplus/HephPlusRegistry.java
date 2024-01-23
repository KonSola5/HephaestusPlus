package konsola5.hephaestusplus;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import konsola5.hephaestusplus.datagen.*;
import konsola5.hephaestusplus.registry.HephPlusAttributes;
import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import konsola5.hephaestusplus.registry.HephPlusItemRegistry;
import konsola5.hephaestusplus.registry.HephPlusModifierRegistry;
import konsola5.hephaestusplus.spritegen.HephPlusMaterialRenderInfoProvider;
import konsola5.hephaestusplus.spritegen.HephaestusPlusMaterialSpriteProvider;
import konsola5.hephaestusplus.spritegen.HephaestusPlusPartSpriteProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import slimeknights.tconstruct.library.client.data.TinkerSpriteSourceGenerator;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import static konsola5.hephaestusplus.HephaestusPlus.MOD_ID;

public class HephPlusRegistry {

    public static void init() {
        HephPlusModifierRegistry.register();
        HephPlusItemRegistry.register();
        HephPlusFluidRegistry.register();

        Registry.register(BuiltInRegistries.ATTRIBUTE, HephaestusPlus.getResource("carmot_shield"), HephPlusAttributes.CARMOT_SHIELD);
    }

    public static void gatherData(FabricDataGenerator.Pack pack, ExistingFileHelper existingFileHelper) {

        FabricTagProvider.BlockTagProvider blockTags = pack.addProvider(HephPlusBlockTagsProvider::new); // Required, despite not adding any blocks yet.
        pack.addProvider((output, registriesFuture) -> new HephPlusItemTagsProvider(output, registriesFuture, blockTags));
        pack.addProvider(HephPlusFluidTagsProvider::new);

        pack.addProvider(HephPlusToolRecipeProvider::new);
        pack.addProvider(HephPlusMaterialRecipeProvider::new);
        pack.addProvider(HephPlusStationSlotLayoutProvider::new);
        pack.addProvider(HephPlusModifierRecipeProvider::new);
        pack.addProvider(HephPlusSmelteryRecipeProvider::new);
        pack.addProvider(HephPlusToolDefinitionProvider::new);
        pack.addProvider(HephPlusModifierProvider::new);

        HephPlusMaterialDataProvider materials = pack.addProvider(HephPlusMaterialDataProvider::new);
        pack.addProvider((output, registriesFuture) -> new HephPlusMaterialStatsProvider(output, materials));
        pack.addProvider((output, registriesFuture) -> new HephPlusMaterialTraitsProvider(output, materials));
        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
        HephaestusPlusMaterialSpriteProvider moreToolMats = new HephaestusPlusMaterialSpriteProvider();
        TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();
        HephaestusPlusPartSpriteProvider morePartSprites = new HephaestusPlusPartSpriteProvider();
        //pack.addProvider((output, registriesFuture) -> new MaterialRenderInfoProvider(output, materialSprites));
        //pack.addProvider((output, registriesFuture) -> new GeneratorPartTextureJsonGenerator(output, HephaestusPlus.MOD_ID, partSprites));
        pack.addProvider((output, registriesFuture) -> new HephPlusMaterialRenderInfoProvider(output, moreToolMats));
        pack.addProvider((output, registriesFuture) -> new GeneratorPartTextureJsonGenerator(output, MOD_ID, morePartSprites));
        //Tinkers' materials for HephaestusPlus parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, morePartSprites, materialSprites));
        //HephaestusPlus materials for Tinkers' parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, partSprites, moreToolMats));
        //HephaestusPlus materials for HephaestusPlus parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, morePartSprites, moreToolMats));
        pack.addProvider((output, registriesFuture) -> new TinkerSpriteSourceGenerator(output, existingFileHelper));
    }

}
