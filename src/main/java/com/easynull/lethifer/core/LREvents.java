package com.easynull.lethifer.core;

import com.easynull.lethifer.api.LetherianLang;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = "lethifer")
public final class LREvents {

    @SubscribeEvent
    private static void onSaveData(PlayerEvent.Clone event){
        if(event.isWasDeath() && event.getOriginal().hasData(LRAttachments.researches)){
            event.getEntity().getData(LRAttachments.researches).setData(event.getOriginal().getData(LRAttachments.researches));
        }
    }

    @SubscribeEvent
    private static void addCipherItem(RenderTooltipEvent.Pre event){
        event.getItemStack().set(DataComponents.CUSTOM_NAME, Component.empty().append(LetherianLang.translate(event.getItemStack().getItemName(), false)));
    }
}
