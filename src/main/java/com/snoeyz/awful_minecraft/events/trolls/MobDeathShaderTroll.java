package com.snoeyz.awful_minecraft.events.trolls;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Spider;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class MobDeathShaderTroll {

    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof ServerPlayer
                && event.getAmount() >= event.getEntityLiving().getHealth()
                && event.getSource().getEntity() != null
        ) {
            Entity srcEntity = event.getSource().getEntity();
            ServerPlayer player = (ServerPlayer)event.getEntityLiving();
            if (srcEntity instanceof Spider || srcEntity instanceof EnderMan) {
                player.setCamera(srcEntity);
            } else if (srcEntity instanceof Creeper) {
                Creeper shaderCreeper = EntityType.CREEPER.create(player.level);
                shaderCreeper.setPos(srcEntity.position());
                player.setCamera(shaderCreeper);
                //shaderCreeper.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }
}
