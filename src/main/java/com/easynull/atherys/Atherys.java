package com.easynull.atherys;

import com.easynull.atherys.api.AeterianLang;
import com.easynull.atherys.core.ASResearches;
import com.easynull.atherys.core.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Atherys.ID)
public final class Atherys {
    public static final String ID = "atherys";

    public Atherys(IEventBus bus) {
        ASComponents.components.register(bus);
        ASItemsBlocks.register(bus);
        ASBlockEntities.bes.register(bus);
        ASWorldGen.register(bus);
        ASAttachments.attachments.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::client);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ASResearches.setupBook();
    }

    private void client(final FMLClientSetupEvent event){
        event.enqueueWork(AeterianLang::onRandomize);
    }

    public static ResourceLocation locTo(String file){
        return ResourceLocation.fromNamespaceAndPath(ID, String.format("textures/%s.png", file));
    }
}
