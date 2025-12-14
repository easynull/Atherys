package com.easynull.atherys.core.essential;

import com.easynull.atherys.registers.AsComponents;
import com.mw.nullcore.Utils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface AreElement {
    default long getAre(Object target) {
        if (target instanceof ItemStack stack) return stack.getOrDefault(AsComponents.essential, 0L);
        else if (target instanceof BlockEntity be) return be.getPersistentData().getLong("are");
        return 0;
    }

    default long getMaxAre() {
        return 5000;
    }

    default boolean reducerLp(long amount, Object target) {
        amount = Math.clamp(getAre(target) + amount, 0, getMaxAre());
        if (target instanceof ItemStack stack) {
            stack.set(AsComponents.essential, amount);
        } else if (target instanceof BlockEntity be) {
            CompoundTag nbt = be.getPersistentData();
            nbt.putLong("are", amount);
            Utils.Block.updateBlockEntity(be);
        }
        return amount != getMaxAre();
    }
}
