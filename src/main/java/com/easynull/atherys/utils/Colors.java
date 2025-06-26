package com.easynull.atherys.utils;

import java.awt.*;

import static com.mw.nullcore.utils.ColorUtils.arrayColor;

public final class Colors {
    public static int getRedBlueColor(float per) {
        int[] colors = new int[100];
        for(int i = 0; i < colors.length; i++) {
            float hue;
            if (i < colors.length/2) {
                hue = 0.8f * (i / (float)(colors.length/2));
            } else {
                hue = 0.8f + 0.2f * ((i - (float) colors.length /2) / (float)(colors.length/2));
                if (hue > 1.0f) hue -= 1.0f;
            }
            colors[i] = Color.HSBtoRGB(hue, 1.0f, 1.0f) & 0xFFFFFF;
        }

        return arrayColor(colors, per);
    }
}
