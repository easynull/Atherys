package com.easynull.atherys.core;

import com.easynull.atherys.api.AeterianLang;
import com.easynull.atherys.core.commands.ResearchCommands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = "atherys")
public final class ASEvents {
    private static final Map<ItemStack, List<Component>> lastTooltips = new HashMap<>();

    @SubscribeEvent
    private static void addCipherItem(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        boolean obfuscate = ASResearches.entries.stream().filter(entry -> !entry.isUnlocked()).flatMap(entry -> entry.items.stream()).anyMatch(item -> stack.getItem() == item);
        if (obfuscate) {
            if (!lastTooltips.containsKey(stack)) {
                lastTooltips.put(stack, event.getToolTip());
            }
            event.getToolTip().replaceAll(c -> Component.literal(AeterianLang.translate(c.getString())).withStyle(c.getStyle()));
        } else if (lastTooltips.containsKey(stack)) {
            event.getToolTip().clear();
            event.getToolTip().addAll(lastTooltips.get(stack));
            lastTooltips.remove(stack);
        }
    }

    @SubscribeEvent
    private static void registerCommands(RegisterCommandsEvent event){
        ResearchCommands.register(event.getDispatcher());
    }
}
