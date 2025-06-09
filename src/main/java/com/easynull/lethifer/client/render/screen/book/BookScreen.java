package com.easynull.lethifer.client.render.screen.book;

import com.easynull.lethifer.Lethifer;
import com.easynull.lethifer.client.render.screen.LRScreen;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.utils.RenderUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public final class BookScreen extends LRScreen {
    public static final BookScreen instance = new BookScreen();
    final ResourceLocation bg = Lethifer.locTo("gui/book/background");
    public static Chapter currentChap;
    private Entry currrentEntry;
    int curPage;

    public BookScreen() {
        super(280, 180);
    }

    public void open() {
        ticks = 0;
        currrentEntry = null;
        currentChap = LRResearches.base;
        mc.setScreen(this);
    }

    @Override
    public void rendering(GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        RenderUtils.drawTexture(bg, gg, guiLeft(), guiTop(), 0, 0, bgWeight, bgHeight, 512, 512);
        if (currrentEntry != null) return;
        LRResearches.chapters.forEach(chapter -> chapter.onDraw(bg, gg, guiLeft(), guiTop() - 9, mouseX, mouseY));
        LRResearches.entries.forEach(entry -> entry.onDraw(bg, gg, guiLeft() + 13, (guiTop() + 13) + (1 * 22), mouseX, mouseY));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            if (currrentEntry != null) return false;
            LRResearches.chapters.forEach(chapter -> {
                if(chapter.isHover(guiLeft(), guiTop() - 9, (int) mouseX, (int) mouseY)){
                    currentChap = chapter;
                }
            });
            LRResearches.entries.forEach(entry -> {
                if(entry.isHover(guiLeft(), guiTop(), (int) mouseX, (int) mouseY)){
                    currrentEntry = entry;
                }
            });
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
