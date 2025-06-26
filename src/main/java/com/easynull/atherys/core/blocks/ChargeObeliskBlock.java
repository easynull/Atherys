package com.easynull.atherys.core.blocks;

import com.easynull.atherys.core.blocks.entities.ChargeObeliskBE;
import com.mw.nullcore.core.NullComponents;
import com.mw.nullcore.core.blocks.InteractionBlock;
import com.mw.nullcore.core.blocks.SaveDataBlock;
import com.mw.nullcore.core.blocks.type.InventoryBlockEntity;
import com.mw.nullcore.utils.ClientUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.List;

public class ChargeObeliskBlock extends SaveDataBlock {
    final int maxEnergy;

    public ChargeObeliskBlock(Properties properties, int maxEnergy) {
        super(properties, ChargeObeliskBE::new);
        this.maxEnergy = maxEnergy;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tooltip.atherys.essential.current", stack.getOrDefault(NullComponents.nbt, new CompoundTag()).getInt("are"), maxEnergy));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity tile = level.getBlockEntity(pos);
            if (tile instanceof InventoryBlockEntity i) {
                SimpleContainer inv = i.inventory;
                if (!i.getFirst().isEmpty()) return InteractionResult.CONSUME;
                ItemStack stackToAdd = stack.copyWithCount(1);
                inv.setItem(0, stackToAdd);
                stack.shrink(1);
                level.gameEvent(null, GameEvent.BLOCK_CHANGE,pos);
                sendPacketDispatch(i);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        System.out.print("ggggg");
        if (!level.isClientSide) {
            System.out.print("ggggg");
            BlockEntity tile = level.getBlockEntity(pos);
            if (tile instanceof InventoryBlockEntity i) {
                SimpleContainer inv = i.inventory;
                System.out.print("ggggg");
                if (i.getFirst().isEmpty()) return InteractionResult.CONSUME;
                player.addItem(i.getFirst());
                inv.removeItem(0, 1);
                level.gameEvent(null, GameEvent.BLOCK_CHANGE,pos);
                sendPacketDispatch(i);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }

    public static void sendPacketDispatch(BlockEntity be) {
        if (be.getLevel() instanceof ServerLevel) {
            Packet<?> packet = be.getUpdatePacket();
            if (packet != null) {
                BlockPos pos = be.getBlockPos();
                ((ServerChunkCache) be.getLevel().getChunkSource()).chunkMap.getPlayers(new ChunkPos(pos), false).forEach(e -> e.connection.send(packet));
            }
        }
    }

    public static void inFromInventory(InventoryBlockEntity inv, Player player) {
        for (int i = inv.inventory.getContainerSize() - 1; i >= 0; i--) {
            ItemStack stack = inv.inventory.getItem(i);
            if (!stack.isEmpty()) {
                ItemStack copy = stack.copy();
                player.getInventory().placeItemBackInInventory(copy);
                inv.inventory.setItem(i, ItemStack.EMPTY);
                inv.getLevel().gameEvent(null, GameEvent.BLOCK_CHANGE, inv.getBlockPos());
                break;
            }
        }
    }
}
