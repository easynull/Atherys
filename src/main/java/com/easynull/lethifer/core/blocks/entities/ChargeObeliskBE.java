package com.easynull.lethifer.core.blocks.entities;

import com.easynull.lethifer.api.essential.LEssential;
import com.easynull.lethifer.core.LRBlockEntities;
import com.easynull.lethifer.utils.EnergyUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class ChargeObeliskBE extends LRHideInventory implements LEssential, TickableBE {
    int essential;
    public ChargeObeliskBE(BlockPos pos, BlockState state) {
        super(LRBlockEntities.chargeObelisks.get(), pos, state);
    }

    @Override
    public void tick() {
        if(!level.isClientSide){
            EnergyUtils.inFrom(getItem(), this, 20, true);
        }
    }

    @Override
    protected void putNBT(CompoundTag nbt) {
        nbt.putInt("le", essential);
    }

    @Override
    protected void getNBT(CompoundTag nbt) {
        essential = nbt.getInt("le");
    }
}
