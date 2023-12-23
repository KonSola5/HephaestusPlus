package konsola5.hephaestusplus.spritegen;

import konsola5.hephaestusplus.HephaestusPlus;
import konsola5.hephaestusplus.ids.MoarMaterialIds;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToSpriteTransformer;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;
import slimeknights.tconstruct.tools.stats.BowstringMaterialStats;
import slimeknights.tconstruct.tools.stats.RepairKitStats;

public class HephaestusPlusMaterialSpriteProvider extends AbstractMaterialSpriteProvider {
    public String getName() {
        return "HephaestusPlus Materials";
    }

    @Override
    protected void addAllMaterials() {
        buildMaterial(MoarMaterialIds.adamantite)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff150711)
                        .addARGB(102, 0xff2b0720)
                        .addARGB(140, 0xff7c051d)
                        .addARGB(178, 0xff930615)
                        .addARGB(216, 0xffb02514)
                        .addARGB(255, 0xffb04032).build());
        buildMaterial(MoarMaterialIds.aquarium)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff091025)
                        .addARGB(102, 0xff001d4e)
                        .addARGB(140, 0xff033c70)
                        .addARGB(178, 0xff0f518e)
                        .addARGB(216, 0xff206db5)
                        .addARGB(255, 0xff3577b5).build());
        buildMaterial(MoarMaterialIds.banglum)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff0a2531)
                        .addARGB(102, 0xff25312f)
                        .addARGB(140, 0xff4a3d2a)
                        .addARGB(178, 0xff6c4826)
                        .addARGB(216, 0xff8e5322)
                        .addARGB(255, 0xff8e5e36).build());
        buildMaterial(MoarMaterialIds.carmot)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff441238)
                        .addARGB(102, 0xff5f1d51)
                        .addARGB(140, 0xff841e4c)
                        .addARGB(178, 0xffb62f44)
                        .addARGB(216, 0xffca3e62)
                        .addARGB(255, 0xffe5438a).build());
        buildMaterial(MoarMaterialIds.celestium)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff41052c)
                        .addARGB(102, 0xff5e163a)
                        .addARGB(140, 0xffd8636b)
                        .addARGB(178, 0xffd8636b)
                        .addARGB(216, 0xffe5958d)
                        .addARGB(255, 0xffe9c8ac).build());
        buildMaterial(MoarMaterialIds.durasteel)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff0c0c0c)
                        .addARGB(102, 0xff121212)
                        .addARGB(140, 0xff242424)
                        .addARGB(178, 0xff313131)
                        .addARGB(216, 0xff4d4d4d)
                        .addARGB(255, 0xff666666).build());
        buildMaterial(MoarMaterialIds.hallowed)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff25242d)
                        .addARGB(102, 0xff33323c)
                        .addARGB(140, 0xff9090a3)
                        .addARGB(178, 0xffa8a8be)
                        .addARGB(216, 0xffcecee5)
                        .addARGB(255, 0xffe1e1ee).build());
        buildMaterial(MoarMaterialIds.kyber)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff2a1452)
                        .addARGB(102, 0xff4d2e75)
                        .addARGB(140, 0xff8f58c7)
                        .addARGB(178, 0xff9561bb)
                        .addARGB(216, 0xffa86ecb)
                        .addARGB(255, 0xffcf9aee).build());
        buildMaterial(MoarMaterialIds.metallurgium)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff070531)
                        .addARGB(102, 0xff1b0a48)
                        .addARGB(140, 0xff390790)
                        .addARGB(178, 0xff4f15aa)
                        .addARGB(216, 0xff6a2bc7)
                        .addARGB(255, 0xff8b42ee).build());
        buildMaterial(MoarMaterialIds.mythril)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff25152b)
                        .addARGB(102, 0xff171744)
                        .addARGB(140, 0xff1e3971)
                        .addARGB(178, 0xff317cbb)
                        .addARGB(216, 0xff5ddaea)
                        .addARGB(255, 0xff97ecd6).build());
        buildMaterial(MoarMaterialIds.orichalcum)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff101b22)
                        .addARGB(102, 0xff1d3135)
                        .addARGB(140, 0xff315d52)
                        .addARGB(178, 0xff46835a)
                        .addARGB(216, 0xff70cd68)
                        .addARGB(255, 0xffafe6ab).build());
        buildMaterial(MoarMaterialIds.palladium)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff5a0e09)
                        .addARGB(102, 0xff83280d)
                        .addARGB(140, 0xffb35312)
                        .addARGB(178, 0xffd46d15)
                        .addARGB(216, 0xffe09123)
                        .addARGB(255, 0xffe9bf35).build());
        buildMaterial(MoarMaterialIds.prometheum)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff092831)
                        .addARGB(102, 0xff09302b)
                        .addARGB(140, 0xff135f3c)
                        .addARGB(178, 0xff208047)
                        .addARGB(216, 0xff3eaf50)
                        .addARGB(255, 0xff83cc70).build());
        buildMaterial(MoarMaterialIds.quadrillum)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff020303)
                        .addARGB(102, 0xff151919)
                        .addARGB(140, 0xff2d3535)
                        .addARGB(178, 0xff303838)
                        .addARGB(216, 0xff3a4242)
                        .addARGB(255, 0xff4d5959).build());
        buildMaterial(MoarMaterialIds.runite)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff002131)
                        .addARGB(102, 0xff003849)
                        .addARGB(140, 0xff004459)
                        .addARGB(178, 0xff00617c)
                        .addARGB(216, 0xff00a2c1)
                        .addARGB(255, 0xff00d5d8).build());
        buildMaterial(MoarMaterialIds.star_platinum)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff302a52)
                        .addARGB(102, 0xff373072)
                        .addARGB(140, 0xff8076ff)
                        .addARGB(178, 0xff9891c8)
                        .addARGB(216, 0xffbbbadc)
                        .addARGB(255, 0xffeaeecf).build());
        ResourceLocation baseTexture = HephaestusPlus.getResource("item/materials/generator/stormyx");
        ResourceLocation highlightTexture = HephaestusPlus.getResource("item/materials/generator/stormyx_highlight");
        ResourceLocation borderTexture = HephaestusPlus.getResource("item/materials/generator/stormyx_border");
        buildMaterial(MoarMaterialIds.stormyx)
                .meleeHarvest().ranged()
                .fallbacks("metal")
                .transformer(GreyToSpriteTransformer.builderFromBlack()
                        .addTexture(63, borderTexture, 0xFFC8C8C8).addTexture(102, borderTexture)
                        .addTexture(140, baseTexture, 0xFFE1E1E1).addTexture(178, baseTexture)
                        .addTexture(216, highlightTexture, 0xFFE1E1E1).addTexture(255, highlightTexture)
                        .build());

        buildMaterial(MoarMaterialIds.livingrock)
                .meleeHarvest()
                .fallbacks("rock")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff8e8574)
                        .addARGB(102, 0xff9d9583)
                        .addARGB(140, 0xffb8af9e)
                        .addARGB(178, 0xffc0b7a6)
                        .addARGB(216, 0xffc9c2b2)
                        .addARGB(255, 0xffd1cbba).build());
        buildMaterial(MoarMaterialIds.livingwood)
                .meleeHarvest()
                .fallbacks("wood", "stick", "primitive")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff380a04)
                        .addARGB(102, 0xff420e05)
                        .addARGB(140, 0xff4b190a)
                        .addARGB(178, 0xff511e0b)
                        .addARGB(216, 0xff562210)
                        .addARGB(255, 0xff5e2409).build());
        buildMaterial(MoarMaterialIds.manasteel)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff25128a)
                        .addARGB(102, 0xff1f20b9)
                        .addARGB(140, 0xff3a63da)
                        .addARGB(178, 0xff4f72eb)
                        .addARGB(216, 0xff67b9ef)
                        .addARGB(255, 0xffb5fffb).build());
        buildMaterial(MoarMaterialIds.manastring)
                .statType(BowstringMaterialStats.ID, RepairKitStats.ID)
                .fallbacks("primitive")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff004a66)
                        .addARGB(102, 0xff007289)
                        .addARGB(140, 0xff9bfcf1)
                        .addARGB(178, 0xff9bfcf1)
                        .addARGB(216, 0xffcdfcf7)
                        .addARGB(255, 0xffffffff).build());
        buildMaterial(MoarMaterialIds.elementium)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff431a5f)
                        .addARGB(102, 0xff773188)
                        .addARGB(140, 0xffc342a6)
                        .addARGB(178, 0xffdd82a3)
                        .addARGB(216, 0xfff2c5c2)
                        .addARGB(255, 0xfffcf8f6).build());
        buildMaterial(MoarMaterialIds.terrasteel)
                .meleeHarvest().statType(TinkerPartSpriteProvider.PLATE)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xff043b1c)
                        .addARGB(102, 0xff0c7127)
                        .addARGB(140, 0xff69e561)
                        .addARGB(178, 0xff2ab73a)
                        .addARGB(216, 0xffcafcb3)
                        .addARGB(255, 0xffcafcb3).build());
    }
}
