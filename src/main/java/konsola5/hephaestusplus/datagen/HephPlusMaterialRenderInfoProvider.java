package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.ids.MoarMaterialIds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class HephPlusMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {

    public HephPlusMaterialRenderInfoProvider(FabricDataOutput output, @Nullable AbstractMaterialSpriteProvider materialSprites) {
        super(output, materialSprites);
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(MoarMaterialIds.adamantite).color(0xa30d17).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.aquarium).color(0x3f8ad0).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.banglum).color(0xb06e37).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.carmot).color(0xb6253c).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.celestium).color(0xe9c8ac).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.durasteel).color(0x373737).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.hallowed).color(0xcecee5).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.kyber).color(0xa86ecb).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.metallurgium).color(0x6a2bc7).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.mythril).color(0x5ddaea).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.orichalcum).color(0x95e49c).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.palladium).color(0xdc7223).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.prometheum).color(0x396955).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.quadrillum).color(0x4d5858).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.runite).color(0x0092b1).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.star_platinum).color(0xbbbadc).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.stormyx).color(0xd760d0).fallbacks("metal");

        buildRenderInfo(MoarMaterialIds.livingwood).color(0x36180e).fallbacks("wood");
        buildRenderInfo(MoarMaterialIds.livingrock).color(0xc7c0af).fallbacks("rock");
        buildRenderInfo(MoarMaterialIds.manasteel).color(0x66b7eb).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.elementium).color(0xc342a6).fallbacks("metal");
        buildRenderInfo(MoarMaterialIds.terrasteel).color(0x69e561).fallbacks("metal");

    }

    @Override
    public String getName() {
        return "HephaestusPlus Material Render Info Provider";
    }
}
