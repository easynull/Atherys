package com.easynull.lethifer.core.blocks.entities;

import com.easynull.lethifer.core.LRBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.world.level.block.state.BlockState;

public class RunicSpringBE extends LRBlockEntity implements TickableBE {
    public RunicSpringBE(BlockPos pos, BlockState blockState) {
        super(LRBlockEntities.runicSprings.get() , pos, blockState);
    }

    @Override
    public void tick() {

    }

    @Override
    protected void putNBT(CompoundTag nbt) {

    }

    @Override
    protected void getNBT(CompoundTag nbt) {

    }
}
