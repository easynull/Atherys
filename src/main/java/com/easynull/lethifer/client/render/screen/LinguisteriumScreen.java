package com.easynull.lethifer.client.render.screen;

import com.easynull.lethifer.api.LetherianLang;
import net.minecraft.client.gui.GuiGraphics;

public final class LinguisteriumScreen extends LRScreen{
    public static final LinguisteriumScreen instance = new LinguisteriumScreen();

    public LinguisteriumScreen() {
        super(99, 99);
    }

    public void open() {
        ticks = 0;
        mc.setScreen(this);
    }

    @Override
    public void rendering(GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        int yOffset = 0;
        for(char c : LetherianLang.getCurrentTranslate().keySet()){
            gg.drawString(mc.font, String.format("%s -> %s", c, LetherianLang.getCurrentTranslate().get(c)), guiLeft() + 30, guiTop() - 115 + yOffset, 0x80000000, false);
            yOffset += 10;
        }
    }
}
