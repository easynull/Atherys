package com.easynull.atherys.registers.blocks.entities;

import com.easynull.atherys.registers.AsBlockEntities;
import com.mw.nullcore.core.blocks.type.Tickable;
import net.minecraft.core.BlockPos;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RunicSpringBE extends BlockEntity implements Tickable {
    public RunicSpringBE(BlockPos pos, BlockState blockState) {
        super(AsBlockEntities.runicSprings.get() , pos, blockState);
    }

    @Override
    public void tick() {

    }
}
