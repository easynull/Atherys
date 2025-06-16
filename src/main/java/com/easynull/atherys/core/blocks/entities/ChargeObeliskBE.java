package com.easynull.atherys.core.blocks.entities;

import com.easynull.atherys.api.essential.ArEssential;
import com.easynull.atherys.core.ASBlockEntities;
import com.easynull.atherys.utils.EnergyUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class ChargeObeliskBE extends LRHideInventory implements ArEssential, TickableBE {
    int essential;
    public ChargeObeliskBE(BlockPos pos, BlockState state) {
        super(ASBlockEntities.chargeObelisks.get(), pos, state);
    }

    @Override
    public void tick() {
        if(!level.isClientSide){
            EnergyUtils.inFrom(getItem(), this, 20, true);
        }
    }

    @Override
    protected void putNBT(CompoundTag nbt) {
        nbt.putInt("are", essential);
    }

    @Override
    protected void getNBT(CompoundTag nbt) {
        essential = nbt.getInt("are");
    }
}
