package com.easynull.lethifer.core;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = "lethifer")
public final class LREvents {

    @SubscribeEvent
    private static void onSaveData(PlayerEvent.Clone event){
        if(event.isWasDeath() && event.getOriginal().hasData(LRAttachments.researches)){
            event.getEntity().getData(LRAttachments.researches).setData(event.getOriginal().getData(LRAttachments.researches));
        }
    }
}
