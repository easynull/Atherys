package com.easynull.rebyssal.client.render.book;

import com.easynull.rebyssal.Rebyssal;
import com.easynull.rebyssal.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TranslationTextComponent;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class BookScreen extends Screen {
    ResourceLocation bg = Rebyssal.locTo("book/background");
    int bgWeight = 282, bgHeight = 185;
    public static Chapter curChap;
    int curPage;
    float ticks;
    Entry curEntry;
    Minecraft mc = RenderUtils.mc;

    public BookScreen() {
        super(new TranslationTextComponent(""));
        ticks = 0;
        curEntry = null;
        curChap = Entries.base;
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float pTicks) {
        renderBackground(ms);
        RenderUtils.Animator2D anim = new RenderUtils.Animator2D(new RenderUtils.Transform(ms), (float) Math.pow(ticks, 0.8));
        anim.start();
        anim.tr.scale(width / 2f, height / 2f, anim.getTicks(), anim.getTicks(), 0);
        RenderUtils.drawTexture(bg, ms, guiLeft(), guiTop(), 0, 0, bgWeight, bgHeight, 512, 512);
        renderEntries(ms, mouseX, mouseY);
        renderChapters(ms, mouseX, mouseY);
        if (curEntry != null) RenderUtils.drawTexture(bg, ms, guiLeft() + 11, guiTop() + 164, 294, 38, 9, 6, 512, 512);
        anim.extraStop();
    }

    private void renderChapters(MatrixStack ms, int mouseX, int mouseY) {
        if (curEntry != null) return;
        Entries.chapters.forEach(chapter -> chapter.onRender(bg, ms, guiLeft(), guiTop() - 9, mouseX, mouseY));
        if ((curEntry != null ? curEntry.pages.size() : curChap.pages.size()) >= curPage + 3) {
            if (mouseX <= guiLeft() + 269 && mouseX >= guiLeft() + 260 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164) RenderUtils.drawTexture(bg, ms, guiLeft() + 260, guiTop() + 164, 294, 32, 9, 6, 512, 512);
        } else if (curPage != 0) {
            if (mouseX <= guiLeft() + 18 && mouseX >= guiLeft() + 9 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164) RenderUtils.drawTexture(bg, ms, guiLeft() + 11, guiTop() + 164, 294, 38, 9, 6, 512, 512);
        }
    }

    private void renderEntries(MatrixStack ms, int mouseX, int mouseY) {
        if (curEntry != null) return;
        if (curChap.getPage(curPage) != null) {
            for (int i = 0; i < curChap.getPage(curPage).size(); i++) {
                Entry entry = curChap.getPage(curPage).get(i);
                entry.onRender(bg, ms, guiLeft() + 13, (guiTop() + 13) + (i * 22), mouseX, mouseY, true);
            }
        }
        if (curChap.getPage(curPage + 1) != null) {
            for (int i = 0; i < curChap.getPage(curPage + 1).size(); i++) {
                Entry entry = curChap.getPage(curPage + 1).get(i);
                entry.onRender(bg, ms, guiLeft() + 168, (guiTop() + 13) + (i * 22), mouseX, mouseY, false);
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            for(Chapter chapter : Entries.chapters){
                if(chapter.isHover(guiLeft(), guiTop() - 9, (int) mouseX, (int) mouseY) && chapter != curChap){
                    mc.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1f, 1f);
                    curChap = chapter;
                    curPage = 0;
                }
            }
            if (curChap.getPage(curPage) != null) {
                for (int i = 0; i < curChap.getPage(curPage).size(); i++) {
                    Entry entry = curChap.getPage(curPage).get(i);
                    if(entry.isHover(guiLeft() + 13, (guiTop() + 13) + (i * 22), (int) mouseX, (int) mouseY)){
                        mc.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1f, 1f);
                        curEntry = entry;
                        curPage = 0;
                    }
                }
            }
            if (curChap.getPage(curPage + 1) != null) {
                for (int i = 0; i < curChap.getPage(curPage + 1).size(); i++) {
                    Entry entry = curChap.getPage(curPage + 1).get(i);
                    if(entry.isHover(guiLeft() + 168, (guiTop() + 13) + (i * 22), (int) mouseX, (int) mouseY)){
                        mc.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1f, 1f);
                        curEntry = entry;
                        curPage = 0;
                    }
                }
            }
            if((curEntry != null ? curEntry.pages.size() : curChap.pages.size()) >= curPage + 3) {
                if (mouseX <= guiLeft() + 269 && mouseX >= guiLeft() + 260 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164){
                    mc.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1f, 1f);
                    curPage += 2;
                }
            } else if (curPage != 0) {
                if(mouseX <= guiLeft() + 20 && mouseX >= guiLeft() + 11 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164){
                    mc.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1f, 1f);
                    curPage -= 2;
                }
            }
            if(curEntry != null && (mouseX <= guiLeft() + 20 && mouseX >= guiLeft() + 11 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164)){
                mc.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1f, 1f);
                curPage = 0;
                curEntry = null;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void tick() {
        ticks += 0.4f;
    }

    public int guiLeft() {
        return (width - bgWeight) / 2;
    }

    public int guiTop() {
        return (height - bgHeight) / 2;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
