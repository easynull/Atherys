package com.easynull.rebyssal;

import com.easynull.rebyssal.client.render.book.Entries;
import com.easynull.rebyssal.core.Register;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.fonts.Font;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Rebyssal.ID)
public final class Rebyssal {
    public static final String ID = "rebyssal";
    public static FontRenderer symbols;

    public Rebyssal() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Register.register(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::client);
        eventBus.addListener(this::enqueueIMC);
        eventBus.addListener(this::processIMC);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()->{
            Register.regWoodTypes();
        });
        symbols = new FontRenderer((r) -> new Font(Minecraft.getInstance().textureManager, new ResourceLocation(ID, "textures/font/symbols")));
    }

    private void client(final FMLClientSetupEvent event) {
        Entries.setupBook();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    public static ResourceLocation locTo(String file){
        return new ResourceLocation(ID, String.format("textures/%s.png", file));
    }

    public static TranslationTextComponent translation(String type, String id){
        return new TranslationTextComponent(String.format("%s.rebyssal.%s", type, id));
    }
}
