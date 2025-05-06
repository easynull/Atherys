package com.easynull.rebyssal.core;

import com.easynull.rebyssal.Rebyssal;
import com.easynull.rebyssal.client.render.book.Entries;
import com.easynull.rebyssal.client.render.book.Entry;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.easynull.rebyssal.Rebyssal.ID;

@Mod.EventBusSubscriber(modid = ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class Events {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void addCustomFont(RenderTooltipEvent.Pre event) {
        for (Entry entry : Entries.entries) {
            if (!entry.isUnlocked()) {
                for (Item stack : entry.items) {
                    if (event.getStack().isItemEqual(stack.getDefaultInstance())) event.setFontRenderer(Rebyssal.symbols);
                }
            }
        }
    }

//    @SubscribeEvent
//    public static void addCapability(AttachCapabilitiesEvent<Entity> event) {
//        if (event.getObject() instanceof PlayerEntity) {
//            event.addCapability(new ResourceLocation(ID, "entries"), new SetupCapability.EntriesCapProvider());
//        }
//    }
}
