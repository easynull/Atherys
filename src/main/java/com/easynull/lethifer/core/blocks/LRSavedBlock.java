package com.easynull.lethifer.core.blocks;

import com.easynull.lethifer.core.LRComponents;
import com.easynull.lethifer.core.blocks.entities.LRBlockEntity;
import com.easynull.lethifer.core.blocks.entities.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class LRSavedBlock extends LRBlock implements EntityBlock {
    final BlockEntityType.BlockEntitySupplier be;

    public LRSavedBlock(Properties properties, BlockEntityType.BlockEntitySupplier be) {
        super(properties);
        this.be = be;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return be.create(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return Tickable.getTicker();
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        ItemStack stack = super.getCloneItemStack(level, pos, state, includeData, player);
        if(level.getBlockEntity(pos) instanceof LRBlockEntity rbe) {
            CompoundTag nbt = rbe.saveCustomOnly(level.registryAccess());
            stack.set(LRComponents.nbt, new LRComponents.NBTComponent(nbt));
        }
        return stack;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if(level.getBlockEntity(pos) instanceof LRBlockEntity rbe && stack.has(LRComponents.nbt)) {
            rbe.loadCustomOnly(stack.get(LRComponents.nbt).nbt(), level.registryAccess());
        }
    }
}
