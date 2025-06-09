package com.easynull.lethifer.core.blocks.entities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

public interface TickableBE {
    void tick();
    static <T extends BlockEntity> BlockEntityTicker<T> getTicker() {
        return (level, pos, state, be) -> ((TickableBE)be).tick();
    }
}
