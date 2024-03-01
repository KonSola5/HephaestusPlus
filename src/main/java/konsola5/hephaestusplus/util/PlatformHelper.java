package konsola5.hephaestusplus.util;

import net.fabricmc.loader.api.FabricLoader;

import java.util.Objects;

public class PlatformHelper {
    public static boolean isModLoaded(String modID) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }

    public static String version(String modID) {
        return FabricLoader.getInstance().getModContainer("mod_id").orElseThrow().getMetadata().getVersion().getFriendlyString();
    }

    public static boolean areVersionsMatching(String modID, String version) {
        return Objects.equals(version(modID), version);
    }
}
