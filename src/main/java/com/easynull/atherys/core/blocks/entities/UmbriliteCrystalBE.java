package com.easynull.atherys.core.blocks.entities;

import com.easynull.atherys.api.essential.ArEssentialGenerator;
import com.easynull.atherys.core.ASBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class UmbriliteCrystalBE extends LRBlockEntity implements TickableBE, ArEssentialGenerator {

    public UmbriliteCrystalBE(BlockPos pos, BlockState blockState) {
        super(ASBlockEntities.umbriliteCrystals.get() ,pos, blockState);
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

    @Override
    public float getBaudRateEssential() {
        return 0;
    }

    @Override
    public int getAmountRateEssential() {
        return 0;
    }
}
