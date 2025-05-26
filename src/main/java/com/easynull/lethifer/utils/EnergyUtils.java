package com.easynull.lethifer.utils;

import com.easynull.lethifer.api.essential.NEssential;
import net.minecraft.world.item.ItemStack;

public final class EnergyUtils {
    public static void inFrom(Object target, Object source, int amount, boolean inTarget){
        if (!((source instanceof ItemStack s ? s.getItem() : source) instanceof NEssential source1)) return;
        if (!((target instanceof ItemStack s ? s.getItem() : target) instanceof NEssential target1)) return;
        int space = target1.getMaxEnergy() - target1.getEnergy(target);
        amount = inTarget ? Math.min(source1.getEnergy(source), Math.min(amount, space)) : Math.min(target1.getEnergy(target), Math.min(amount, source1.getMaxEnergy() - source1.getEnergy(source)));
        if (inTarget) {
            target1.reducerEnergy(amount, target);
            source1.reducerEnergy(-amount, source);
        } else {
            target1.reducerEnergy(-amount, target);
            source1.reducerEnergy(amount, source);
        }
    }
}
