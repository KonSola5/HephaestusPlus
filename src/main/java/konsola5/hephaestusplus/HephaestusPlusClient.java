package konsola5.hephaestusplus;

import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import slimeknights.mantle.registration.object.FluidObject;

public class HephaestusPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        boolean isAnyModPresent = false;
        String[] modIds = {"fabricaeexnihilo", "mythicmetals", "botania"};
        for (String mod : modIds) {
            if (FabricLoader.getInstance().isModLoaded(mod)) {
                isAnyModPresent = true;
                break;
            }
        }
        if (!isAnyModPresent) {
            ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
                if (client.player != null) {
                    client.player.displayClientMessage(Component.literal("⚠ No optional dependencies were detected! Currently HephaestusPlus does not add any additions to the base Hephaestus by itself.").withStyle(ChatFormatting.GOLD), false);
                }
            });
        }
        setTranslucent(HephPlusFluidRegistry.moltenStarrite);
    }

    private static void setTranslucent(FluidObject<?> fluid) {
        BlockRenderLayerMap.INSTANCE.putFluid(fluid.getStill(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putFluid(fluid.getFlowing(), RenderType.translucent());
    }
}
