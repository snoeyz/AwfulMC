package com.snoeyz.awful_minecraft.trolls;

import com.snoeyz.awful_minecraft.core.AwfulnessConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;

public class PowderedSnowTroll {

    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        BlockState block = event.player.getLevel().getBlockState(new BlockPos(event.player.position()));
        if (block.is(Blocks.SNOW) && event.player.getRandom().nextInt(AwfulnessConfig.MAX_TROLL_THRESHOLD) <= AwfulnessConfig.POWDERED_SNOW_TROLL_THRESHOLD.get()) {
            event.player.getLevel().setBlockAndUpdate(new BlockPos(event.player.position()), Blocks.POWDER_SNOW.defaultBlockState());
            event.player.getLevel().setBlockAndUpdate(new BlockPos(event.player.position().add(0.0, -1.0, 0.0)), Blocks.POWDER_SNOW.defaultBlockState());
        }
    }
}
