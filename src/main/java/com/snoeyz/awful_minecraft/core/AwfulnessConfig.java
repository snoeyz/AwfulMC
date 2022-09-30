package com.snoeyz.awful_minecraft.core;

import net.minecraftforge.common.ForgeConfigSpec;

public class AwfulnessConfig {

    public static final int MAX_TROLL_THRESHOLD = 1000;

    public static ForgeConfigSpec.IntValue POWDERED_SNOW_TROLL_THRESHOLD;

    public static ForgeConfigSpec.IntValue EXHAUSTION_ONSET_TICKS;
    public static ForgeConfigSpec.IntValue EXHAUSTION_FUMBLE_THRESHOLD;
    public static ForgeConfigSpec.IntValue EXHAUSTION_FUMBLE_COOLDOWN;

    public static void registerServerConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Powdered Snow Troll Settings").push("snowtrap");

        POWDERED_SNOW_TROLL_THRESHOLD = builder
                .comment("Threshold that controls how frequently the powdered snow troll occurs")
                .defineInRange("powderedSnowTrollThreshold", 10, 0, MAX_TROLL_THRESHOLD);

        builder.pop();

        builder.comment("Exhausted Player Settings").push("exhaustion");

        EXHAUSTION_ONSET_TICKS = builder
                .comment("How many game ticks before a player becomes exhausted")
                .defineInRange("exhaustionOnsetTicks", 24000, 100, Integer.MAX_VALUE);

        EXHAUSTION_FUMBLE_THRESHOLD = builder
                .comment("Threshold that controls how frequently an exhausted player drops items")
                .defineInRange("exhaustionFumbleThreshold", 10, 0, MAX_TROLL_THRESHOLD);

        EXHAUSTION_FUMBLE_COOLDOWN = builder
                .comment("Minimum number of ticks between attempting to drop items")
                .defineInRange("exhaustionFumbleCooldown", 500, 0, Integer.MAX_VALUE);

        builder.pop();
    }
}
