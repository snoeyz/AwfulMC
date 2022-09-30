package com.snoeyz.awful_minecraft.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TrollCreeper extends Creeper {
    private int ticksRemaining;

    public TrollCreeper(EntityType<? extends Creeper> p_32278_, Level p_32279_) {
        super(p_32278_, p_32279_);
        this.setNoAi(true);
        this.ignite();
        this.ticksRemaining = 25;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes();
    }

    public void positionAtPlayer(Player player) {
        this.setPos(player.position());
    }

    @Override
    public void tick() {
        super.tick();
        this.ticksRemaining--;
        if (ticksRemaining <= 0) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }
}
