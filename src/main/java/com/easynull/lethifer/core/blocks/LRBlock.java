package com.easynull.lethifer.core.blocks;

import com.easynull.lethifer.core.blocks.entities.LRHideInventory;
import com.easynull.lethifer.core.blocks.entities.TickableBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public abstract class LRBlock extends Block implements EntityBlock {
    final BlockEntityType.BlockEntitySupplier be;

    public LRBlock(Properties properties, BlockEntityType.BlockEntitySupplier be) {
        super(properties);
        this.be = be;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return be.create(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return TickableBE.getTicker();
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity tile = level.getBlockEntity(pos);
            if(tile instanceof LRHideInventory i){
                SimpleContainer inv = i.inv;
                if (!stack.isEmpty() && i.getItem().isEmpty()) {
                    inv.setItem(0, stack.copy());
                    stack.shrink(1);
                } else {
                    ItemStack dropped = inv.removeItem(0, 1);
                    ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), dropped);
                    level.addFreshEntity(item);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }
}
