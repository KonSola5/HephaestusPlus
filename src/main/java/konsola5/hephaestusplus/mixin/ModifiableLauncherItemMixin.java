package konsola5.hephaestusplus.mixin;

import io.github.fabricators_of_create.porting_lib.common.util.Lazy;
import konsola5.hephaestusplus.util.ToolEnergyCapability;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableLauncherItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import team.reborn.energy.api.EnergyStorage;

@SuppressWarnings("UnstableApiUsage")
@Mixin(ModifiableLauncherItem.class)
public class ModifiableLauncherItemMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addEnergyCapability(Item.Properties properties, ToolDefinition toolDefinition, ResourceKey<CreativeModeTab> tab, CallbackInfo ci) {
        EnergyStorage.ITEM.registerFallback(((itemStack, context) -> {
            if (itemStack.getItem() instanceof ModifiableLauncherItem) {
                return new ToolEnergyCapability(context, Lazy.of(() -> ToolStack.from(itemStack)));
            }
            return null;
        }));
    }
}
