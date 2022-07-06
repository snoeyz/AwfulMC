package com.snoeyz.awful_minecraft.core;

import net.minecraftforge.common.ForgeConfigSpec;

public class AwfulnessConfig {

    public static ForgeConfigSpec.IntValue POWDERED_SNOW_TROLL_THRESHOLD;

    public static void registerServerConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Settings to specify how awful the game is").push("awfulness");

        POWDERED_SNOW_TROLL_THRESHOLD = builder
                .comment("Threshold that controls how frequently the powdered snow troll occurs")
                .defineInRange("powderedSnowTrollThreshold", 10, 0, 1000);

        builder.pop();
    }
}
