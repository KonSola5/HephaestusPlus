package konsola5.hephaestusplus.modifiers;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import vazkii.botania.common.handler.PixieHandler;

import java.util.function.BiConsumer;

public class FairyBlessingModifier extends Modifier {

    @Override
    public void addAttributes(IToolStackView tool, int level, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (FabricLoader.getInstance().isModLoaded("botania") && slot.getType() == EquipmentSlot.Type.HAND) {
            consumer.accept(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Tool modifier", 0.03 * level));
        }
    }
}
