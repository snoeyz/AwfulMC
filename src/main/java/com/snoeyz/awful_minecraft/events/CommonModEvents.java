package com.snoeyz.awful_minecraft.events;

import com.snoeyz.awful_minecraft.AwfulMinecraft;
import com.snoeyz.awful_minecraft.api.exhaustion.IExhaustionManager;
import com.snoeyz.awful_minecraft.entities.TrollCreeper;
import com.snoeyz.awful_minecraft.setup.Registration;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AwfulMinecraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(Registration.TROLL_CREEPER.get(), TrollCreeper.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IExhaustionManager.class);
    }
}
