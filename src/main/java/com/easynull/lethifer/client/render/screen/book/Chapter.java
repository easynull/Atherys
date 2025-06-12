package com.easynull.lethifer.client.render.screen.book;

import com.easynull.lethifer.api.researches.Research;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.utils.RenderUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.easynull.lethifer.client.render.screen.book.BookScreen.currentChap;

public final class Chapter extends Research<Chapter> {
    public final HashSet<Entry> children;
    public List<ArrayList<Entry>> pages;
    public final int index;

    public Chapter(String name, ItemLike icon) {
        super(name, icon, 21, 16);
        this.children = new HashSet<>();
        this.index = LRResearches.chapters.indexOf(this);
        addPage();
    }

    @Override
    public void onDraw(ResourceLocation bg, GuiGraphics gg, int pX, int pY, int mouseX, int mouseY) {
        int actualX = getXOffset(pX);
        int actualY = getYOffset(pY);
        RenderUtils.rePaint(0x80FF0000, ()-> RenderUtils.drawTexture(bg, gg, actualX - (isHover(pX, pY, mouseX, mouseY) || equals(currentChap) ? 18 : 17), actualY, 280, index > 7 ? 0 : 16, width, height, 512, 512));
        if(isHover(pX, pY, mouseX, mouseY) || equals(currentChap)){
            RenderUtils.drawTexture(bg, gg, actualX - 18, actualY, 280 + width, index > 7 ? 0 : 16, width, height, 512, 512);
        }
        if (isUnlocked()) {
            RenderUtils.renderItemGUI(gg, icon.asItem().getDefaultInstance(), actualX - (isHover(pX, pY, mouseX, mouseY) || equals(currentChap) ? 13 : 12), actualY);
        } else {
            RenderUtils.drawTexture(bg, gg, actualX - 11, actualY + 2, 280, 32, 12, 12, 512, 512);
        }
    }

    @Override
    public boolean isHover(int pX, int pY, int mouseX, int mouseY) {
        return isUnlocked() && mouseX >= getXOffset(pX) - width  && mouseX <= getXOffset(pX) + 4 && mouseY >= getYOffset(pY) && mouseY <= getYOffset(pY) + height;
    }

    public void addChild(Entry entry) {
        List<Entry> lastPage = pages.getLast();
        if (lastPage.size() >= 7) {
            addPage();
            lastPage = pages.getLast();
        }
        lastPage.add(entry);
    }

    private void addPage() {
        if(pages == null) pages = new ArrayList<>();
        pages.add(new ArrayList<>());
    }

    public ArrayList<Entry> getPage(int i) {
        if (i >= pages.size()) return null;
        return pages.get(i);
    }

    public int getXOffset(int pX) {
        int xOffset = 1;
        if (index > 7) {
            xOffset = 292;
        }
        return xOffset + pX;
    }

    public int getYOffset(int pY) {
        int i = index;
        if (index > 7) {
            i = index - 8;
        }
        return (i * 21 + 20) + pY;
    }
}
