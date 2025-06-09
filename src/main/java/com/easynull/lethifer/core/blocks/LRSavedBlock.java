package com.easynull.lethifer.core.blocks;

import com.easynull.lethifer.core.LRComponents;
import com.easynull.lethifer.core.blocks.entities.LRBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class LRSavedBlock extends LRBlock implements EntityBlock {

    public LRSavedBlock(Properties properties, BlockEntityType.BlockEntitySupplier be) {
        super(properties, be);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        ItemStack stack = super.getCloneItemStack(level, pos, state, includeData, player);
        if(level.getBlockEntity(pos) instanceof LRBlockEntity rbe) {
            CompoundTag nbt = rbe.saveCustomOnly(level.registryAccess());
            stack.set(LRComponents.nbt, nbt);
        }
        return stack;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if(level.getBlockEntity(pos) instanceof LRBlockEntity rbe && stack.has(LRComponents.nbt)) {
            rbe.loadCustomOnly(stack.get(LRComponents.nbt), level.registryAccess());
        }
    }
}
