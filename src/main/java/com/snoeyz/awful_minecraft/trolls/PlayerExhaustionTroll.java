package com.snoeyz.awful_minecraft.trolls;

import com.mojang.logging.LogUtils;
import com.snoeyz.awful_minecraft.AwfulMinecraft;
import com.snoeyz.awful_minecraft.api.exhaustion.IExhaustionManager;
import com.snoeyz.awful_minecraft.client.ClientFumbleData;
import com.snoeyz.awful_minecraft.core.AwfulnessConfig;
import com.snoeyz.awful_minecraft.network.PacketExhaustionFumbleToClient;
import com.snoeyz.awful_minecraft.setup.Messages;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.Optional;

@Mod.EventBusSubscriber
public class PlayerExhaustionTroll {
    private static final Logger LOGGER = LogUtils.getLogger();

    public enum FumbleEventType {
        AUDITORY,
        BLINKING,
        CLUMSY,
        DIZZY,
        EPIPHORA,
        EXHAUSTED,
        SLEEPY,
        SLUGGISH,
        YAWNING
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) event.player;
            Optional<IExhaustionManager> optional = AwfulMinecraft.getExhaustionManager(serverPlayer);
            if (optional.isPresent()) {
                IExhaustionManager manager = optional.get();

                manager.tickAwakeCounter();
                if (manager.getAwakeCounter() >= AwfulnessConfig.EXHAUSTION_ONSET_TICKS.get()) {
                    if (manager.getFumbleCooldown() > 0) {
                        manager.setFumbleCooldown(manager.getFumbleCooldown() - 1);
                    } else {
                        if (serverPlayer.getRandom().nextInt(AwfulnessConfig.MAX_TROLL_THRESHOLD) >= AwfulnessConfig.EXHAUSTION_FUMBLE_THRESHOLD.get()) {
                            // Drop an item
                            var stack = serverPlayer.getInventory().getItem(serverPlayer.getRandom().nextInt(9));
                            if (stack != null && !stack.isEmpty()) {;
                                serverPlayer.getInventory().removeItem(stack);
                                ItemEntity droppedItem = serverPlayer.drop(stack, true);
                                droppedItem.playSound(new SoundEvent(new ResourceLocation("minecraft", "entity.puffer_fish.blow_up")));
                                var fumbleType = FumbleEventType.values()[serverPlayer.getRandom().nextInt(FumbleEventType.values().length)];
                                Messages.sendToPlayer(new PacketExhaustionFumbleToClient(fumbleType), serverPlayer);
                                if (fumbleType == FumbleEventType.SLUGGISH) {
                                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 500));
                                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 500));
                                }
                            }
                        }
                        manager.setFumbleCooldown(AwfulnessConfig.EXHAUSTION_FUMBLE_COOLDOWN.get());
                    }
                } else if (manager.getFumbleCooldown() > 0) {
                    manager.setFumbleCooldown(0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
        AwfulMinecraft.getExhaustionManager(event.getPlayer()).ifPresent(manager -> manager.resetAwakeCounter());
        if (event.getPlayer().isLocalPlayer()) {
            Minecraft.getInstance().gameRenderer.shutdownEffect();
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (ClientFumbleData.isSet()) {
            if (ClientFumbleData.hasClientShader()) {
                Minecraft.getInstance().gameRenderer.loadEffect(ClientFumbleData.getShaderResource());
            }
            Minecraft.getInstance().gui.setTitle(MutableComponent.create(new TranslatableContents(ClientFumbleData.getTitleKey())));
            Minecraft.getInstance().gui.setSubtitle(MutableComponent.create(new TranslatableContents(ClientFumbleData.getSubtitleKey())));
            if (ClientFumbleData.get() == FumbleEventType.AUDITORY) {
                Minecraft.getInstance().player.level.playLocalSound(
                        Minecraft.getInstance().player.position().x,
                        Minecraft.getInstance().player.position().y,
                        Minecraft.getInstance().player.position().z,
                        new SoundEvent(new ResourceLocation("minecraft", "entity.zombie.ambient")),
                        SoundSource.HOSTILE,
                        1f,
                        1f,
                        false
                );
            }
            ClientFumbleData.set(null);
        }
    }
}
