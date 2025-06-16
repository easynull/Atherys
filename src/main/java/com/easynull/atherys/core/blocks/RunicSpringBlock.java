package com.easynull.atherys.core.blocks;

import com.easynull.atherys.core.ASComponents;
import com.easynull.atherys.core.blocks.entities.LRBlockEntity;
import com.easynull.atherys.core.blocks.entities.RunicSpringBE;
import com.easynull.atherys.utils.BlockUtils;
import com.mw.nullcore.core.blocks.SaveDataBlock;
import com.mw.nullcore.core.components.NullComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RunicSpringBlock extends SaveDataBlock {
    public RunicSpringBlock(Properties properties) {
        super(properties, RunicSpringBE::new);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if(level.getBlockState(pos.above()).getBlock() instanceof UmbriliteCrystalBlock){
            level.destroyBlock(pos.above(), false);
        }
        super.destroy(level, pos, state);
    }

    @Override
    protected CompoundTag additionalData(LevelReader level, BlockPos pos, BlockState state, Player player, ItemStack stack) {
        if(level.getBlockState(pos.above()).getBlock() instanceof UmbriliteCrystalBlock wc){
            stack.set(DataComponents.ITEM_NAME, getName().append(Component.translatable("tooltip.atherys.busy")));
            return NbtUtils.writeBlockState(wc.defaultBlockState());
        }
        return super.additionalData(level, pos, state, player, stack);
    }

    @Override
    protected void additionalActions(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (stack.get(NullComponents.nbt).contains("Name") && !level.getBlockState(pos.above()).is(Blocks.AIR)) return;
        level.setBlock(pos.above(), NbtUtils.readBlockState(level.holderLookup(Registries.BLOCK), stack.get(NullComponents.nbt)), 3);
    }
}
