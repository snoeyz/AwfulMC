package com.snoeyz.awful_minecraft.datagen;

        import com.snoeyz.awful_minecraft.AwfulMinecraft;
        import net.minecraft.data.DataGenerator;
        import net.minecraft.data.tags.BlockTagsProvider;
        import net.minecraft.data.tags.ItemTagsProvider;
        import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemTags extends ItemTagsProvider {

    public TutItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, AwfulMinecraft.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
    }

    @Override
    public String getName() {
        return "Awful Minecraft Tags";
    }
}
