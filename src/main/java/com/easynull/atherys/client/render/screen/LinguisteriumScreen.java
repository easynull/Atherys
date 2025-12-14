package com.easynull.atherys.client.render.screen;

import com.mw.nullcore.client.screen.NullScreen;
import net.minecraft.client.gui.GuiGraphics;

public final class LinguisteriumScreen extends NullScreen {
    public static final LinguisteriumScreen instance = new LinguisteriumScreen();

    public LinguisteriumScreen() {
        super(99, 99, 0);
    }

    public void open() {
        ticks = 0;
        mc().setScreen(this);
    }

    @Override
    protected void draw(GuiGraphics gg, int i, int i1, float v) {
    }
}
