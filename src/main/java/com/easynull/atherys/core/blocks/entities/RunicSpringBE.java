package com.easynull.atherys.core.blocks.entities;

import com.easynull.atherys.core.ASBlockEntities;
import com.mw.nullcore.core.blocks.Tickable;
import com.mw.nullcore.core.blocks.entities.NullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.world.level.block.state.BlockState;

public class RunicSpringBE extends NullBlockEntity implements Tickable {
    public RunicSpringBE(BlockPos pos, BlockState blockState) {
        super(ASBlockEntities.runicSprings.get() , pos, blockState);
    }

    @Override
    public void tick() {

    }
}
