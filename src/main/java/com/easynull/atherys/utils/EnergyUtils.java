package com.easynull.atherys.utils;

import com.easynull.atherys.core.essential.AreElement;
import net.minecraft.world.item.ItemStack;

public final class EnergyUtils {
    public static void extractInFrom(Object target, Object source, long amount, boolean inTarget) {
        if (amount <= 0) {
            return;
        }

        AreElement sc = getLpElement(source);
        AreElement tg = getLpElement(target);

        if (sc == null || tg == null) {
            return;
        }

        long sourceLp = sc.getAre(source);
        long targetLp = tg.getAre(target);
        long sourceMax = sc.getMaxAre();
        long targetMax = tg.getMaxAre();

        long transfer;

        if (inTarget) {
            long space = targetMax - targetLp;
            transfer = Math.min(amount, Math.min(sourceLp, space));

            if (transfer > 0) {
                sc.reducerLp(-transfer, source);
                tg.reducerLp(transfer, target);
            }
        } else {
            long space = sourceMax - sourceLp;
            transfer = Math.min(amount, Math.min(targetLp, space));

            if (transfer > 0) {
                tg.reducerLp(-transfer, target);
                sc.reducerLp(transfer, source);
            }
        }
    }

    public static AreElement getLpElement(Object obj) {
        if (obj instanceof ItemStack stack) {
            return stack.getItem() instanceof AreElement element ? element : null;
        }
        return obj instanceof AreElement element ? element : null;
    }
}
