package com.easynull.lethifer.client.render.screen;

import com.easynull.lethifer.Lethifer;
import com.easynull.lethifer.api.LangLetherian;
import com.easynull.lethifer.api.Research;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public final class PageScreen extends LRScreen {
    public static final PageScreen instance = new PageScreen();
    final ResourceLocation bg = Lethifer.locTo("gui/page");
    public String current = "";
    Research entry;
    ItemStack stack;

    public PageScreen() {
        super(232, 196);
    }

    @Override
    protected void init() {
        addRenderableWidget(new TextField(guiLeft(), guiTop(), 280, 19, Component.empty(), text -> current = text));
    }

    public void open(Research entry, ItemStack stack) {
        ticks = 0;
        this.entry = entry;
        this.stack = stack;
        mc.setScreen(instance);
    }

    @Override
    public void rendering(GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        gg.drawCenteredString(mc.font, LangLetherian.translate(entry.getCipher()), guiLeft() + 110, guiTop() - 50, 0x80000000);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (current.equals(entry.getCipher()) && keyCode == GLFW.GLFW_KEY_ENTER) {
            ResearchUtils.setState(mc.player, entry, true);
            stack.shrink(1);
            mc.player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
