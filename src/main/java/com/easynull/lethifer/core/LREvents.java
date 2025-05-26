package com.easynull.lethifer.core;

import com.easynull.lethifer.api.LetherianLang;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = "lethifer")
public final class LREvents {
    private static final Map<ItemStack, List<Component>> lastTooltips = new HashMap<>();

    @SubscribeEvent
    private static void onSaveData(PlayerEvent.Clone event){
        if(event.isWasDeath() && event.getOriginal().hasData(LRAttachments.researches)){
            event.getEntity().getData(LRAttachments.researches).setData(event.getOriginal().getData(LRAttachments.researches));
        }
    }

    @SubscribeEvent
    private static void addCipherItem(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        boolean obfuscate = LRResearches.entries.stream().filter(entry -> !entry.isUnlocked()).flatMap(entry -> entry.items.stream()).anyMatch(item -> stack.getItem() == item);
        if (obfuscate) {
            if (!lastTooltips.containsKey(stack)) {
                lastTooltips.put(stack, new ArrayList<>(event.getToolTip()));
            }
            event.getToolTip().replaceAll(c -> Component.literal(LetherianLang.translate(c.getString())).withStyle(c.getStyle()));
        } else if (lastTooltips.containsKey(stack)) {
            event.getToolTip().clear();
            event.getToolTip().addAll(lastTooltips.get(stack));
            lastTooltips.remove(stack);
        }
    }
}
