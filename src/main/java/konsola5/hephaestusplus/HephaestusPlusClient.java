package konsola5.hephaestusplus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

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
    }
}
