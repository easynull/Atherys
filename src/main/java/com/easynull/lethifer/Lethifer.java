package com.easynull.lethifer;

import com.easynull.lethifer.api.LetherianLang;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.core.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Lethifer.ID)
public final class Lethifer {
    public static final String ID = "lethifer";

    public Lethifer(IEventBus bus) {
        LRComponents.components.register(bus);
        LRItemsBlocks.register(bus);
        LRBlockEntities.bes.register(bus);
        LRWorldGen.register(bus);
        LRAttachments.attachments.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::client);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LRResearches.setupBook();
    }

    private void client(final FMLClientSetupEvent event){
        event.enqueueWork(LetherianLang::onRandomize);
    }

    public static ResourceLocation locTo(String file){
        return ResourceLocation.fromNamespaceAndPath(ID, String.format("textures/%s.png", file));
    }
}
