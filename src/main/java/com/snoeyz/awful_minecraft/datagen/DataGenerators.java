package com.snoeyz.awful_minecraft.datagen;

import com.snoeyz.awful_minecraft.AwfulMinecraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = AwfulMinecraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(true, new TutRecipes(generator));
            generator.addProvider(true, new TutLootTables(generator));
            TutBlockTags blockTags = new TutBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new TutItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(true, new TutBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(true, new TutItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(true, new TutLanguageProvider(generator, "en_us"));
        }
    }
}
