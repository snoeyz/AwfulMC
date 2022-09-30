package com.snoeyz.awful_minecraft.datagen;

import com.snoeyz.awful_minecraft.AwfulMinecraft;
import com.snoeyz.awful_minecraft.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockTags extends BlockTagsProvider {
    public TutBlockTags(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, AwfulMinecraft.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_AXE).add(Registration.AWFUL_LOG.get());

        tag(BlockTags.LOGS).add(Registration.AWFUL_LOG.get());
    }

    @Override
    public String getName() {
        return "Awful Minecraft Tags";
    }
}
