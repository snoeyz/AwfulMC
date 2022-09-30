package com.snoeyz.awful_minecraft.datagen;

        import com.snoeyz.awful_minecraft.setup.Registration;
        import net.minecraft.data.DataGenerator;
        import net.minecraft.world.item.Items;

public class TutLootTables extends BaseLootTableProvider {

    public TutLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.AWFUL_LOG.get(), createSilkTouchTable("awful_log", Registration.AWFUL_LOG.get(), Items.STICK, 1, 4));
    }
}
