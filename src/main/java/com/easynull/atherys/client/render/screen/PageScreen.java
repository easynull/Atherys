package com.easynull.atherys.client.render.screen;

import com.easynull.atherys.Atherys;
import com.easynull.atherys.core.AeterianLang;
import com.easynull.atherys.core.researches.Research;
import com.easynull.atherys.utils.ResearchUtils;
import com.mw.nullcore.client.screen.NullScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public final class PageScreen extends NullScreen {
    public static final PageScreen instance = new PageScreen();
    final EditBox box;
    final ResourceLocation bg = Atherys.path("gui/page");
    Research entry;
    ItemStack stack;
    Player player;

    public PageScreen() {
        super(232, 196, 0,1);
        box = new EditBox(font, guiLeft(), guiTop(), 280, 19, Component.empty());
    }

    public void open(Research entry, ItemStack stack, Player player) {
        ticks = 0;
        this.entry = entry;
        this.stack = stack;
        this.player = player;
        mc().setScreen(instance);
    }

    @Override
    protected void init() {
        addRenderableWidget(box);
    }

    @Override
    protected void draw(GuiGraphics gg, int i, int i1, float v) {
        gg.drawCenteredString(mc().font, AeterianLang.translate(entry.getCipher()), guiLeft() + 110, guiTop() - 50, 0x80000000);
        gg.drawCenteredString(mc().font, entry.getCipher(), guiLeft() + 110, guiTop() - 25, 0x80000000);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (box.getValue().equals(entry.getCipher()) && keyCode == GLFW.GLFW_KEY_ENTER) {
            ResearchUtils.setState(player, entry, true);
            stack.shrink(1);
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
            AeterianLang.onRandomize();
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
