package com.snoeyz.awful_minecraft.setup;

import com.mojang.logging.LogUtils;
import com.snoeyz.awful_minecraft.AwfulMinecraft;
import com.snoeyz.awful_minecraft.trolls.MobDeathShaderTroll;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = AwfulMinecraft.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientSetup {

    private static final Logger LOGGER = LogUtils.getLogger();

    public static void init(FMLClientSetupEvent event) {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(MobDeathShaderTroll::onClientTick);
    }
}
