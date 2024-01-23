package konsola5.hephaestusplus;

import konsola5.hephaestusplus.registry.HephPlusFluidRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import slimeknights.mantle.registration.object.FluidObject;

import java.util.Objects;

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

        ModContainer hephaestus = FabricLoader.getInstance().getModContainer("tconstruct").orElseThrow();
        boolean finalIsAnyModPresent = isAnyModPresent;
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (client.player != null) {
                if (!finalIsAnyModPresent) {
                    client.player.displayClientMessage(Component.literal("⚠ No optional dependencies were detected! Currently HephaestusPlus does not add any additions to the base Hephaestus by itself.").withStyle(ChatFormatting.GOLD), false);
                }
                if (Objects.equals(hephaestus.getMetadata().getVersion().getFriendlyString(), "1.20.1-3.6.3.241")) {
                    client.player.displayClientMessage(Component.literal("⚠ You are using a broken version of Hephaestus (3.6.3.241). Fluid transfer doesn't work in this version. Please downgrade Hephaestus to 3.6.3.240.").withStyle(ChatFormatting.RED), false);
                }
            }
        });
        setTranslucent(HephPlusFluidRegistry.moltenStarrite);
    }

    @SuppressWarnings("SameParameterValue")
    private static void setTranslucent(FluidObject<?> fluid) {
        BlockRenderLayerMap.INSTANCE.putFluid(fluid.getStill(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putFluid(fluid.getFlowing(), RenderType.translucent());
    }
}
