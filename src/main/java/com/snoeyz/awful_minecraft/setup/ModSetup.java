package com.snoeyz.awful_minecraft.setup;

import com.snoeyz.awful_minecraft.trolls.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {
    public static final String TAB_NAME = "awful_minecraft";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() { return new ItemStack(Items.DEEPSLATE); }
    };

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(FakeCreeperTroll::onRightClickBlock);

        bus.addListener(BoatCramTroll::onEntityJoinWorldEvent);
        bus.addListener(BoatCramTroll::onEntityMountingEvent);
        bus.addListener(WorldLoadTrolls::onLoadWorld);
        bus.addListener(MobDeathShaderTroll::onLivingDamage);
        bus.addListener(PowderedSnowTroll::onPlayerTick);
        bus.addListener(NetherPortalTroll::onEntityTravelToDimension);
        bus.addListener(NetherPortalTroll::onEntityPlaceBlock);
    }

    public static void init(FMLCommonSetupEvent event) {
        Messages.register();
    }
}
