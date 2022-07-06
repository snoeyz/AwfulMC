package com.snoeyz.awful_minecraft.trolls;

import com.snoeyz.awful_minecraft.entities.TrollCreeper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.Random;

public class FakeCreeperTroll {

    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if(!event.getPlayer().isCrouching()) {
            //Player is not crouching
            BlockEntity t = event.getWorld().getBlockEntity(event.getPos());
            if (t instanceof ChestBlockEntity || t instanceof ShulkerBoxBlockEntity || t instanceof EnderChestBlockEntity || t instanceof BarrelBlockEntity) {
                if (!event.getWorld().isClientSide()) {
                    if (new Random().nextInt(15) == 0) {
                        TrollCreeper trollCreeper = new TrollCreeper(EntityType.CREEPER, event.getWorld());
                        trollCreeper.positionAtPlayer(event.getPlayer());
                        event.getWorld().addFreshEntity(trollCreeper);
                    }
                }
            }
        }
    }
}
