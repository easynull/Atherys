package com.easynull.atherys.api.researches;

import com.easynull.atherys.client.render.screen.book.Chapter;
import com.easynull.atherys.client.render.screen.book.Entry;
import com.easynull.atherys.core.ASResearches;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("unchecked")
public abstract class Research<O extends Research<O>> {
    final Minecraft mc = Minecraft.getInstance();
    final String id, cipher;
    public final int width, height;
    public final ItemLike icon;
    public boolean primal, unlocked;

    protected Research(String id, ItemLike icon, int width, int height){
        this.id = id;
        this.cipher = String.format("%s.cipher.%s", this.getClass().getSimpleName().toLowerCase(), id);
        this.icon = icon;
        this.width = width;
        this.height = height;
        add((O) this);
    }

    public abstract void onDraw(ResourceLocation bg, GuiGraphics gg, int pX, int pY, int mouseX, int mouseY);

    public boolean isHover(int pX, int pY, int mouseX, int mouseY){
        return isUnlocked() && mouseX >= getXOffset(pX) && mouseX <= getXOffset(pX) + width && mouseY >= getYOffset(pY) && mouseY <= getYOffset(pY) + height;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return Component.translatable(String.format("%s.%s", this.getClass().getSimpleName().toLowerCase(), id)).getString();
    }

    public String getCipher() {
        return Component.translatable(cipher).getString();
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setState(boolean unlock) {
        unlocked = unlock;
    }

    public O primal() {
        this.primal = true;
        return (O) this;
    }

    public Research getParent() {
        return null;
    }

    public abstract int getXOffset(int pX);
    public abstract int getYOffset(int pY);

    private void add(O research) {
        if(research instanceof Chapter c) ASResearches.chapters.add(c);
        if(research instanceof Entry e) ASResearches.entries.add(e);
        ASResearches.researches.put(getID(), research);
    }
}
