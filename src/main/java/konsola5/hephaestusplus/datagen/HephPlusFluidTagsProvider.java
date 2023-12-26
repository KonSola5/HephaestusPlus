package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
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
        tagAll(HephPlusFluidRegistry.moltenAdamantite);
        tagAll(HephPlusFluidRegistry.moltenAquarium);
        tagAll(HephPlusFluidRegistry.moltenBanglum);
        tagAll(HephPlusFluidRegistry.moltenCarmot);
        tagAll(HephPlusFluidRegistry.moltenCelestium);
        tagAll(HephPlusFluidRegistry.moltenDurasteel);
        tagAll(HephPlusFluidRegistry.moltenHallowed);
        tagAll(HephPlusFluidRegistry.moltenKyber);
        tagAll(HephPlusFluidRegistry.moltenMetallurgium);
        tagAll(HephPlusFluidRegistry.moltenManganese);
        tagAll(HephPlusFluidRegistry.moltenMythril);
        tagAll(HephPlusFluidRegistry.moltenOrichalcum);
        tagAll(HephPlusFluidRegistry.moltenPalladium);
        tagAll(HephPlusFluidRegistry.moltenPrometheum);
        tagAll(HephPlusFluidRegistry.moltenQuadrillum);
        tagAll(HephPlusFluidRegistry.moltenRunite);
        tagAll(HephPlusFluidRegistry.moltenStarrite);
        tagAll(HephPlusFluidRegistry.moltenStarPlatinum);
        tagAll(HephPlusFluidRegistry.moltenStormyx);
        tagAll(HephPlusFluidRegistry.moltenUnobtainium);

        tagAll(HephPlusFluidRegistry.moltenManasteel);
        tagAll(HephPlusFluidRegistry.moltenElementium);
        tagAll(HephPlusFluidRegistry.moltenTerrasteel);

        this.tag(TinkerTags.Fluids.METAL_TOOLTIPS)
                .addTag(HephPlusFluidRegistry.moltenAdamantite.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenAquarium.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenBanglum.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenCarmot.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenCelestium.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenDurasteel.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenHallowed.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenKyber.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenMetallurgium.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenManganese.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenMythril.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenOrichalcum.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenPalladium.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenPrometheum.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenQuadrillum.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenRunite.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenStarPlatinum.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenStormyx.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenUnobtainium.getForgeTag())
        // Botania
                .addTag(HephPlusFluidRegistry.moltenManasteel.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenElementium.getForgeTag())
                .addTag(HephPlusFluidRegistry.moltenTerrasteel.getForgeTag());

        this.tag(TinkerTags.Fluids.LARGE_GEM_TOOLTIPS)
                .addTag(HephPlusFluidRegistry.moltenStarrite.getForgeTag());
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
