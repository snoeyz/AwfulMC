package com.snoeyz.awful_minecraft.trolls;

import com.snoeyz.awful_minecraft.entities.TrollBoat;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMountEvent;

public class BoatCramTroll {

    public static void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (!event.getWorld().isClientSide()) {
            if (!event.loadedFromDisk() && event.getEntity() instanceof Boat && !(event.getEntity() instanceof TrollBoat)) {
                for (int ndx = 0; ndx < 207; ndx++) {
                    TrollBoat trollBoat = new TrollBoat(event.getWorld(), event.getEntity().xo, event.getEntity().yo, event.getEntity().zo);
                    trollBoat.setXRot(event.getEntity().getXRot());
                    trollBoat.setYRot(event.getEntity().getYRot());
                    event.getWorld().addFreshEntity(trollBoat);
                }
            }
        }
    }

    public static void onEntityMountingEvent(EntityMountEvent event) {
        if (!event.getWorldObj().isClientSide()) {
            if (event.getEntityBeingMounted() instanceof Boat && event.getEntityBeingMounted() instanceof TrollBoat) {
                //event.getEntityBeingMounted().remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }
}
