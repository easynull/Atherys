package com.easynull.lethifer.client.render;

import com.easynull.lethifer.Lethifer;
import com.easynull.lethifer.api.Research;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public final class PageScreen extends LRScreen {
    final ResourceLocation bg = Lethifer.locTo("gui/page");
    public String current = "";
    final Research entry;
    final ItemStack stack;

    public PageScreen(Research entry, ItemStack stack) {
        super(152, 278);
        this.entry = entry;
        this.stack = stack;
        mc.player.playSound(SoundEvents.BOOK_PAGE_TURN, 1f, 1f);
    }

    @Override
    protected void init() {
        addRenderableWidget(new TextField(guiLeft(), guiTop(), 280, 19, Component.empty(), text -> current = text));
    }

    @Override
    public void rendering(GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        gg.drawCenteredString(mc.font, entry.getCipher(), guiLeft() + 140, guiTop() - 50, 0x80000000);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (current.equals(Component.translatable("cipher." + entry.getName()).getString()) && keyCode == GLFW.GLFW_KEY_ENTER) {
            ResearchUtils.setState(mc.player, entry, true);
            stack.shrink(1);
            mc.player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
