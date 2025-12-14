package com.easynull.atherys.registers.blocks.entities;

import com.easynull.atherys.core.essential.AreGenerator;
import com.easynull.atherys.registers.AsBlockEntities;
import com.mw.nullcore.core.blocks.type.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class UmbriliteCrystalBE extends BlockEntity implements Tickable, AreGenerator {

    public UmbriliteCrystalBE(BlockPos pos, BlockState blockState) {
        super(AsBlockEntities.umbriliteCrystals.get() ,pos, blockState);
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
