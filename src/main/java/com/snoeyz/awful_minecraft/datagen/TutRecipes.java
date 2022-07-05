package com.snoeyz.awful_minecraft.datagen;

import com.snoeyz.awful_minecraft.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class TutRecipes extends RecipeProvider {

    public TutRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer) {
        ShapelessRecipeBuilder.shapeless(Blocks.OAK_PLANKS)
                .requires(Registration.AWFUL_LOG.get())
                .group("awful_minecraft")
                .unlockedBy("awful_log", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.AWFUL_LOG.get()))
                .save(finishedRecipeConsumer, "awful_oak_planks");
    }
}
