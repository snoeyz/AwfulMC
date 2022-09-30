package com.snoeyz.awful_minecraft.trolls;

import com.mojang.logging.LogUtils;
import com.snoeyz.awful_minecraft.core.AwfulPortalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.world.BlockEvent;
import org.slf4j.Logger;

import java.util.Optional;

public class NetherPortalTroll {

    private static final Logger LOGGER = LogUtils.getLogger();

    public static void onEntityTravelToDimension(EntityTravelToDimensionEvent event) {
        if (event.getEntity() instanceof Player && event.getDimension().equals(Level.NETHER)) {
            BlockPos blockPos = new BlockPos(event.getEntity().position());
            ServerLevel overworld = event.getEntity().getServer().getLevel(Level.OVERWORLD);
            Optional<PortalShape> portalShape = PortalShape.findPortalShape(overworld, blockPos, (shape) -> {
                return shape.isValid() && shape.isComplete();
            }, Direction.Axis.X);
            if (portalShape != null && portalShape.isPresent()) {
                overworld.destroyBlock(blockPos, false);
                event.setCanceled(true);
            } else {
                BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
                boolean portalFound = false;
                for (Direction direction : Direction.values()) {
                    if (overworld.getBlockState(mutableBlockPos.set(blockPos).move(direction)).is(Blocks.NETHER_PORTAL)) {
                        portalFound = true;
                        break;
                    }
                }
                if (portalFound) {
                    portalShape = PortalShape.findPortalShape(overworld, mutableBlockPos.immutable(), (shape) -> {
                        return shape.isValid() && shape.isComplete();
                    }, Direction.Axis.X);
                    if (portalShape != null && portalShape.isPresent()) {
                        overworld.destroyBlock(blockPos, false);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    public static void onEntityPlaceBlock(BlockEvent.EntityPlaceEvent event) {
        if (event.getState().is(Blocks.FIRE) && ((Level)event.getWorld()).dimension().equals(Level.OVERWORLD)) {
            Optional<AwfulPortalShape> portalShape = AwfulPortalShape.findEmptyPortalShape(event.getWorld(), event.getPos(), Direction.Axis.X);
            if (portalShape.isPresent() && portalShape.get().isValid()) {
                portalShape.get().createPortalBlocks();
            }
        }
    }
}
