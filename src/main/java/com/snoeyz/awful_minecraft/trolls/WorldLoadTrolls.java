package com.snoeyz.awful_minecraft.trolls;

import net.minecraft.world.level.Level;
import net.minecraftforge.event.world.WorldEvent;

public class WorldLoadTrolls {

    public static void onLoadWorld(WorldEvent.Load event) {
        if (!event.getWorld().isClientSide() && event.getWorld().getLevelData().getGameTime() < 5) {
            event.getWorld().getServer().getLevel(Level.OVERWORLD).setDayTime(12000);
        }
    }
}
