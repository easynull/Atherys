package com.easynull.lethifer.core.blocks;

import com.easynull.lethifer.core.LRComponents;
import com.easynull.lethifer.core.blocks.entities.LRBlockEntity;
import com.easynull.lethifer.core.blocks.entities.RunicSpringBE;
import com.easynull.lethifer.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RunicSpring extends LRSavedBlock {
    public RunicSpring(Properties properties) {
        super(properties, RunicSpringBE::new);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if(level.getBlockState(pos.above()).getBlock() instanceof UmbriliteCrystal){
            level.destroyBlock(pos.above(), false);
        }
        super.destroy(level, pos, state);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        ItemStack stack = super.getCloneItemStack(level, pos, state, includeData, player);
        if(level.getBlockEntity(pos) instanceof LRBlockEntity rbe) {
            CompoundTag nbt = rbe.saveCustomOnly(level.registryAccess());
            if(level.getBlockState(pos.above()).getBlock() instanceof UmbriliteCrystal wc){
                BlockUtils.saveBlock(wc, nbt);
                stack.set(DataComponents.ITEM_NAME, getName().append(Component.translatable("tooltip.lethifer.busy")));
            }
            stack.set(LRComponents.nbt, nbt);
        }
        return stack;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(stack.has(LRComponents.nbt) && stack.get(LRComponents.nbt).contains("block") && !level.getBlockState(pos.above()).is(Blocks.AIR)) return;
        if(level.getBlockEntity(pos) instanceof LRBlockEntity rbe && stack.has(LRComponents.nbt)) {
            rbe.loadCustomOnly(stack.get(LRComponents.nbt), level.registryAccess());
            if(stack.get(LRComponents.nbt).contains("block")) {
                level.setBlock(pos.above(), BlockUtils.loadBlock(stack.get(LRComponents.nbt)).defaultBlockState(), 3);
            }
        }
        super.setPlacedBy(level, pos, state, placer, stack);
    }
}
