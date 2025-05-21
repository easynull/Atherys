package com.easynull.lethifer.core;

import com.easynull.lethifer.client.render.book.Entry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = "lethifer")
public final class LREvents {
//    @SubscribeEvent
//    private void addCipher(RenderTooltipEvent.Pre event){
//        for (Entry entry : Entries.entries) {
//            if (!entry.isUnlocked()) {
//                for (Item stack : entry.items) {
//                    if (event.getItemStack().equals(stack.getDefaultInstance())){
//                        String name = event.getComponents().getFirst().toString();
//                        String cipheredName = "-".repeat(name.length());
//                        event.getComponents().set(0, (ClientTooltipComponent) Component.literal(cipheredName));
//                    }
//                }
//            }
//        }
//    }

    @SubscribeEvent
    private static void onSaveData(PlayerEvent.Clone event){
        if(event.isWasDeath() && event.getOriginal().hasData(LRAttachments.researches)){
            event.getEntity().getData(LRAttachments.researches).setData(event.getOriginal().getData(LRAttachments.researches));
        }
    }
}
