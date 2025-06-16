package com.easynull.atherys.client.render.screen;

import com.easynull.atherys.utils.RenderUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import java.util.function.Consumer;

public final class TextField extends AbstractWidget {
    private final Consumer<String> current;
    private final Font font = RenderUtils.mc.font;
    private String value = "";
    private int cursorPos, cursorTick;

    public TextField(int x, int y, int width, int height, Component message, Consumer<String> current) {
        super(x, y, width, height, message);
        this.current = current;
    }

    @Override
    protected void renderWidget(GuiGraphics gg, int mouseX, int mouseY, float pTick) {
        String visibleText = font.plainSubstrByWidth(value, width - 8);
        gg.drawString(font, visibleText, getX() + 4, getY() + (height - 8) / 2, 0xFFFFFF, false);

        if (cursorTick / 14 % 2 == 0) {
            int cursorX = getX() + 4 + font.width(value.substring(0, cursorPos));
            gg.fill(cursorX, getY() + 3, cursorX + 1, getY() + height - 3, 0xFFE0E0E0);
        }
        cursorTick++;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {}

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return switch (keyCode) {
            case GLFW.GLFW_KEY_BACKSPACE -> {
                if (cursorPos > 0) {
                    value = new StringBuilder(value).deleteCharAt(cursorPos - 1).toString();
                    cursorPos--;
                    current.accept(value);
                }
                yield true;
            }
            case GLFW.GLFW_KEY_LEFT -> {
                if (cursorPos > 0) cursorPos--;
                yield true;
            }
            case GLFW.GLFW_KEY_RIGHT -> {
                if (cursorPos < value.length()) cursorPos++;
                yield true;
            }
            default -> false;
        };
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        value = new StringBuilder(value).insert(cursorPos, codePoint).toString();
        cursorPos++;
        current.accept(value);
        return true;
    }
}
