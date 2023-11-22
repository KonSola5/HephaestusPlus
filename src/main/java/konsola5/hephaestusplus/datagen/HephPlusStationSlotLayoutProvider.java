package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.Registry;
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
        defineModifiable(Registry.handHammer)
                .sortIndex(SORT_HARVEST)
                .addInputItem(TinkerToolParts.hammerHead,    53, 22)
                .addInputItem(TinkerToolParts.toolHandle,  15, 60)
                .addInputItem(TinkerToolParts.toolBinding, 33, 42)
                .build();

        defineModifiable(Registry.crook)
                .sortIndex(SORT_HARVEST)
                .addInputItem(Registry.crookHead,          53, 22)
                .addInputItem(TinkerToolParts.toolHandle,  15, 60)
                .addInputItem(TinkerToolParts.toolBinding, 33, 42)
                .build();
    }

    @Override
    public String getName() {
        return "HephaestusPlus Tinker Station Slot Layouts";
    }
}
