package com.easynull.lethifer.core.blocks;

import com.easynull.lethifer.core.LRComponents;
import com.easynull.lethifer.core.blocks.entities.ChargeObeliskBE;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ChargeObelisk extends LRSavedBlock {
    final int maxEnergy;
    public ChargeObelisk(Properties properties, int maxEnergy) {
        super(properties, ChargeObeliskBE::new);
        this.maxEnergy = maxEnergy;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tooltip.lethifer.essential.current", stack.getOrDefault(LRComponents.nbt, new CompoundTag()).getInt("le"), maxEnergy));
    }
}
