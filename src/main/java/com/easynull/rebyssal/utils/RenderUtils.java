package com.easynull.rebyssal.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.text.ITextComponent;

public final class RenderUtils {
    public static BufferBuilder buffer = Tessellator.getInstance().getBuffer();
    public static Minecraft mc = Minecraft.getInstance();

    public static void drawTexture(ResourceLocation tex, MatrixStack ms, int x, int y, int uOffset, int vOffset, int pixelWidth, int pixelHeight, int texWidth, int texHeight) {
        mc.getTextureManager().bindTexture(tex);
        AbstractGui.blit(ms, x, y, uOffset, vOffset, pixelWidth, pixelHeight, texWidth, texHeight);
    }

    public static void drawTexture(ResourceLocation tex, MatrixStack ms, float x, float y, float size) {
        RenderSystem.enableBlend();
        mc.getTextureManager().bindTexture(tex);
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(ms.getLast().getMatrix(), x + 0.0f, y + size, 0).tex(0.0F, 1.0F).endVertex();
        buffer.pos(ms.getLast().getMatrix(), x + size, y + size, 0).tex(1.0F, 1.0F).endVertex();
        buffer.pos(ms.getLast().getMatrix(), x + size, y + 0.0f, 0).tex(1.0F, 0.0F).endVertex();
        buffer.pos(ms.getLast().getMatrix(), x + 0.0f, y + 0.0f, 0).tex(0.0F, 0.0F).endVertex();
        buffer.finishDrawing();
        RenderSystem.disableBlend();
    }

    public static void drawText(Object text, MatrixStack ms, int x, int y, int color, FontRenderer font) {
        if(text instanceof ITextComponent t) font.drawString(ms, t.getString(), x, y, color);
        if(text instanceof String t) font.drawString(ms, t, x, y, color);
    }

    public static void renderItemGUI(MatrixStack ms, ItemStack stack, int x, int y) {
        RenderSystem.pushMatrix();
        RenderSystem.multMatrix(ms.getLast().getMatrix());
        Minecraft.getInstance().getItemRenderer().renderItemAndEffectIntoGUI(stack, x, y);
        RenderSystem.popMatrix();
    }

    public static void rePaint(int color, Runnable method) {
        float red = (float) ((color >> 16) & 0xFF) / 255.0f;
        float green = (float) ((color >> 8) & 0xFF) / 255.0f;
        float blue = (float) (color & 0xFF) / 255.0f;
        float alpha = (float) ((color >> 24) & 0xFF) / 255.0f;
        RenderSystem.color4f(red, green, blue, alpha);
        method.run();
        RenderSystem.color4f(1f, 1f, 1f, 1f);
    }

    public record Transform(MatrixStack ms) {
        public void start() {
            ms.push();
        }

        public void stop() {
            ms.pop();
        }

        public void rotate(float pX, float pY, Quaternion angel) {
            ms.translate(pX, pY, 0);
            ms.rotate(angel);
            ms.translate(-pX, -pY, 0);
        }

        public void scale(float pX, float pY, float sX, float sY, float sZ) {
            ms.translate(pX, pY, 0);
            ms.scale(sX, sY, sZ);
            ms.translate(-pX, -pY, 0);
        }

        public void moved(float pX, float pY, float pZ) {
            ms.translate(pX, pY, pZ);
        }
    }

    public static class Animator2D {
        public final Transform tr;
        float ticks, mult;
        public boolean isRun;
        final int limiter;

        public Animator2D(Transform tr, float ticks, float mult, int limiter) {
            this.tr = tr;
            this.ticks = ticks;
            this.mult = mult;
            this.limiter = limiter;
            this.isRun = false;
        }

        public Animator2D(Transform tr, float ticks) {
            this(tr, ticks, 0.34f, 1);
        }

        public void start() {
            this.isRun = true;
            this.ticks = MathHelper.clamp(ticks * mult, 0, limiter);
            tr.start();
        }

        public void stop() {
            this.isRun = false;
            this.ticks = 0;
            tr.stop();
        }

        public void extraStop(){
            if(ticks >= limiter){
                stop();
            }
        }

        public float getTicks(){
            return ticks;
        }
    }
}
