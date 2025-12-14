package com.easynull.atherys.registers.blocks;

import com.easynull.atherys.registers.AsBlockEntities;
import com.mw.nullcore.Utils;
import com.mw.nullcore.core.blocks.EntitibleBlock;
import com.mw.nullcore.core.blocks.SaveDataBlock;
import com.mw.nullcore.core.blocks.type.ContainerBlockEntity;
import com.mw.nullcore.registers.NullComponents;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class ChargeObeliskBlock extends EntitibleBlock implements SaveDataBlock {
    final int maxEnergy;

    public ChargeObeliskBlock(Properties properties, int maxEnergy) {
        super(properties, AsBlockEntities.chargeObelisks::get);
        this.maxEnergy = maxEnergy;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.getBlockEntity(pos) instanceof ContainerBlockEntity container) {
            if (Utils.Item.insertItem(container, player, 0, Screen.hasControlDown() ? 1 : 64)) return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tooltip.atherys.essential.current", stack.getOrDefault(NullComponents.nbt, new CompoundTag()).getInt("are"), maxEnergy));
    }
}
