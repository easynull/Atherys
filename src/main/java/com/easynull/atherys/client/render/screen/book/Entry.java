package com.easynull.atherys.client.render.screen.book;

import com.easynull.atherys.core.AeterianLang;
import com.easynull.atherys.core.researches.Research;
import com.mw.nullcore.Utils;
import com.mw.nullcore.client.render.Transform;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Arrays;

import static com.easynull.atherys.client.render.screen.book.BookScreen.currentChap;

public final class Entry extends Research<Entry> {
    public final Chapter chapter;
    public Difficulty difficulty;
    public final ArrayList<Page> pages;
    public Entry parent;
    public final ArrayList<Item> items;

    public Entry(String name, ItemLike icon, Chapter chapter) {
        this(name, icon, chapter, Difficulty.basic);
    }

    public Entry(String name, ItemLike icon, Chapter chapter, Difficulty difficulty) {
        super(name, icon, 18, 18);
        this.chapter = chapter;
        this.difficulty = difficulty;
        this.pages = new ArrayList<>();
        this.items = new ArrayList<>();
        chapter.addChild(this);
    }

    @Override
    public void onDraw(ResourceLocation bg, GuiGraphics gg, int pX, int pY, int mouseX, int mouseY) {
        if(chapter == currentChap){
            Utils.Render.drawTexture(gg, bg, pX, pY, 0, difficulty.v, width, 18, 512, 512);
            Utils.Render.drawTexture(gg, bg, pX + width + 1, pY + 9, 18, 189, 99, 4, 512, 512);
            Transform.create(gg.pose(), tr -> {
                tr.autoPose(()-> {
                    tr.scale(pX + 22, pY + 9, 0.63f, 0.64f);
                    Utils.Render.drawText(gg, AeterianLang.translate(getName(), isUnlocked()), pX + 17, pY + 4, 0xFF4DBE);
                });
                if (isUnlocked()) {
                    gg.renderItem(icon.asItem().getDefaultInstance(), pX + 1, pY + (difficulty == Difficulty.basic ? 0 : 1));
                } else {
                    Utils.Render.drawTexture(gg, bg, pX + 3, pY + (difficulty == Difficulty.basic ? 2 : 3), 280, 38, 12, 12, 512, 512);
                }
            });
        }
    }

    public boolean isHover(int pX, int pY, int mouseX, int mouseY){
        return isUnlocked() && mouseX <= pX + 117 && mouseX >= pX && mouseY <= pY + 18 && mouseY >= pY;
    }

    @Override
    public Research getParent() {
        return parent;
    }

    @Override
    public int getXOffset(int pX) {
        return 0;
    }

    @Override
    public int getYOffset(int pY) {
        return 0;
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

    public enum Difficulty {
        locked(0),
        basic(0),
        arcana(18),
        archaic(18),
        forbidden(18);

        public final int v;
        Difficulty(int v){
            this.v = v + 180;
        }

        public Component getTranslate(){
            return Component.translatable("difficulty." + name());
        }
    }
}
