package com.konsola5;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class HephaestusPlusDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		ExistingFileHelper helper = ExistingFileHelper.withResourcesFromArg();
		FabricDataGenerator.Pack pack = generator.createPack();
		Registry.gatherData(pack, helper);
	}
}