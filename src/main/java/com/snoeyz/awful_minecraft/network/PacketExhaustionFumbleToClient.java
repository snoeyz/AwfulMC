package com.snoeyz.awful_minecraft.network;

import com.snoeyz.awful_minecraft.client.ClientFumbleData;
import com.snoeyz.awful_minecraft.trolls.PlayerExhaustionTroll;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketExhaustionFumbleToClient {

    private final PlayerExhaustionTroll.FumbleEventType eventType;

    public PacketExhaustionFumbleToClient(PlayerExhaustionTroll.FumbleEventType eventType) {
        this.eventType = eventType;
    }

    public PacketExhaustionFumbleToClient(FriendlyByteBuf buf) {
        eventType = buf.readEnum(PlayerExhaustionTroll.FumbleEventType.class);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(eventType);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Here we are client side.
            // Be very careful not to access client-only classes here! (like Minecraft) because
            // this packet needs to be available server-side too
            ClientFumbleData.set(eventType);
        });
        return true;
    }
}
