package com.easynull.atherys.core.blocks;

import com.easynull.atherys.core.ASComponents;
import com.easynull.atherys.core.blocks.entities.ChargeObeliskBE;
import com.mw.nullcore.core.blocks.SaveDataBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ChargeObeliskBlock extends SaveDataBlock {
    final int maxEnergy;
    public ChargeObeliskBlock(Properties properties, int maxEnergy) {
        super(properties, ChargeObeliskBE::new);
        this.maxEnergy = maxEnergy;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tooltip.atherys.essential.current", stack.getOrDefault(ASComponents.nbt, new CompoundTag()).getInt("are"), maxEnergy));
    }
}
