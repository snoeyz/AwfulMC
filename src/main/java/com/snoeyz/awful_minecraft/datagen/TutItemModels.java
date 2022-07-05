package com.snoeyz.awful_minecraft.datagen;

import com.snoeyz.awful_minecraft.AwfulMinecraft;
import com.snoeyz.awful_minecraft.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider {

    public TutItemModels(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, AwfulMinecraft.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.AWFUL_LOG.getId().getPath(), modLoc("block/awful_log"));
    }
}
