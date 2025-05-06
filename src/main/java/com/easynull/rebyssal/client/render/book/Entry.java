package com.easynull.rebyssal.client.render.book;

import com.easynull.rebyssal.Rebyssal;
import com.easynull.rebyssal.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;

import static com.easynull.rebyssal.client.render.book.BookScreen.curChap;

public final class Entry {
    public final String name;
    final Object icon;
    public final Chapter chapter;
    public final ArrayList<Page> pages;
    public final ArrayList<Entry> children;
    public final ArrayList<Item> items;
    public boolean unlocked;

    public Entry(String name, Object icon, Chapter chapter) {
        this.name = name;
        this.icon = icon;
        this.chapter = chapter;
        this.pages = new ArrayList<>();
        this.children = new ArrayList<>();
        this.items = new ArrayList<>();
        this.unlocked = false;
        chapter.addChildren(this);
        Entries.entries.add(this);
    }

    public void onRender(ResourceLocation bg, MatrixStack ms, int pX, int pY, int mouseX, int mouseY, boolean left) {
        if (chapter == curChap) {
            RenderUtils.rePaint(isUnlocked() ? 0xFFFFFFFF : 0xFFA9A9A9, ()-> {
                RenderUtils.drawTexture(bg, ms, pX, pY, 0, left ? 185 : 203, 24, left ? 18 : 20, 512, 512);
                RenderUtils.Transform tr = new RenderUtils.Transform(ms);
                tr.start();
                tr.scale(pX + 24, pY + 8, 0.65f, 0.7f, 0);
                RenderUtils.drawText(Rebyssal.translation("entry", name), ms, pX + 28, pY + 5, 0xFF7F2D1B, isUnlocked() ? Minecraft.getInstance().fontRenderer : Rebyssal.symbols);
                tr.stop();
            });
            if (isUnlocked()) {
                if (icon instanceof Item item) RenderUtils.renderItemGUI(ms, item.getDefaultInstance(), pX + 5, pY + (left ? 1 : 2));
                else if (icon instanceof Block block) RenderUtils.renderItemGUI(ms, block.asItem().getDefaultInstance(), pX + 5, pY + (left ? 1 : 2));
                else if (icon instanceof ResourceLocation loc) RenderUtils.drawTexture(loc, ms, pX + 5, pY + (left ? 1 : 2), 16);
            } else {
                RenderUtils.drawTexture(bg, ms, pX + 7, pY + (left ? 3 : 4), 282, 32, 12, 12, 512, 512);
            }
        }
    }

    public boolean isHover(int pX, int pY, int mouseX, int mouseY){
        return isUnlocked() && chapter == curChap && mouseX <= pX + 95 && mouseX >= pX && mouseY <= pY + 20 && mouseY >= pY;
    }

    public boolean isUnlocked(){
        return unlocked;
    }

    public Entry unlock(){
        this.unlocked = true;
        return this;
    }

    public Entry addPages(Page... pages){
        this.pages.addAll(Arrays.asList(pages));
        return this;
    }

    public Entry addChildren(Entry... entries){
        children.addAll(Arrays.asList(entries));
        return this;
    }

    public Entry addEntryItems(Object... items){
        for (Object o : items){
            if(o instanceof Block block){
                this.items.add(block.asItem());
            } else {
                this.items.add((Item) o);
            }
        }
        return this;
    }
}
