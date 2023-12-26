package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.registry.HephPlusItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;


public class HephPlusStationSlotLayoutProvider extends AbstractStationSlotLayoutProvider {
    public HephPlusStationSlotLayoutProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    protected void addLayouts() {
        Ingredient modifiable = Ingredient.of(TinkerTags.Items.MODIFIABLE);
        defineModifiable(HephPlusItemRegistry.handHammer)
                .sortIndex(SORT_HARVEST)
                .addInputItem(TinkerToolParts.hammerHead, 53, 22)
                .addInputItem(TinkerToolParts.toolHandle, 15, 60)
                .addInputItem(TinkerToolParts.toolBinding, 33, 42)
                .build();

        defineModifiable(HephPlusItemRegistry.crook)
                .sortIndex(SORT_HARVEST)
                .addInputItem(HephPlusItemRegistry.crookHead, 53, 22)
                .addInputItem(TinkerToolParts.toolHandle, 15, 60)
                .addInputItem(TinkerToolParts.toolBinding, 33, 42)
                .build();
    }

    @Override
    public String getName() {
        return "HephaestusPlus Tinker Station Slot Layouts";
    }
}
