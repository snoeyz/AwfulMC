package com.snoeyz.awful_minecraft.api.exhaustion;

import net.minecraft.nbt.CompoundTag;

public class ExhaustionManager implements IExhaustionManager {

    private int ticksAwake = 0;
    private int fumbleCooldown = 0;

    @Override
    public int getAwakeCounter() {
        return this.ticksAwake;
    }

    @Override
    public void tickAwakeCounter() {
        this.ticksAwake++;
    }

    @Override
    public void resetAwakeCounter() {
        this.ticksAwake = 0;
    }

    @Override
    public int getFumbleCooldown() { return this.fumbleCooldown; }

    @Override
    public void setFumbleCooldown(int value) { this.fumbleCooldown = value; }

    @Override
    public void serialize(CompoundTag tag) {
        tag.putInt("amc:awake_ticks", this.ticksAwake);
        tag.putInt("amc:fumble_cooldown", this.fumbleCooldown);
    }

    @Override
    public void deserialize(CompoundTag tag) {
        if (tag.contains("amc:awake_ticks")) {
            this.ticksAwake = tag.getInt("amc:awake_ticks");
        }
        if (tag.contains("amc:fumble_cooldown")) {
            this.fumbleCooldown = tag.getInt("amc:fumble_cooldown");
        }
    }
}
