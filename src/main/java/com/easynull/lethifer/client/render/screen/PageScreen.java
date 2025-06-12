package com.easynull.lethifer.client.render.screen;

import com.easynull.lethifer.Lethifer;
import com.easynull.lethifer.api.LetherianLang;
import com.easynull.lethifer.api.researches.Research;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public final class PageScreen extends LRScreen {
    public static final PageScreen instance = new PageScreen();
    final ResourceLocation bg = Lethifer.locTo("gui/page");
    public String current = "";
    Research entry;
    ItemStack stack;
    Player player;

    public PageScreen() {
        super(232, 196);
    }

    public void open(Research entry, ItemStack stack, Player player) {
        ticks = 0;
        this.entry = entry;
        this.stack = stack;
        this.player = player;
        mc.setScreen(instance);
    }

    @Override
    protected void init() {
        addRenderableWidget(new TextField(guiLeft(), guiTop(), 280, 19, Component.empty(), text -> current = text));
    }

    @Override
    public void rendering(GuiGraphics gg, int mouseX, int mouseY, float pTicks) {
        gg.drawCenteredString(mc.font, LetherianLang.translate(entry.getCipher()), guiLeft() + 110, guiTop() - 50, 0x80000000);
        gg.drawCenteredString(mc.font, entry.getCipher(), guiLeft() + 110, guiTop() - 25, 0x80000000);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (current.equals(entry.getCipher()) && keyCode == GLFW.GLFW_KEY_ENTER) {
            ResearchUtils.setState(player, entry, true);
            stack.shrink(1);
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
            LetherianLang.onRandomize();
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
