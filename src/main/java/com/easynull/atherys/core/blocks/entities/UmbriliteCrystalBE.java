package com.easynull.atherys.core.blocks.entities;

import com.easynull.atherys.api.essential.ArEssentialGenerator;
import com.easynull.atherys.core.ASBlockEntities;
import com.mw.nullcore.core.blocks.Tickable;
import com.mw.nullcore.core.blocks.entities.NullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class UmbriliteCrystalBE extends NullBlockEntity implements Tickable, ArEssentialGenerator {

    public UmbriliteCrystalBE(BlockPos pos, BlockState blockState) {
        super(ASBlockEntities.umbriliteCrystals.get() ,pos, blockState);
    }

    @Override
    public void tick() {

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
