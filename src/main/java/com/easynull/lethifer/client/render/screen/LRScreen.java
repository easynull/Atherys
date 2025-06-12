package com.easynull.lethifer.client.render.screen;

import com.easynull.lethifer.utils.RenderUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public abstract class LRScreen extends Screen {
    public final Minecraft mc = Minecraft.getInstance();
    public float ticks = 0;
    public final int bgWeight, bgHeight;

    protected LRScreen(int bgWeight, int bgHeight) {
        super(Component.empty());
        this.bgWeight = bgWeight;
        this.bgHeight = bgHeight;
    }

    @Override
    public void render(@NotNull GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        renderBackground(gg, mouseX, mouseY, pTicks);
        RenderUtils.Transform tr = new RenderUtils.Transform(gg.pose());
        tr.moved(0, 200 - ticks * 200, 0);
        tr.scale(width / 2f, height / 2f, ticks, ticks, 0);
        rendering(gg, mouseX, mouseY, pTicks);
        tr.stop();
    }

    public abstract void rendering(GuiGraphics gg, int mouseX, int mouseY, float pTicks);

    @Override
    public void tick() {
        ticks = (float) Mth.clamp(Math.pow(ticks + 0.4, 2.2d), 0, 1);
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
