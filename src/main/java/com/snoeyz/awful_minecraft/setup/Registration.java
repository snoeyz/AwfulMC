package com.snoeyz.awful_minecraft.setup;

import com.snoeyz.awful_minecraft.AwfulMinecraft;
import com.snoeyz.awful_minecraft.blocks.AwfulLogBlock;
import com.snoeyz.awful_minecraft.entities.TrollBoat;
import com.snoeyz.awful_minecraft.entities.TrollCreeper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AwfulMinecraft.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AwfulMinecraft.MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, AwfulMinecraft.MOD_ID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITIES.register(bus);
    }

    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);

    public static final RegistryObject<RotatedPillarBlock> AWFUL_LOG = BLOCKS.register("awful_log", AwfulLogBlock::new);
    public static final RegistryObject<Item> AWFUL_LOG_ITEM = fromBlock(AWFUL_LOG);

    public static final RegistryObject<EntityType<TrollCreeper>> TROLL_CREEPER = ENTITIES.register("troll_creeper", () -> EntityType.Builder.of(TrollCreeper::new, MobCategory.CREATURE)
            .sized(0.6F, 1.7F)
            .build("troll_creeper"));

    public static final RegistryObject<EntityType<TrollBoat>> TROLL_BOAT = ENTITIES.register("troll_boat", () -> EntityType.Builder.<TrollBoat>of(TrollBoat::new, MobCategory.MISC)
            .sized(1.375F, 0.5625F)
            .clientTrackingRange(10)
            .build("troll_boat"));

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
