package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.HephPlusRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.TinkerTags;

import java.util.concurrent.CompletableFuture;

public class HephPlusFluidTagsProvider extends FabricTagProvider.FluidTagProvider {

    public HephPlusFluidTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        tagAll(HephPlusRegistry.moltenAdamantite);
        tagAll(HephPlusRegistry.moltenAquarium);
        tagAll(HephPlusRegistry.moltenBanglum);
        tagAll(HephPlusRegistry.moltenCarmot);
        tagAll(HephPlusRegistry.moltenCelestium);
        tagAll(HephPlusRegistry.moltenDurasteel);
        tagAll(HephPlusRegistry.moltenHallowed);
        tagAll(HephPlusRegistry.moltenKyber);
        tagAll(HephPlusRegistry.moltenMetallurgium);
        tagAll(HephPlusRegistry.moltenManganese);
        tagAll(HephPlusRegistry.moltenMythril);
        tagAll(HephPlusRegistry.moltenOrichalcum);
        tagAll(HephPlusRegistry.moltenPalladium);
        tagAll(HephPlusRegistry.moltenPrometheum);
        tagAll(HephPlusRegistry.moltenQuadrillum);
        tagAll(HephPlusRegistry.moltenRunite);
        tagAll(HephPlusRegistry.moltenStarrite);
        tagAll(HephPlusRegistry.moltenStarPlatinum);
        tagAll(HephPlusRegistry.moltenStormyx);
        tagAll(HephPlusRegistry.moltenUnobtainium);

        tagAll(HephPlusRegistry.moltenManasteel);
        tagAll(HephPlusRegistry.moltenElementium);
        tagAll(HephPlusRegistry.moltenTerrasteel);

        this.tag(TinkerTags.Fluids.METAL_TOOLTIPS)
                .addTag(HephPlusRegistry.moltenAdamantite.getForgeTag())
                .addTag(HephPlusRegistry.moltenAquarium.getForgeTag())
                .addTag(HephPlusRegistry.moltenBanglum.getForgeTag())
                .addTag(HephPlusRegistry.moltenCarmot.getForgeTag())
                .addTag(HephPlusRegistry.moltenCelestium.getForgeTag())
                .addTag(HephPlusRegistry.moltenDurasteel.getForgeTag())
                .addTag(HephPlusRegistry.moltenHallowed.getForgeTag())
                .addTag(HephPlusRegistry.moltenKyber.getForgeTag())
                .addTag(HephPlusRegistry.moltenMetallurgium.getForgeTag())
                .addTag(HephPlusRegistry.moltenManganese.getForgeTag())
                .addTag(HephPlusRegistry.moltenMythril.getForgeTag())
                .addTag(HephPlusRegistry.moltenOrichalcum.getForgeTag())
                .addTag(HephPlusRegistry.moltenPalladium.getForgeTag())
                .addTag(HephPlusRegistry.moltenPrometheum.getForgeTag())
                .addTag(HephPlusRegistry.moltenQuadrillum.getForgeTag())
                .addTag(HephPlusRegistry.moltenRunite.getForgeTag())
                .addTag(HephPlusRegistry.moltenStarPlatinum.getForgeTag())
                .addTag(HephPlusRegistry.moltenStormyx.getForgeTag())
                .addTag(HephPlusRegistry.moltenUnobtainium.getForgeTag())
        // Botania
                .addTag(HephPlusRegistry.moltenManasteel.getForgeTag())
                .addTag(HephPlusRegistry.moltenElementium.getForgeTag())
                .addTag(HephPlusRegistry.moltenTerrasteel.getForgeTag());

        this.tag(TinkerTags.Fluids.LARGE_GEM_TOOLTIPS)
                .addTag(HephPlusRegistry.moltenStarrite.getForgeTag());
    }

    @Override
    public String getName() {
        return "HephaestusPlus Fluid Tags";
    }

    /**
     * Tags this fluid using local tags
     */
    private void tagLocal(FluidObject<?> fluid) {
        getOrCreateTagBuilder(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
    }

    /**
     * Tags this fluid with local and forge tags
     */
    private void tagAll(FluidObject<?> fluid) {
        tagLocal(fluid);
        tag(fluid.getForgeTag()).addTag(fluid.getLocalTag());
    }
}
