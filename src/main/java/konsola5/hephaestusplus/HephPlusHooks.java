//package konsola5.hephaestusplus;
//
//import konsola5.hephaestusplus.util.EnergyModifierHook;
//import slimeknights.tconstruct.library.modifiers.ModifierHook;
//import slimeknights.tconstruct.library.modifiers.ModifierHooks;
//
//import javax.annotation.Nullable;
//
//import java.util.Collection;
//import java.util.function.Function;
//
//public class HephPlusHooks {
//    private HephPlusHooks() {}
//
//    public static void init() {}
//
//    /** Hook for a tool boosting the tool's Energy capacity */
//    public static final ModifierHook<EnergyModifierHook> TOOL_ENERGY = register("tool_energy", EnergyModifierHook.class, EnergyModifierHook.SUM_MERGER,
//            (tool, modifier, capacity) -> 0);
//
//    /** Registers a new modifier hook under {@code hephaestusplus} */
//    private static <T> ModifierHook<T> register(String name, Class<T> filter, @Nullable Function<Collection<T>,T> merger, T defaultInstance) {
//        return ModifierHooks.register(HephaestusPlus.getResource(name), filter, defaultInstance, merger);
//    }
//}
