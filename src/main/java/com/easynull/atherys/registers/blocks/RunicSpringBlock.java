package com.easynull.atherys.registers.blocks;

import com.easynull.atherys.registers.AsBlockEntities;
import com.mw.nullcore.core.blocks.EntitibleBlock;
import com.mw.nullcore.core.blocks.SaveDataBlock;
import com.mw.nullcore.registers.NullComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class RunicSpringBlock extends EntitibleBlock implements SaveDataBlock {
    public RunicSpringBlock(Properties properties) {
        super(properties, AsBlockEntities.runicSprings::get);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if(level.getBlockState(pos.above()).getBlock() instanceof UmbriliteCrystalBlock){
            level.destroyBlock(pos.above(), false);
        }
        super.destroy(level, pos, state);
    }

    @Override
    public CompoundTag additionalData(LevelReader level, BlockPos pos, BlockState state, ItemStack stack) {
        if(level.getBlockState(pos.above()).getBlock() instanceof UmbriliteCrystalBlock wc){
            stack.set(DataComponents.ITEM_NAME, getName().append(Component.translatable("tooltip.atherys.busy")));
            return NbtUtils.writeBlockState(wc.defaultBlockState());
        }
        return null;
    }

    @Override
    public void additionalActions(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (stack.get(NullComponents.nbt).contains("Name") && !level.getBlockState(pos.above()).is(Blocks.AIR)) return;
        level.setBlock(pos.above(), NbtUtils.readBlockState(level.holderLookup(Registries.BLOCK), stack.get(NullComponents.nbt)), 3);
    }
}
