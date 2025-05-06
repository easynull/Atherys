package com.easynull.rebyssal.client.render.book;

import com.easynull.rebyssal.api.LimitedList;
import com.easynull.rebyssal.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    public final String name;
    final Object icon;
    public final ArrayList<Entry> children;
    public final ArrayList<LimitedList<Entry>> pages;
    public boolean unlocked;
    public final int index, color;

    public Chapter(String name, Object icon, int index, int color) {
        this.name = name;
        this.icon = icon;
        this.children = new ArrayList<>();
        this.pages = new ArrayList<>();
        this.unlocked = false;
        this.index = index;
        this.color = color;
        addPage();
        Entries.chapters.add(this);
    }

    public void onRender(ResourceLocation bg, MatrixStack ms, int pX, int pY, int mouseX, int mouseY) {
        int actualX = getXOffset(pX);
        int actualY = getYOffset(pY);
        RenderUtils.rePaint(isUnlocked() ? color : 0xFFFFFFFF, () -> RenderUtils.drawTexture(bg, ms, actualX - (isHover(pX, pY, mouseX, mouseY) ? 18 : 17), actualY, isHover(pX, pY, mouseX, mouseY) ? 304 : 282, index > 7 ? 0 : 16, 22, 16, 512, 512));
        if (isUnlocked()) {
            if (icon instanceof Item item) RenderUtils.renderItemGUI(ms, item.getDefaultInstance(), actualX - (isHover(pX, pY, mouseX, mouseY) ? 13 : 12), actualY);
            else if (icon instanceof Block block) RenderUtils.renderItemGUI(ms, block.asItem().getDefaultInstance(), actualX - (isHover(pX, pY, mouseX, mouseY) ? 13 : 12), actualY);
            else if (icon instanceof ResourceLocation loc) RenderUtils.drawTexture(loc, ms, actualX - 13, actualY + 3, 16);
        } else {
            RenderUtils.drawTexture(bg, ms, actualX - 10, actualY + 2, 282, 32, 12, 12, 512, 512);
        }
    }

    public boolean isHover(int pX, int pY, int mouseX, int mouseY){
        return isUnlocked() && mouseX <= getXOffset(pX) + 4 && mouseX >= getXOffset(pX) - 17 && mouseY <= getYOffset(pY) + 18 && mouseY >= getYOffset(pY);
    }

    public boolean isUnlocked(){
        return unlocked;
    }

    public Chapter unlock(){
        this.unlocked = true;
        return this;
    }

    public void addChildren(Entry entry){
        List<Entry> lastPage = pages.get(pages.size() - 1);
        if (lastPage.size() >= 7) {
            addPage();
            lastPage = pages.get(pages.size() - 1);
        }
        lastPage.add(entry);
    }

    private void addPage(){
        pages.add(new LimitedList<>(7));
    }

    public LimitedList<Entry> getPage(int i){
        if(i >= pages.size()) return null;
        return pages.get(i);
    }

    public int getXOffset(int pX){
        int xOffset = 1;
        if (index > 7) {
            xOffset = 292;
        }
        return xOffset + pX;
    }

    public int getYOffset(int pY){
        int i = index;
        if (index > 7) {
            i = index - 8;
        }
        return (i * 21 + 20) + pY;
    }
}
