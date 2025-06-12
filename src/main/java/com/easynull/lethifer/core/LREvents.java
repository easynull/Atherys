package com.easynull.lethifer.core;

import com.easynull.lethifer.api.LetherianLang;
import com.easynull.lethifer.core.commands.ResearchCommands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = "lethifer")
public final class LREvents {
    private static final Map<ItemStack, List<Component>> lastTooltips = new HashMap<>();

    @SubscribeEvent
    private static void addCipherItem(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        boolean obfuscate = LRResearches.entries.stream().filter(entry -> !entry.isUnlocked()).flatMap(entry -> entry.items.stream()).anyMatch(item -> stack.getItem() == item);
        if (obfuscate) {
            if (!lastTooltips.containsKey(stack)) {
                lastTooltips.put(stack, event.getToolTip());
            }
            event.getToolTip().replaceAll(c -> Component.literal(LetherianLang.translate(c.getString())).withStyle(c.getStyle()));
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
