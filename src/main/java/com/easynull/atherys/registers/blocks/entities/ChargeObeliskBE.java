package com.easynull.atherys.registers.blocks.entities;

import com.easynull.atherys.core.essential.AreElement;
import com.easynull.atherys.registers.AsBlockEntities;
import com.easynull.atherys.utils.EnergyUtils;
import com.mw.nullcore.core.blocks.type.ContainerBlockEntity;
import com.mw.nullcore.core.blocks.type.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class ChargeObeliskBE extends ContainerBlockEntity implements AreElement, Tickable {
    long are;
    public ChargeObeliskBE(BlockPos pos, BlockState state) {
        super(AsBlockEntities.chargeObelisks.get(), pos, state);
    }

    @Override
    public void tick() {
        if(!level.isClientSide){
            EnergyUtils.extractInFrom(getFirst(), this, 20, true);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        nbt.putLong("are", are);
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        are = nbt.getLong("are");
    }
}
