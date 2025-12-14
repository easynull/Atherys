package com.easynull.atherys;

import com.easynull.atherys.core.AeterianLang;
import com.easynull.atherys.registers.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Atherys.ID)
public final class Atherys {
    public static final String ID = "atherys";

    public Atherys(IEventBus bus) {
        AsComponents.components.register(bus);
        AsElements.register(bus);
        AsBlockEntities.bes.register(bus);
        AsWorldGen.register(bus);
        AsAttachments.attachments.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::client);
    }

    private void setup(final FMLCommonSetupEvent event) {
        AsResearches.setupBook();
    }

    private void client(final FMLClientSetupEvent event){
        event.enqueueWork(AeterianLang::onRandomize);
    }

    public static ResourceLocation path(String file){
        return ResourceLocation.fromNamespaceAndPath(ID, String.format("textures/%s.png", file));
    }
}
