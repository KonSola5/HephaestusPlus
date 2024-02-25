package konsola5.hephaestusplus;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class HephPlusMixinPlugin implements IMixinConfigPlugin {
    // Copy-paste from Adorn's Mixin plugin.
    // Will prevent loading mod-specific mixins when said mod is not present.
// https://github.com/FabricMC/fabric-loader/issues/188
    private static final Supplier<Boolean> TRUE = () -> true;

    private static final Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of(
            "konsola5.hephaestusplus.mixin.IsCrookMixin", () -> FabricLoader.getInstance().isModLoaded("fabricaeexnihilo"),
            "konsola5.hephaestusplus.mixin.IsHammerMixin", () -> FabricLoader.getInstance().isModLoaded("fabricaeexnihilo"),
            "konsola5.hephaestusplus.mixin.CarmotShieldMixin", () -> FabricLoader.getInstance().isModLoaded("mythicmetals"),
            "konsola5.hephaestusplus.mixin.PlayerMixin", () -> FabricLoader.getInstance().isModLoaded("mythicmetals"),
            "konsola5.hephaestusplus.mixin.ShouldFilterOutMixin", () -> FabricLoader.getInstance().isModLoaded("botania")
    );

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
    }

    // Boilerplate

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
