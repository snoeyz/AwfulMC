package com.snoeyz.awful_minecraft.setup;

import com.snoeyz.awful_minecraft.AwfulMinecraft;
import com.snoeyz.awful_minecraft.network.PacketExhaustionFumbleToClient;
import com.snoeyz.awful_minecraft.network.PacketSyncShaderToClient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Messages {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() { return packetId++; }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(AwfulMinecraft.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PacketSyncShaderToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncShaderToClient::new)
                .encoder(PacketSyncShaderToClient::toBytes)
                .consumer(PacketSyncShaderToClient::handle)
                .add();

        net.messageBuilder(PacketExhaustionFumbleToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketExhaustionFumbleToClient::new)
                .encoder(PacketExhaustionFumbleToClient::toBytes)
                .consumer(PacketExhaustionFumbleToClient::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
