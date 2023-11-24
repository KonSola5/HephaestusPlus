package konsola5.hephaestusplus;

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
        if (!FabricLoader.getInstance().isModLoaded("fabricaeexnihilo") && !FabricLoader.getInstance().isModLoaded("mythicmetals")) {
            ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
                if (client.player != null) {
                    client.player.displayClientMessage(Component.literal("⚠ Neither FabricaeExNihilo nor Mythic Metals were detected! Currently HephaestusPlus does not add any additions to the base Hephaestus.").withStyle(ChatFormatting.GOLD), false);
                }
            });
        }

        setTranslucent(Registry.moltenStarrite);
    }

    private static void setTranslucent(FluidObject<?> fluid) {
        BlockRenderLayerMap.INSTANCE.putFluid(fluid.getStill(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putFluid(fluid.getFlowing(), RenderType.translucent());
    }
}
