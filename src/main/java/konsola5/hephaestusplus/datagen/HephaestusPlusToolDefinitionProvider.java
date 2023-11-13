package konsola5.hephaestusplus.datagen;

import konsola5.hephaestusplus.HephaestusPlus;
import konsola5.hephaestusplus.Registry;
import io.github.fabricators_of_create.porting_lib.tool.ToolActions;
import konsola5.hephaestusplus.tools.ToolDefinitions;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.definition.aoe.BoxAOEIterator;
import slimeknights.tconstruct.library.tools.definition.aoe.IBoxExpansion;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import wraith.fabricaeexnihilo.modules.ModTags;

import static slimeknights.tconstruct.tools.TinkerToolParts.*;

public class HephaestusPlusToolDefinitionProvider extends AbstractToolDefinitionDataProvider {
    public HephaestusPlusToolDefinitionProvider(FabricDataOutput output) {
        super(output, HephaestusPlus.MOD_ID);
    }

    @Override
    protected void addToolDefinitions() {
        define(ToolDefinitions.HAND_HAMMER)
                // parts
                .part(hammerHead)
                .part(toolHandle)
                .part(toolBinding)
                // stats
                .stat(ToolStats.ATTACK_DAMAGE, 1.0f)
                .stat(ToolStats.ATTACK_SPEED, 1.2f)
                .smallToolStartingSlots()
                // traits
                .trait(Registry.SMASHING, 1)
                // harvest
                .action(ToolActions.PICKAXE_DIG)
                .action(ToolActions.SHOVEL_DIG)
                .effective(ModTags.HAMMERABLES)
                .aoe(BoxAOEIterator.builder(0, 0, 0).addDepth(2).addHeight(1).direction(IBoxExpansion.PITCH).build());

        define(ToolDefinitions.CROOK)
                // parts
                .part(Registry.crookHead)
                .part(toolHandle)
                .part(toolBinding)
                // stats
                .stat(ToolStats.ATTACK_DAMAGE, 1.0f)
                .stat(ToolStats.ATTACK_SPEED, 1.2f)
                .smallToolStartingSlots()
                // traits
                .trait(Registry.CROOKING, 1)
                // harvest
                .action(ToolActions.SHEARS_DIG)
                .effective(ModTags.CROOKABLES)
                .aoe(BoxAOEIterator.builder(0, 0, 0).addDepth(2).addHeight(1).direction(IBoxExpansion.PITCH).build());

    }

    @Override
    public String getName() {
        return "HephaestusPlus Tool Definition Data Generator";
    }
}
