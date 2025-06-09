package com.easynull.lethifer.core.blocks.entities;

import com.easynull.lethifer.api.essential.LEssentialGenerator;
import com.easynull.lethifer.core.LRBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class UmbriliteCrystalBE extends LRBlockEntity implements TickableBE, LEssentialGenerator {

    public UmbriliteCrystalBE(BlockPos pos, BlockState blockState) {
        super(LRBlockEntities.umbriliteCrystals.get() ,pos, blockState);
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
