package com.easynull.atherys.utils;

import com.easynull.atherys.api.essential.ArEssential;
import net.minecraft.world.item.ItemStack;

public final class EnergyUtils {
    public static void inFrom(Object target, Object source, int amount, boolean inTarget){
        if (!((source instanceof ItemStack s ? s.getItem() : source) instanceof ArEssential source1)) return;
        if (!((target instanceof ItemStack s ? s.getItem() : target) instanceof ArEssential target1)) return;
        int space = target1.getMaxEssential() - target1.getEssential(target);
        amount = inTarget ? Math.min(source1.getEssential(source), Math.min(amount, space)) : Math.min(target1.getEssential(target), Math.min(amount, source1.getMaxEssential() - source1.getEssential(source)));
        if (inTarget) {
            target1.reducerEssential(amount, target);
            source1.reducerEssential(-amount, source);
        } else {
            target1.reducerEssential(-amount, target);
            source1.reducerEssential(amount, source);
        }
    }
}
