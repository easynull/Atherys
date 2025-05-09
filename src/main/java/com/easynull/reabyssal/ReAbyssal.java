package com.easynull.reabyssal;

import com.easynull.reabyssal.client.render.book.Entries;
import com.easynull.reabyssal.core.Loader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(ReAbyssal.ID)
public final class ReAbyssal {
    public static final String ID = "reabyssal";
    public static Font symbols;

    public ReAbyssal(IEventBus bus) {
        Loader.register(bus);
        bus.addListener(this::setup);
        bus.addListener(this::client);
    }

    private void setup(final FMLCommonSetupEvent event) {
        symbols = new Font(r -> new FontSet(Minecraft.getInstance().getTextureManager(), locTo("font/symbols")), false);
        event.enqueueWork(()-> Entries.setupBook());
    }

    private void client(final FMLClientSetupEvent event){}

    public static ResourceLocation locTo(String file){
        return ResourceLocation.fromNamespaceAndPath(ID, String.format("textures/%s.png", file));
    }

    public static Component translation(String type, String id){
        return Component.translatable(String.format("%s.rebyssal.%s", type, id));
    }
}
