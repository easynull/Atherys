package com.easynull.lethifer;

import com.easynull.lethifer.client.render.book.Entry;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.core.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(Lethifer.ID)
public final class Lethifer {
    public static final String ID = "lethifer";

    public Lethifer(IEventBus bus) {
        LRComponents.components.register(bus);
        LRItemsBlocks.register(bus);
        LRBlockEntities.bes.register(bus);
        LRAttachments.attachments.register(bus);
        LRWorldGen.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::client);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()-> LRResearches.setupBook());
    }

    private void client(final FMLClientSetupEvent event){}

    public static ResourceLocation locTo(String file){
        return ResourceLocation.fromNamespaceAndPath(ID, String.format("textures/%s.png", file));
    }
}
