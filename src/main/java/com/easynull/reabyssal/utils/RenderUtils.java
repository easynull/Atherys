package com.easynull.reabyssal.utils;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;

public class RenderUtils {
    public static Minecraft mc = Minecraft.getInstance();
    public static MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();

    public static void drawTexture(ResourceLocation tex, GuiGraphics gg, int x, int y, int uOffset, int vOffset, int pixelWidth, int pixelHeight, int texWidth, int texHeight) {
        gg.blit(RenderType::guiTextured, tex, x, y, uOffset, vOffset, pixelWidth, pixelHeight, texWidth, texHeight);
    }

//    public static void drawTexture(ResourceLocation tex, PoseStack ms, float x, float y, float size) {
//        RenderSystem.enableBlend();
//        RenderSystem.setShaderTexture(0, tex);
//        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
//        buffer.pos(ms.last().pose(), x + 0.0f, y + size, 0).tex(0.0F, 1.0F).endVertex();
//        buffer.pos(ms.getLast().getMatrix(), x + size, y + size, 0).tex(1.0F, 1.0F).endVertex();
//        buffer.pos(ms.getLast().getMatrix(), x + size, y + 0.0f, 0).tex(1.0F, 0.0F).endVertex();
//        buffer.pos(ms.getLast().getMatrix(), x + 0.0f, y + 0.0f, 0).tex(0.0F, 0.0F).endVertex();
//        buffer.finishDrawing();
//        RenderSystem.disableBlend();
//    }

    public static void drawText(Object text, GuiGraphics gg, int x, int y, int color, Font font) {
        if(text instanceof Component t) gg.drawString(font, t.getString(), x, y, color);
        if(text instanceof String t) gg.drawString(font, t, x, y, color);
    }

//    public static void renderItemGUI(GuiGraphics gg, ItemStack stack, int x, int y) {
//        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
//        BakedModel model = renderer.getModel(stack, null, null, 0);
//        PoseStack ps = gg.pose();
//        ps.pushPose();
//        ps.translate(x, y, 100.0F);
//        ps.translate(0.5f, 0.5f, 0);
//        ps.scale(1, -1, 1);
//
//        RenderSystem.getModelViewStack()..applyModelViewMatrix();
//        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
//        if (!model.usesBlockLight()) {
//            Lighting.setupForFlatItems();
//        } else {
//            Lighting.setupFor3DItems();
//        }
//
//        gg.renderItem();
//        renderer.renderStatic();
//        buffer.endBatch();
//        ps.popPose();
//        RenderSystem.applyModelViewMatrix();
//    }

    public static void rePaint(int color, Runnable method) {
        float red = (float) ((color >> 16) & 0xFF) / 255.0f;
        float green = (float) ((color >> 8) & 0xFF) / 255.0f;
        float blue = (float) (color & 0xFF) / 255.0f;
        float alpha = (float) ((color >> 24) & 0xFF) / 255.0f;
        RenderSystem.setShaderColor(red, green, blue, alpha);
        method.run();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }

    public record Transform(PoseStack ms) {
        public void start() {
            ms.pushPose();
        }

        public void stop() {
            ms.popPose();
        }

        public void rotate(float pX, float pY, Quaternionf angel) {
            ms.translate(pX, pY, 0);
            ms.mulPose(angel);
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
            this.ticks = Math.clamp(ticks * mult, 0, limiter);
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
