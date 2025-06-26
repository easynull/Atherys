package com.easynull.atherys.client.render.screen.book;

import com.easynull.atherys.api.researches.Research;
import com.easynull.atherys.core.ASResearches;
import com.mw.nullcore.utils.RenderUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.easynull.atherys.client.render.screen.book.BookScreen.currentChap;

public final class Chapter extends Research<Chapter> {
    public final HashSet<Entry> children;
    public List<ArrayList<Entry>> pages;
    public final int index;

    public Chapter(String name, ItemLike icon) {
        super(name, icon, 21, 19);
        this.children = new HashSet<>();
        this.index = ASResearches.chapters.indexOf(this);
        addPage();
    }

    @Override
    public void onDraw(ResourceLocation bg, GuiGraphics gg, int pX, int pY, int mouseX, int mouseY) {
        if(index > 14) return;
        int actualX = getXOffset(pX);
        int actualY = getYOffset(pY);
        boolean hover = isHover(pX, pY, mouseX, mouseY) || equals(currentChap);
        RenderUtils.drawTexture(bg, gg, actualX - (hover ? 18 : 17), actualY, 280 + (hover ? width : 0), index > 6 ? 0 : height, width + (hover ? 1 : 0), height, 512, 512);
        if (isUnlocked()) {
            gg.renderItem(icon.asItem().getDefaultInstance(), actualX - (isHover(pX, pY, mouseX, mouseY) || equals(currentChap) ? 15 : 14), actualY + 1);
        } else {
            RenderUtils.drawTexture(bg, gg, actualX - (index > 6 ? 13 : 12), actualY + 3, 280, 38, 12, 12, 512, 512);
        }
    }

    @Override
    public boolean isHover(int pX, int pY, int mouseX, int mouseY) {
        return isUnlocked() && mouseX >= getXOffset(pX) - width  && mouseX <= getXOffset(pX) + 4 && mouseY >= getYOffset(pY) && mouseY <= getYOffset(pY) + height;
    }

    public void addChild(Entry entry) {
        List<Entry> lastPage = pages.getLast();
        if (lastPage.size() >= 6) {
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
        if (index > 6) {
            xOffset = 292;
        }
        return xOffset + pX;
    }

    public int getYOffset(int pY) {
        int i = index;
        if (index > 6) {
            i = index - 7;
        }
        return (i * 22 + 21) + pY;
    }
}
