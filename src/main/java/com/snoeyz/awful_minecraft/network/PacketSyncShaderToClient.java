package com.snoeyz.awful_minecraft.network;

import com.snoeyz.awful_minecraft.client.ClientShaderData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSyncShaderToClient {

    private final ResourceLocation shaderResource;

    public PacketSyncShaderToClient(String namespace, String path) {
        shaderResource = new ResourceLocation(namespace, path);
    }

    public PacketSyncShaderToClient(FriendlyByteBuf buf) {
        shaderResource = buf.readResourceLocation();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceLocation(shaderResource);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Here we are client side.
            // Be very careful not to access client-only classes here! (like Minecraft) because
            // this packet needs to be available server-side too
            ClientShaderData.set(shaderResource);
        });
        return true;
    }
}
