package com.konsola5.portingLibHacks;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper.*;

public class ExistingFileHelperHack {
    public static ExistingFileHelper withResourcesFromArg() {
        String property = System.getProperty(EXISTING_RESOURCES);
        if (property == null)
            throw new IllegalArgumentException("Existing resources not specified with '" + EXISTING_RESOURCES + "' argument");
        Path path = Paths.get(property);
        if (!Files.isDirectory(path))
            throw new IllegalStateException("Path " + property + " is not a directory or does not exist");
        String mods = System.getProperty(EXISTING_MODS);
        if (mods == null)
            mods = "";
        return withResources(Set.of("tconstruct"), path);
    }
}
