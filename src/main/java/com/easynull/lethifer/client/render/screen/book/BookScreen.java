package com.easynull.lethifer.client.render.screen.book;

import com.easynull.lethifer.Lethifer;
import com.easynull.lethifer.client.render.screen.LRScreen;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.utils.RenderUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import org.lwjgl.glfw.GLFW;

public final class BookScreen extends LRScreen {
    public static final BookScreen instance = new BookScreen();
    final ResourceLocation bg = Lethifer.locTo("gui/book/background");
    public static Chapter curChap;
    Entry curEntry;
    int curPage;

    public BookScreen() {
        super(282, 185);
    }

    public void open() {
        ticks = 0;
        curEntry = null;
        curChap = LRResearches.base;
        mc.setScreen(this);
    }

    @Override
    public void rendering(GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        RenderUtils.drawTexture(bg, gg, guiLeft(), guiTop(), 0, 0, bgWeight, bgHeight, 512, 512);
        renderEntries(gg, mouseX, mouseY);
        renderChapters(gg, mouseX, mouseY);
        if (curEntry != null) RenderUtils.drawTexture(bg, gg, guiLeft() + 11, guiTop() + 164, 294, 38, 9, 6, 512, 512);
    }

    private void renderChapters(GuiGraphics gg, int mouseX, int mouseY) {
        if (curEntry != null) return;
        LRResearches.chapters.forEach(chapter -> chapter.onRender(bg, gg, guiLeft(), guiTop() - 9, mouseX, mouseY));
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
            for(Chapter chapter : LRResearches.chapters){
                if(chapter.isHover(guiLeft(), guiTop() - 9, (int) mouseX, (int) mouseY) && chapter != curChap){
                    mc.player.playSound(SoundEvents.BOOK_PUT, 1f, 1f);
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
}
