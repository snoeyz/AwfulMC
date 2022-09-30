package com.snoeyz.awful_minecraft.client;

import com.snoeyz.awful_minecraft.trolls.PlayerExhaustionTroll;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class ClientFumbleData {

    private static PlayerExhaustionTroll.FumbleEventType eventType;

    public static void set(PlayerExhaustionTroll.FumbleEventType event) {
        eventType = event;
    }

    @Nullable
    public static PlayerExhaustionTroll.FumbleEventType get() {
        return eventType;
    }

    @Nullable
    public static String getTitleKey() {
        return String.format("exhaustion.fumble.%s.title", eventType.toString().toLowerCase());
    }

    @Nullable
    public static String getSubtitleKey() {
        return String.format("exhaustion.fumble.%s.subtitle", eventType.toString().toLowerCase());
    }

    @Nullable
    public static ResourceLocation getShaderResource() {
        String shaderName;
        switch (eventType) {
            case BLINKING:
                shaderName = "blobs2";
                break;
            case DIZZY:
                shaderName = "phosphor";
                break;
            case EPIPHORA:
                shaderName = "art";
                break;
            case EXHAUSTED:
                shaderName = "bits";
                break;
            case SLEEPY:
                shaderName = "deconverge";
                break;
            case YAWNING:
                shaderName = "desaturate";
                break;
            default:
                return null;
        }
        return new ResourceLocation("minecraft", String.format("shaders/post/%s.json", shaderName));
    }

    public static boolean hasClientShader() {
        return getShaderResource() != null;
    }

    public static boolean isSet() {
        return eventType != null;
    }
}
