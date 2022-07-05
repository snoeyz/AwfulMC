package com.snoeyz.awful_minecraft.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class TrollBoat extends Boat {

    public static final String TROLL_BOAT_TAG = "troll_boat";

    public TrollBoat(Level level, double xo, double yo, double zo) {
        super(level, xo, yo, zo);
        this.addTag(TROLL_BOAT_TAG);
    }

    public TrollBoat(EntityType<TrollBoat> trollBoatEntityType, Level level) {
        super(trollBoatEntityType, level);
        this.addTag(TROLL_BOAT_TAG);
    }

    @Override
    public Item getDropItem() {
        return null;
    }

    @Override
    protected boolean canAddPassenger(Entity p_38390_) {
        kill();
        return false;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }
}
