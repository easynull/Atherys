package com.easynull.lethifer.client.render.book;

import com.easynull.lethifer.api.Research;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.utils.RenderUtils;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Arrays;

import static com.easynull.lethifer.client.render.book.BookScreen.curChap;

public final class Entry implements Research {
    public final String name, cipher;
    final Object icon;
    public final Chapter chapter;
    public final ArrayList<Page> pages;
    public final ArrayList<Entry> children;
    public final ArrayList<Item> items;
    public boolean unlocked;

    public Entry(String name, String cipher, Object icon, Chapter chapter) {
        this.name = name;
        this.cipher = cipher;
        this.icon = icon;
        this.chapter = chapter;
        this.pages = new ArrayList<>();
        this.children = new ArrayList<>();
        this.items = new ArrayList<>();
        this.unlocked = false;
        chapter.addChildren(this);
        LRResearches.entries.add(this);
    }

    public void onRender(ResourceLocation bg, GuiGraphics gg, int pX, int pY, int mouseX, int mouseY, boolean left) {
        if (chapter == curChap) {
            RenderUtils.drawTexture(bg, gg, pX, pY, 0, left ? 185 : 203, 24, left ? 18 : 20, 512, 512);
            RenderUtils.Transform tr = new RenderUtils.Transform(gg.pose());
            tr.start();
            tr.scale(pX + 22, pY + 9, 0.7f, 0.7f, 0);
            RenderUtils.drawText(isUnlocked() ? Component.translatable("entry.lethifer." + name) : Component.translatable("entry.lethifer." + name).getString().replaceAll(".", "-"), gg, pX + 28, pY + 5, 0xFF7F2D1B);
            tr.stop();
            if (isUnlocked()) {
                if (icon instanceof ItemLike i) RenderUtils.renderItemGUI(gg, i.asItem().getDefaultInstance(), pX + 5, pY + (left ? 1 : 2));
//                else if (icon instanceof ResourceLocation loc) RenderUtils.drawTexture(loc, gg, pX + 5, pY + (left ? 1 : 2), 16);
            } else {
                RenderUtils.drawTexture(bg, gg, pX + 7, pY + (left ? 3 : 4), 282, 32, 12, 12, 512, 512);
            }
        }
    }

    public boolean isHover(int pX, int pY, int mouseX, int mouseY){
        return isUnlocked() && chapter == curChap && mouseX <= pX + 95 && mouseX >= pX && mouseY <= pY + 20 && mouseY >= pY;
    }

    @Override
    public boolean isUnlocked(){
        return ResearchUtils.isUnlocked(RenderUtils.mc.player, this) || unlocked;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCipher() {
        return cipher;
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

    public Entry addEntryItems(ItemLike... items){
        for (ItemLike o : items){
            this.items.add(o.asItem());
        }
        return this;
    }
}
