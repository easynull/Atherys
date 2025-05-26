package com.easynull.lethifer.api.essential;

import com.easynull.lethifer.core.LRComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface NEssential {
    default int getEnergy(Object obj){
        if(obj instanceof ItemStack stack) return stack.getOrDefault(LRComponents.essential, 0);
        else if(obj instanceof BlockEntity be) return be.saveCustomOnly(be.getLevel().registryAccess()).getInt("ne");
        return 0;
    }
    default int getMaxEnergy(){
        return 5000;
    }
    default void reducerEnergy(int amount, Object obj){
        amount = Math.clamp(getEnergy(obj) + amount, 0, getMaxEnergy());
        if(obj instanceof ItemStack stack) stack.set(LRComponents.essential, amount);
        else if(obj instanceof BlockEntity be) {
            CompoundTag tag = be.saveWithFullMetadata(be.getLevel().registryAccess());
            tag.putInt("ne", amount);
            be.loadCustomOnly(tag, be.getLevel().registryAccess());
        }
    }
}
