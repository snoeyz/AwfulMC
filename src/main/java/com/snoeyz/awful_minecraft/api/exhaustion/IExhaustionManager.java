package com.snoeyz.awful_minecraft.api.exhaustion;

import net.minecraft.nbt.CompoundTag;

public interface IExhaustionManager {

    /**
     * @return the current number of ticks player has spent awake.
     */
    int getAwakeCounter();

    /**
     * Increments number of ticks player has been awake.
     */
    void tickAwakeCounter();

    /**
     * Resets the number of ticks player has been awake back to zero.
     */
    void resetAwakeCounter();

    /**
     * @return the current ticks remaining on the fumble cooldown timer.
     */
    int getFumbleCooldown();

    /**
     * Sets the number of ticks on the fumble cooldown timer.
     * @param value the number of ticks
     */
    void setFumbleCooldown(int value);

    /**
     * Serializes the data to the given NBTTagCompound.
     * @param compound the tag to which the data will be saved
     */
    void serialize(CompoundTag compound);

    /**
     * Reads the data from the given NBTTagCompound.
     * @param compound the tag from which the data will be read
     */
    void deserialize(CompoundTag compound);
}
