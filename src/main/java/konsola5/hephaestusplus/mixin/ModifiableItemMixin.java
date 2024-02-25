package konsola5.hephaestusplus.mixin;

import io.github.fabricators_of_create.porting_lib.common.util.Lazy;
import konsola5.hephaestusplus.util.ToolEnergyCapability;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import team.reborn.energy.api.EnergyStorage;

@SuppressWarnings("UnstableApiUsage")
@Mixin(ModifiableItem.class)
public class ModifiableItemMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addEnergyCapability(Properties properties, ToolDefinition toolDefinition, ResourceKey<CreativeModeTab> tab, CallbackInfo ci) {
        EnergyStorage.ITEM.registerFallback((itemStack, context) -> new ToolEnergyCapability(context, Lazy.of(() -> ToolStack.from(itemStack))));
    }

}
