package com.easynull.lethifer.api.essential;

import com.easynull.lethifer.core.LRComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface LEssential {
    default int getEssential(Object target) {
        if (target instanceof ItemStack stack) return stack.getOrDefault(LRComponents.essential, 0);
        else if (target instanceof BlockEntity be) return be.saveCustomOnly(be.getLevel().registryAccess()).getInt("le");
        return 0;
    }

    default int getMaxEssential() {
        return 2500;
    }

    default void reducerEssential(int amount, Object target) {
        amount = Math.clamp(getEssential(target) + amount, 0, getMaxEssential());
        if (target instanceof ItemStack stack) stack.set(LRComponents.essential, amount);
        else if (target instanceof BlockEntity be) {
            CompoundTag nbt = be.saveWithFullMetadata(be.getLevel().registryAccess());
            nbt.putInt("le", amount);
            be.loadCustomOnly(nbt, be.getLevel().registryAccess());
        }
    }
}
