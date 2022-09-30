package com.snoeyz.awful_minecraft.capability;

import com.snoeyz.awful_minecraft.AwfulMinecraft;
import com.snoeyz.awful_minecraft.api.exhaustion.ExhaustionManager;
import com.snoeyz.awful_minecraft.api.exhaustion.IExhaustionManager;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ExhaustionCapability {

    private static final ResourceLocation NAME = new ResourceLocation(AwfulMinecraft.MOD_ID, "exhaustion");
    public static final Capability<IExhaustionManager> CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    @SubscribeEvent
    public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(NAME, new ICapabilitySerializable<CompoundTag>() {

                private final ExhaustionManager instance = new ExhaustionManager();

                @NotNull
                @Override
                public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
                    return ExhaustionCapability.CAPABILITY.orEmpty(capability, LazyOptional.of(() -> this.instance));
                }

                @Override
                public CompoundTag serializeNBT() {
                    CompoundTag tag = new CompoundTag();
                    this.instance.serialize(tag);
                    return tag;
                }

                @Override
                public void deserializeNBT(CompoundTag nbt) {
                    this.instance.deserialize(nbt);
                }
            });
        }
    }
}
