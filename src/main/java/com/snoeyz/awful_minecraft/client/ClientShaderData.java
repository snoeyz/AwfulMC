package com.snoeyz.awful_minecraft.client;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class ClientShaderData {

    private static ResourceLocation shaderResource;

    public static void set(ResourceLocation loc) {
        shaderResource = loc;
    }

    @Nullable
    public static ResourceLocation getShaderResource() {
        return shaderResource;
    }

    public static boolean isSet() {
        return shaderResource != null;
    }
}
