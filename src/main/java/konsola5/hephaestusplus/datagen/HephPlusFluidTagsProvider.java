package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.Registry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.data.tags.FluidTagProvider;

import java.util.concurrent.CompletableFuture;

public class HephPlusFluidTagsProvider extends FabricTagProvider.FluidTagProvider {

    public HephPlusFluidTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        tagAll(Registry.moltenAdamantite);
        tagAll(Registry.moltenAquarium);
        tagAll(Registry.moltenBanglum);
        tagAll(Registry.moltenCarmot);
        tagAll(Registry.moltenCelestium);
        tagAll(Registry.moltenDurasteel);
        tagAll(Registry.moltenHallowed);
        tagAll(Registry.moltenKyber);
        tagAll(Registry.moltenMetallurgium);
        tagAll(Registry.moltenMythril);
        tagAll(Registry.moltenOrichalcum);
        tagAll(Registry.moltenPalladium);
        tagAll(Registry.moltenPrometheum);
        tagAll(Registry.moltenQuadrillum);
        tagAll(Registry.moltenRunite);
        tagAll(Registry.moltenStarPlatinum);
        tagAll(Registry.moltenStormyx);

        this.tag(TinkerTags.Fluids.METAL_TOOLTIPS)
                .addTag(Registry.moltenAdamantite.getForgeTag())
                .addTag(Registry.moltenAquarium.getForgeTag())
                .addTag(Registry.moltenBanglum.getForgeTag())
                .addTag(Registry.moltenCarmot.getForgeTag())
                .addTag(Registry.moltenCelestium.getForgeTag())
                .addTag(Registry.moltenDurasteel.getForgeTag())
                .addTag(Registry.moltenHallowed.getForgeTag())
                .addTag(Registry.moltenKyber.getForgeTag())
                .addTag(Registry.moltenMetallurgium.getForgeTag())
                .addTag(Registry.moltenMythril.getForgeTag())
                .addTag(Registry.moltenOrichalcum.getForgeTag())
                .addTag(Registry.moltenPalladium.getForgeTag())
                .addTag(Registry.moltenPrometheum.getForgeTag())
                .addTag(Registry.moltenQuadrillum.getForgeTag())
                .addTag(Registry.moltenRunite.getForgeTag())
                .addTag(Registry.moltenStarPlatinum.getForgeTag())
                .addTag(Registry.moltenStormyx.getForgeTag());
    }

    @Override
    public String getName() {
        return "HephaestusPlus Fluid Tags";
    }

    /** Tags this fluid using local tags */
    private void tagLocal(FluidObject<?> fluid) {
        getOrCreateTagBuilder(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
    }

    /** Tags this fluid with local and forge tags */
    private void tagAll(FluidObject<?> fluid) {
        tagLocal(fluid);
        tag(fluid.getForgeTag()).addTag(fluid.getLocalTag());
    }
}
