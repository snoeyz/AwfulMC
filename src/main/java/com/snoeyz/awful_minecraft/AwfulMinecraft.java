package com.snoeyz.awful_minecraft;

import com.snoeyz.awful_minecraft.setup.ClientSetup;
import com.snoeyz.awful_minecraft.setup.Config;
import com.snoeyz.awful_minecraft.setup.ModSetup;
import com.snoeyz.awful_minecraft.setup.Registration;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AwfulMinecraft.MOD_ID)
public class AwfulMinecraft {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "awful_minecraft";

    public AwfulMinecraft() {
        // Register the deferred registry
        ModSetup.setup();
        Registration.init();
        Config.register();

        // Register the setup methods for modloading
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        LOGGER.debug("Awful Minecraft loaded!");
    }
}
