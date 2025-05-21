package com.easynull.lethifer.core.blocks.entities;

import com.easynull.lethifer.api.NEnergy;
import com.easynull.lethifer.core.LRBlockEntities;
import com.easynull.lethifer.utils.EnergyUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class ChargeObelisk extends LRHideInventory implements NEnergy, Tickable {
    int energy;
    public ChargeObelisk(BlockPos pos, BlockState state) {
        super(LRBlockEntities.chargeObelisks.get(), pos, state);
        energy = 5000;
    }

    @Override
    public void tick() {
        if(!level.isClientSide){
            EnergyUtils.inFrom(getItem(), this, 20, true);
        }
    }

    @Override
    protected void putNBT(CompoundTag nbt) {
        nbt.putInt("ne", energy);
    }

    @Override
    protected void getNBT(CompoundTag nbt) {
        energy = nbt.getInt("ne");
    }
}
