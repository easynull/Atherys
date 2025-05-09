package com.easynull.reabyssal.client.render.book;

import com.easynull.reabyssal.ReAbyssal;
import com.easynull.reabyssal.utils.RenderUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;

public class BookScreen extends Screen {
    public static BookScreen instance = new BookScreen();
    final ResourceLocation bg = ReAbyssal.locTo("gui/book/background");
    int bgWeight = 282, bgHeight = 185;
    public static Chapter curChap;
    int curPage;
    float ticks;
    Entry curEntry;
    Minecraft mc = RenderUtils.mc;

    public BookScreen() {
        super(Component.translatable(""));
    }

    public void openTo(Player player){
        ticks = 0;
        curEntry = null;
        curChap = Entries.base;
        mc.setScreen(this);
        player.playSound(SoundEvents.BOOK_PAGE_TURN, 1f, 1f);
    }

    @Override
    public void render(GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        renderBackground(gg, mouseX, mouseY, pTicks);
        RenderUtils.Animator2D anim = new RenderUtils.Animator2D(new RenderUtils.Transform(gg.pose()), (float) Math.pow(ticks, 0.8));
        anim.start();
        anim.tr.scale(width / 2f, height / 2f, anim.getTicks(), anim.getTicks(), 0);
        RenderUtils.drawTexture(bg, gg, guiLeft(), guiTop(), 0, 0, bgWeight, bgHeight, 512, 512);
        renderEntries(gg, mouseX, mouseY);
        renderChapters(gg, mouseX, mouseY);
        if (curEntry != null) RenderUtils.drawTexture(bg, gg, guiLeft() + 11, guiTop() + 164, 294, 38, 9, 6, 512, 512);
        anim.extraStop();
    }

    private void renderChapters(GuiGraphics gg, int mouseX, int mouseY) {
        if (curEntry != null) return;
        Entries.chapters.forEach(chapter -> chapter.onRender(bg, gg, guiLeft(), guiTop() - 9, mouseX, mouseY));
        if ((curEntry != null ? curEntry.pages.size() : curChap.pages.size()) >= curPage + 3) {
            if (mouseX <= guiLeft() + 269 && mouseX >= guiLeft() + 260 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164) RenderUtils.drawTexture(bg, gg, guiLeft() + 260, guiTop() + 164, 294, 32, 9, 6, 512, 512);
        } else if (curPage != 0) {
            if (mouseX <= guiLeft() + 18 && mouseX >= guiLeft() + 9 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164) RenderUtils.drawTexture(bg, gg, guiLeft() + 11, guiTop() + 164, 294, 38, 9, 6, 512, 512);
        }
    }

    private void renderEntries(GuiGraphics gg, int mouseX, int mouseY) {
        if (curEntry != null) return;
        if (curChap.getPage(curPage) != null) {
            for (int i = 0; i < curChap.getPage(curPage).size(); i++) {
                Entry entry = curChap.getPage(curPage).get(i);
                entry.onRender(bg, gg, guiLeft() + 13, (guiTop() + 13) + (i * 22), mouseX, mouseY, true);
            }
        }
        if (curChap.getPage(curPage + 1) != null) {
            for (int i = 0; i < curChap.getPage(curPage + 1).size(); i++) {
                Entry entry = curChap.getPage(curPage + 1).get(i);
                entry.onRender(bg, gg, guiLeft() + 168, (guiTop() + 13) + (i * 22), mouseX, mouseY, false);
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            for(Chapter chapter : Entries.chapters){
                if(chapter.isHover(guiLeft(), guiTop() - 9, (int) mouseX, (int) mouseY) && chapter != curChap){
                    mc.player.playSound(SoundEvents.BOOK_PAGE_TURN, 1f, 1f);
                    curChap = chapter;
                    curPage = 0;
                }
            }
            if (curChap.getPage(curPage) != null) {
                for (int i = 0; i < curChap.getPage(curPage).size(); i++) {
                    Entry entry = curChap.getPage(curPage).get(i);
                    if(entry.isHover(guiLeft() + 13, (guiTop() + 13) + (i * 22), (int) mouseX, (int) mouseY)){
                        mc.player.playSound(SoundEvents.BOOK_PAGE_TURN, 1f, 1f);
                        curEntry = entry;
                        curPage = 0;
                    }
                }
            }
            if (curChap.getPage(curPage + 1) != null) {
                for (int i = 0; i < curChap.getPage(curPage + 1).size(); i++) {
                    Entry entry = curChap.getPage(curPage + 1).get(i);
                    if(entry.isHover(guiLeft() + 168, (guiTop() + 13) + (i * 22), (int) mouseX, (int) mouseY)){
                        mc.player.playSound(SoundEvents.BOOK_PAGE_TURN, 1f, 1f);
                        curEntry = entry;
                        curPage = 0;
                    }
                }
            }
            if((curEntry != null ? curEntry.pages.size() : curChap.pages.size()) >= curPage + 3) {
                if (mouseX <= guiLeft() + 269 && mouseX >= guiLeft() + 260 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164){
                    mc.player.playSound(SoundEvents.BOOK_PAGE_TURN, 1f, 1f);
                    curPage += 2;
                }
            } else if (curPage != 0) {
                if(mouseX <= guiLeft() + 20 && mouseX >= guiLeft() + 11 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164){
                    mc.player.playSound(SoundEvents.BOOK_PAGE_TURN, 1f, 1f);
                    curPage -= 2;
                }
            }
            if(curEntry != null && (mouseX <= guiLeft() + 20 && mouseX >= guiLeft() + 11 && mouseY <= guiTop() + 170 && mouseY >= guiTop() + 164)){
                mc.player.playSound(SoundEvents.BOOK_PAGE_TURN, 1f, 1f);
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
