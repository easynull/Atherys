package com.easynull.atherys.client.render.screen.book;

import com.easynull.atherys.Atherys;
import com.easynull.atherys.client.render.screen.LRScreen;
import com.easynull.atherys.core.ASResearches;
import com.easynull.atherys.utils.RenderUtils;
import com.mw.nullcore.core.render.NullScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.lwjgl.glfw.GLFW;

public final class BookScreen extends NullScreen {
    public static final BookScreen instance = new BookScreen();
    final ResourceLocation bg = Atherys.locTo("gui/book/background");
    public static Chapter currentChap;
    private Entry currentEntry;
    int currentPage;

    public BookScreen() {
        super(280, 180);
    }

    public void open() {
        ticks = 0;
        currentChap = ASResearches.basic;
        mc.setScreen(this);
    }

    @Override
    public void draw(GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        RenderUtils.drawTexture(bg, gg, guiLeft(), guiTop(), 0, 0, bgWeight, bgHeight, 512, 512);
        renderTurning(gg, mouseX, mouseY);
        if (currentEntry != null) return;
        ASResearches.chapters.forEach(chapter -> chapter.onDraw(bg, gg, guiLeft(), guiTop() - 9, mouseX, mouseY));
        ASResearches.entries.forEach(entry -> {
            renderPage(gg, mouseX, mouseY, currentPage, 13);
            renderPage(gg, mouseX, mouseY, currentPage + 1, 168);
        });
    }

    private void renderPage(GuiGraphics gg, int mouseX, int mouseY, int page, int xOffset) {
        if (currentChap.getPage(page) == null) return;
        for (int i = 0; i < currentChap.getPage(page).size(); i++) {
            Entry entry = currentChap.getPage(page).get(i);
            entry.onDraw(bg, gg, guiLeft() + xOffset, guiTop() + 13 + (i * 22), mouseX, mouseY);
        }
    }

    private void renderTurning(GuiGraphics gg, int mouseX, int mouseY){
        int maxPages = currentEntry != null ? currentEntry.pages.size() : currentChap.pages.size();
        if (maxPages >= currentPage + 3) {
            RenderUtils.drawTexture(bg, gg, guiLeft() + 261, guiTop() + 160, 292, 42, 10, 10, 512, 512);
        } else if (currentEntry != null || currentPage != 0) RenderUtils.drawTexture(bg, gg, guiLeft() + 10, guiTop() + 158, 292, 32, 10, 10, 512, 512);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != GLFW.GLFW_MOUSE_BUTTON_LEFT) return super.mouseClicked(mouseX, mouseY, button);
        for (Chapter chapter : ASResearches.chapters) {
            if (chapter != currentChap && chapter.isHover(guiLeft(), guiTop() - 9, (int) mouseX, (int) mouseY)) {
                sound(SoundEvents.BOOK_PUT);
                currentChap = chapter;
                reset();
                return true;
            }
        }
        if (isEntryClick(currentPage, 13, mouseX, mouseY) || isEntryClick(currentPage + 1, 168, mouseX, mouseY)) {
            return true;
        }
        if (handlePageTurning(mouseX, mouseY)) {
            return true;
        }
        if (currentEntry != null && isBackButton(mouseX, mouseY)) {
            sound(SoundEvents.BOOK_PAGE_TURN);
            reset();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isEntryClick(int page, int xOffset, double mouseX, double mouseY) {
        if (currentChap.getPage(page) == null) return false;
        for (int i = 0; i < currentChap.getPage(page).size(); i++) {
            Entry entry = currentChap.getPage(page).get(i);
            if (entry.isHover(guiLeft() + xOffset, guiTop() + 13 + (i * 22), (int) mouseX, (int) mouseY)) {
                sound(SoundEvents.BOOK_PAGE_TURN);
                System.out.print(entry.getID());
                currentEntry = entry;
                currentPage = 0;
                return true;
            }
        }
        return false;
    }

    private boolean handlePageTurning(double mouseX, double mouseY) {
        int maxPages = currentEntry != null ? currentEntry.pages.size() : currentChap.pages.size();
        if (maxPages >= currentPage + 3 && isNextButton(mouseX, mouseY)) {
            sound(SoundEvents.BOOK_PAGE_TURN);
            currentPage += 2;
            return true;
        } else if (currentPage != 0 && isBackButton(mouseX, mouseY)) {
            sound(SoundEvents.BOOK_PAGE_TURN);
            currentPage -= 2;
            return true;
        }
        return false;
    }

    private boolean isNextButton(double mouseX, double mouseY) {
        return mouseX >= guiLeft() + 261 && mouseX <= guiLeft() + 271 && mouseY >= guiTop() + 158 && mouseY <= guiTop() + 168;
    }

    private boolean isBackButton(double mouseX, double mouseY) {
        return mouseX >= guiLeft() + 10 && mouseX <= guiLeft() + 20 && mouseY >= guiTop() + 158 && mouseY <= guiTop() + 168;
    }

    private void reset() {
        currentPage = 0;
        currentEntry = null;
    }

    private void sound(SoundEvent sound) {
        mc.player.playSound(sound, 1f, 1f);
    }
}
