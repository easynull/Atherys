package com.easynull.rebyssal.client.sounds;

import com.easynull.rebyssal.core.Register;
import com.easynull.rebyssal.core.world.DimensionsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.easynull.rebyssal.Rebyssal.ID;

@Mod.EventBusSubscriber(modid = ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class BackgroundMusicEvent {
    private static Minecraft mc = Minecraft.getInstance();
    private static BackgroundMusic musicTicker = new BackgroundMusic(mc);;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        TickEvent.Phase phase = event.phase;
        TickEvent.Type type = event.type;
        if (phase == TickEvent.Phase.END && type.equals(TickEvent.Type.CLIENT) && !mc.isGamePaused()) {
            musicTicker.tick();
        }
    }

    @SubscribeEvent
    public static void onMusicControl(PlaySoundEvent event) {
        ISound sound = event.getSound();
        SoundCategory category = sound.getCategory();
        for (DimensionsManager.Data dim : DimensionsManager.dims) {
            if (category == SoundCategory.MUSIC && mc.player != null && (mc.player.world.getDimensionKey() == dim.reg || mc.player.world.getBiome(mc.player.getPosition()) == Register.darklandsForest.get()) && !sound.getSoundLocation().toString().contains("rebyssal")) {
                event.setResultSound(null);
            }
        }
    }
}
