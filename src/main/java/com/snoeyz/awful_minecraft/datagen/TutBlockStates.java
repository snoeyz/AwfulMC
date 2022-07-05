package com.snoeyz.awful_minecraft.datagen;

import com.snoeyz.awful_minecraft.AwfulMinecraft;
import com.snoeyz.awful_minecraft.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockStates extends BlockStateProvider {
    public TutBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, AwfulMinecraft.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(Registration.AWFUL_LOG.get());
    }
}
