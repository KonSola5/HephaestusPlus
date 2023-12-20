package konsola5.hephaestusplus.mixin;

import konsola5.hephaestusplus.ids.MoarMaterialIds;
import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.tools.client.PlateArmorModel;

@Mixin(value = PlateArmorModel.class, remap = false)
public class PlateArmorModelMixin {
    @Shadow private String material;

    @Inject(method = "setup", at = @At(value = "FIELD", target = "Lslimeknights/tconstruct/tools/client/PlateArmorModel;base:Lnet/minecraft/client/model/HumanoidModel;", opcode = Opcodes.PUTFIELD))
    private void setupInject(HumanoidModel<?> base, ItemStack stack, EquipmentSlot slot, CallbackInfo ci) {
        // Custom a' la Golden ""embellishments"" but not quite embellishments.
        if (ModifierUtil.getModifierLevel(stack, MoarModifierIds.carmot_shield) > 0) {
            material = MoarMaterialIds.carmot.toString();
        }
    }
}
