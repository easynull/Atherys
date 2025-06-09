package com.easynull.lethifer.client.render.screen.book;

import com.easynull.lethifer.api.LetherianLang;
import com.easynull.lethifer.api.researches.Research;
import com.easynull.lethifer.utils.RenderUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Arrays;

import static com.easynull.lethifer.client.render.screen.book.BookScreen.currentChap;

public final class Entry extends Research<Entry> {
    public final Chapter chapter;
    public Difficulty difficulty;
    public final ArrayList<Page> pages;
    public Entry parent;
    public final ArrayList<Item> items;
    final int pX, pY;

    public Entry(String name, ItemLike icon, Chapter chapter, int pX, int pY) {
        this(name, icon, chapter, Difficulty.basic, pX, pY);
    }

    public Entry(String name, ItemLike icon, Chapter chapter, Difficulty difficulty, int pX, int pY) {
        super(name, icon, 18, 18);
        this.chapter = chapter;
        this.difficulty = difficulty;
        this.pages = new ArrayList<>();
        this.items = new ArrayList<>();
        this.pX = pX;
        this.pY = pY;
        chapter.addChild(this);
    }

    @Override
    public void onDraw(ResourceLocation bg, GuiGraphics gg, int pX, int pY, int mouseX, int mouseY) {
        if(chapter == currentChap){
            RenderUtils.drawTexture(bg, gg, pX, pY, 0, difficulty.v, width, height, 512, 512);
            RenderUtils.Transform tr = new RenderUtils.Transform(gg.pose());
            tr.start();
            tr.scale(pX + 22, pY + 9, 0.7f, 0.7f, 0);
            RenderUtils.drawText(LetherianLang.translate(Component.literal(getName()), isUnlocked()), gg, pX + 20, pY + 5, 0xFF7F2D1B);
            RenderUtils.drawTexture(bg, gg, pX + width, pY + 6, 18, 193, 99, 4, 512, 512);
            tr.stop();
            if (isUnlocked()) {
                RenderUtils.renderItemGUI(gg, icon.asItem().getDefaultInstance(), pX + 5, pY + 2);
            } else {
                RenderUtils.drawTexture(bg, gg, pX + 7, pY + 4, 282, 32, 12, 12, 512, 512);
            }
        }
    }

    @Override
    public Research getParent() {
        return parent;
    }

    @Override
    public int getXOffset(int pX) {
        return this.pX * 20 + pX;
    }

    @Override
    public int getYOffset(int pY) {
        return this.pY * 20 + pY;
    }

    public Entry addPages(Page... pages){
        this.pages.addAll(Arrays.asList(pages));
        return this;
    }

    public Entry setParent(Entry parent){
        this.parent = parent;
        return this;
    }

    public Entry addEntryItems(ItemLike... items){
        for (ItemLike o : items){
            this.items.add(o.asItem());
        }
        return this;
    }

    enum Difficulty {
        locked(0),
        basic(0),
        arcana(16),
        archaic(32),
        forbidden(48);

        public final int v;
        Difficulty(int v){
            this.v = v + 180;
        }

        public Component getTranslate(){
            return Component.translatable("difficulty." + name());
        }
    }
}
