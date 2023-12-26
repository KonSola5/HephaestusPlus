package konsola5.hephaestusplus.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import slimeknights.mantle.fluid.attributes.FluidAttributes;
import slimeknights.mantle.registration.ModelFluidAttributes;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.util.SimpleFlowableFluid;

import static konsola5.hephaestusplus.HephaestusPlus.MOD_ID;

public class HephPlusFluidRegistry {

    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MOD_ID);

    private static FluidAttributes.Builder hotBuilder() {
        return ModelFluidAttributes.builder().density(2000).viscosity(10000).temperature(1000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
    }
    
    @SuppressWarnings("SameParameterValue")
    private static FluidObject<SimpleFlowableFluid> hotLiquid(FluidDeferredRegister registry, String name, int temperature) {
        return registry.register(
                name,
                hotBuilder().temperature(temperature),
                properties -> properties.mapColor(MapColor.FIRE).replaceable().pushReaction(PushReaction.DESTROY).liquid(),
                12);
    }

    public static final FluidObject<SimpleFlowableFluid> moltenAdamantite   = hotLiquid(FLUIDS, "molten_adamantite"   ,1000);
    public static final FluidObject<SimpleFlowableFluid> moltenAquarium     = hotLiquid(FLUIDS, "molten_aquarium"     ,1000);
    public static final FluidObject<SimpleFlowableFluid> moltenBanglum      = hotLiquid(FLUIDS, "molten_banglum"      ,1070);
    public static final FluidObject<SimpleFlowableFluid> moltenCarmot       = hotLiquid(FLUIDS, "molten_carmot"       ,1270);
    public static final FluidObject<SimpleFlowableFluid> moltenCelestium    = hotLiquid(FLUIDS, "molten_celestium"    ,1700);
    public static final FluidObject<SimpleFlowableFluid> moltenDurasteel    = hotLiquid(FLUIDS, "molten_durasteel"    ,1100);
    public static final FluidObject<SimpleFlowableFluid> moltenHallowed     = hotLiquid(FLUIDS, "molten_hallowed"     ,1500);
    public static final FluidObject<SimpleFlowableFluid> moltenKyber        = hotLiquid(FLUIDS, "molten_kyber"        ,1060);
    public static final FluidObject<SimpleFlowableFluid> moltenManganese    = hotLiquid(FLUIDS, "molten_manganese"    ,800);
    public static final FluidObject<SimpleFlowableFluid> moltenMetallurgium = hotLiquid(FLUIDS, "molten_metallurgium" ,1770);
    public static final FluidObject<SimpleFlowableFluid> moltenMythril      = hotLiquid(FLUIDS, "molten_mythril"      ,1200);
    public static final FluidObject<SimpleFlowableFluid> moltenOrichalcum   = hotLiquid(FLUIDS, "molten_orichalcum"   ,1050);
    public static final FluidObject<SimpleFlowableFluid> moltenPalladium    = hotLiquid(FLUIDS, "molten_palladium"    ,1200);
    public static final FluidObject<SimpleFlowableFluid> moltenPrometheum   = hotLiquid(FLUIDS, "molten_prometheum"   ,1100);
    public static final FluidObject<SimpleFlowableFluid> moltenQuadrillum   = hotLiquid(FLUIDS, "molten_quadrillum"   ,850);
    public static final FluidObject<SimpleFlowableFluid> moltenRunite       = hotLiquid(FLUIDS, "molten_runite"       ,1270);
    public static final FluidObject<SimpleFlowableFluid> moltenStarrite     = hotLiquid(FLUIDS, "molten_starrite"     ,1200);
    public static final FluidObject<SimpleFlowableFluid> moltenStarPlatinum = hotLiquid(FLUIDS, "molten_star_platinum",1600);
    public static final FluidObject<SimpleFlowableFluid> moltenStormyx      = hotLiquid(FLUIDS, "molten_stormyx"      ,1200);
    public static final FluidObject<SimpleFlowableFluid> moltenUnobtainium  = hotLiquid(FLUIDS, "molten_unobtainium"  ,1700);
    public static final FluidObject<SimpleFlowableFluid> moltenManasteel    = hotLiquid(FLUIDS, "molten_manasteel"    ,1000);
    public static final FluidObject<SimpleFlowableFluid> moltenElementium   = hotLiquid(FLUIDS, "molten_elementium"   ,1300);
    public static final FluidObject<SimpleFlowableFluid> moltenTerrasteel   = hotLiquid(FLUIDS, "molten_terrasteel"   ,1500);

    public static void register() {
        FLUIDS.register();
    }
}
