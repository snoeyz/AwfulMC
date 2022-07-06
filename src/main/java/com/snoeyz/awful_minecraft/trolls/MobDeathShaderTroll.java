package com.snoeyz.awful_minecraft.trolls;

import com.snoeyz.awful_minecraft.client.ClientShaderData;
import com.snoeyz.awful_minecraft.network.PacketSyncShaderToClient;
import com.snoeyz.awful_minecraft.setup.Messages;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Spider;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class MobDeathShaderTroll {

    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof ServerPlayer
                && event.getAmount() >= event.getEntityLiving().getHealth()
                && event.getSource().getEntity() != null
        ) {
            Entity srcEntity = event.getSource().getEntity();
            ServerPlayer player = (ServerPlayer)event.getEntityLiving();
            if (srcEntity instanceof Spider) {
                Messages.sendToPlayer(new PacketSyncShaderToClient("minecraft", "shaders/post/spider.json"), player);
            } else if (srcEntity instanceof EnderMan) {
                Messages.sendToPlayer(new PacketSyncShaderToClient("minecraft", "shaders/post/invert.json"), player);
            } else if (srcEntity instanceof Creeper) {
                Messages.sendToPlayer(new PacketSyncShaderToClient("minecraft", "shaders/post/creeper.json"), player);
            }
        }
    }

    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (ClientShaderData.isSet()) {
            Minecraft.getInstance().gameRenderer.loadEffect(ClientShaderData.getShaderResource());
            ClientShaderData.set(null);
        }
    }
}
