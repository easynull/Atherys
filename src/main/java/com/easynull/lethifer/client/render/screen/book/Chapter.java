package com.easynull.lethifer.client.render.screen.book;

import com.easynull.lethifer.api.Research;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.utils.RenderUtils;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

import static com.easynull.lethifer.client.render.screen.book.BookScreen.curChap;

public final class Chapter implements Research {
    public final String name;
    final Object icon;
    public final String cipher;
    public final ArrayList<Entry> children;
    public final ArrayList<ArrayList<Entry>> pages;
    public boolean unlocked;
    public final int index;

    public Chapter(String name, Object icon) {
        this.name = name;
        this.cipher = "cipher.lethifer." + name;
        this.icon = icon;
        this.children = new ArrayList<>();
        this.pages = new ArrayList<>();
        this.unlocked = false;
        addPage();
        LRResearches.chapters.add(this);
        this.index = LRResearches.chapters.indexOf(this);
    }

    public void onRender(ResourceLocation bg, GuiGraphics gg, int pX, int pY, int mouseX, int mouseY) {
        int actualX = getXOffset(pX);
        int actualY = getYOffset(pY);
        RenderUtils.drawTexture(bg, gg, actualX - (isHover(pX, pY, mouseX, mouseY) || equals(curChap) ? 18 : 17), actualY, isHover(pX, pY, mouseX, mouseY) || equals(curChap) ? 304 : 282, index > 7 ? 0 : 16, 22, 16, 512, 512);
        if (isUnlocked()) {
            if (icon instanceof ItemLike i) RenderUtils.renderItemGUI(gg, i.asItem().getDefaultInstance(), actualX - (isHover(pX, pY, mouseX, mouseY) || equals(curChap) ? 13 : 12), actualY);
        } else {
            RenderUtils.drawTexture(bg, gg, actualX - 10, actualY + 2, 282, 32, 12, 12, 512, 512);
        }
    }

    public boolean isHover(int pX, int pY, int mouseX, int mouseY) {
        return isUnlocked() && mouseX <= getXOffset(pX) + 4 && mouseX >= getXOffset(pX) - 17 && mouseY <= getYOffset(pY) + 18 && mouseY >= getYOffset(pY);
    }

    @Override
    public boolean isUnlocked() {
        return ResearchUtils.isUnlocked(RenderUtils.mc.player, this) || unlocked;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCipher() {
        return Component.translatable(cipher).getString();
    }

    public Chapter unlock() {
        this.unlocked = true;
        return this;
    }

    public void addChildren(Entry entry) {
        List<Entry> lastPage = pages.getLast();
        if (lastPage.size() >= 7) {
            addPage();
            lastPage = pages.getLast();
        }
        lastPage.add(entry);
    }

    private void addPage() {
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
