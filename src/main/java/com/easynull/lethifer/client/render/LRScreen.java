package com.easynull.lethifer.client.render;

import com.easynull.lethifer.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public abstract class LRScreen extends Screen {
    public final Minecraft mc = RenderUtils.mc;
    public float ticks = 0;
    public final int bgWeight, bgHeight;

    protected LRScreen(int bgWeight, int bgHeight) {
        super(Component.empty());
        this.bgWeight = bgWeight;
        this.bgHeight = bgHeight;
    }

    @Override
    public void render(GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        if(mc == null) return;
        renderBackground(gg, mouseX, mouseY, pTicks);
        RenderUtils.Animator2D anim = new RenderUtils.Animator2D(new RenderUtils.Transform(gg.pose()), ticks);
        anim.start();
        anim.tr.scale(width / 2f, height / 2f, anim.ticks, anim.ticks, 0);
        rendering(gg, mouseX, mouseY, pTicks);
        for (Renderable renderable : this.renderables) {
            renderable.render(gg, mouseX, mouseY, pTicks);
        }
        anim.extraStop();
    }

    public abstract void rendering(GuiGraphics gg, int mouseX, int mouseY, float pTicks);

    @Override
    public void tick() {
        ticks = Mth.clamp(ticks + 0.45f, 0, 1);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public int guiLeft() {
        return (width - bgWeight) / 2;
    }

    public int guiTop() {
        return (height - bgHeight) / 2;
    }
}
