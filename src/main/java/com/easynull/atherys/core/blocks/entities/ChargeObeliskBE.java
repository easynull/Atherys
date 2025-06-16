package com.easynull.atherys.core.blocks.entities;

import com.easynull.atherys.api.essential.ArEssential;
import com.easynull.atherys.core.ASBlockEntities;
import com.easynull.atherys.utils.EnergyUtils;
import com.mw.nullcore.core.blocks.Tickable;
import com.mw.nullcore.core.blocks.entities.InventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class ChargeObeliskBE extends InventoryBlockEntity implements ArEssential, Tickable {
    int essential;
    public ChargeObeliskBE(BlockPos pos, BlockState state) {
        super(ASBlockEntities.chargeObelisks.get(), pos, state);
    }

    @Override
    public void tick() {
        if(!level.isClientSide){
            EnergyUtils.inFrom(getFirst(), this, 20, true);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        nbt.putInt("are", essential);
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        essential = nbt.getInt("are");
    }
}
