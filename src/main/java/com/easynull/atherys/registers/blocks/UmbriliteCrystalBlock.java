package com.easynull.atherys.registers.blocks;

import com.easynull.atherys.registers.AsBlockEntities;
import com.easynull.atherys.registers.AsElements;
import com.mw.nullcore.core.blocks.EntitibleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public class UmbriliteCrystalBlock extends EntitibleBlock {
    public static final IntegerProperty age = IntegerProperty.create("age", 0, 8), erosion = IntegerProperty.create("erosion", 0, 4);

    public UmbriliteCrystalBlock(Properties properties) {
        super(properties, AsBlockEntities.umbriliteCrystals::get);
        registerDefaultState(this.stateDefinition.any().setValue(age, 0).setValue(erosion, 0));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState curState = level.getBlockState(pos.below());
        return curState.is(Tags.Blocks.STONES) || curState.is(Tags.Blocks.ORES) || curState.is(Tags.Blocks.COBBLESTONES) || curState.is(Tags.Blocks.SANDSTONE_BLOCKS) || curState.getBlock() instanceof RunicSpringBlock;
    }

    @Override
    protected @NotNull BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduled, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource rand) {
        return canSurvive(state, level, pos) ? super.updateShape(state, level, scheduled, pos, direction, neighborPos, neighborState, rand) : Blocks.AIR.defaultBlockState();
    }

    public int getAge(BlockState state) {
        return state.getValue(age);
    }

    public int getErosion(BlockState state) {
        return state.getValue(erosion);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (!level.isAreaLoaded(pos, 1)) return;
        int a = this.getAge(state);
        int e = this.getErosion(state);
        boolean flag = level.getBlockState(pos.below()).is(AsElements.runicSpring);
        if (!isInDarkness(level, pos) || e == 4 && !flag) {
            level.destroyBlock(pos, false);
            ItemEntity item = new ItemEntity(level, pos.getX() + 0.5f, pos.getY() + 0.7f, pos.getZ() + 0.5f, new ItemStack(AsElements.umbriliteDust.asItem(), level.random.nextInt(1, 3)));
            level.addFreshEntity(item);
        }
        if (a < 8) {
            float f = getGrowthSpeed(level, pos);
            if (CommonHooks.canCropGrow(level, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0)) {
                level.setBlock(pos, state.setValue(UmbriliteCrystalBlock.age, a + 1), 2);
                CommonHooks.fireCropGrowPost(level, pos, state);
            }
        } else if (!flag && e < 4) {
            if (rand.nextInt(10) == 0) {
                level.setBlock(pos, state.setValue(UmbriliteCrystalBlock.erosion, e + 1), 2);
            }
        }
        System.out.print(state.getProperties());
    }

    private float getGrowthSpeed(ServerLevel level, BlockPos pos) {
        float speed = 1f;
        BlockState soilState = level.getBlockState(pos.below());
        if (soilState.is(Tags.Blocks.COBBLESTONES_DEEPSLATE)) {
            speed *= 2f;
        }
        if (soilState.is(AsElements.umbriliteOre)) {
            speed *= 2.5f;
        }
        if (soilState.is(AsElements.runicSpring)) {
            speed *= 4f;
        }
        return speed;
    }

    private boolean isInDarkness(ServerLevel level, BlockPos pos) {
        return level.getRawBrightness(pos.above(), 0) <= 11;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(age, erosion);
    }
}
