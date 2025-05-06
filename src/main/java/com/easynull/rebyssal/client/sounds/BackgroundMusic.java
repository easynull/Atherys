package com.easynull.rebyssal.client.sounds;

import com.easynull.rebyssal.core.world.DimensionsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public final class BackgroundMusic implements ITickable {
    public ISound music, bossfight;
    final Minecraft mc;
    public int timeNextMusic = 100;

    public BackgroundMusic(Minecraft mc) {
        this.mc = mc;
    }

    @Override
    public void tick() {
        ClientPlayerEntity player = mc.player;
        if (player != null) {
            RegistryKey<World> dim = player.world.getDimensionKey();
            for(DimensionsManager.Data d : DimensionsManager.dims) {
                if (d.reg == dim && d.track != null) {
                    if (this.music != null && this.music.getVolume() <= 0.0F) {
                        this.music = null;
                    }
                    if (this.music != null && !this.mc.getSoundHandler().isPlaying(this.music)) {
                        this.music = null;
                        this.timeNextMusic = Math.min(MathHelper.nextInt(player.world.rand, d.track.minDelay(), d.track.maxDelay()), this.timeNextMusic);
                    }
                    this.timeNextMusic = Math.min(this.timeNextMusic, d.track.maxDelay());
                    if (this.music != null && this.music.getVolume() < 1.0F || this.music == null && this.timeNextMusic-- <= 0) {
                        playMusic(d.track.sound());
                    }
                    if (this.music != null) System.out.println(music.getSound());
                }
            }
//            for(DimensionsManager.Data s : DimensionsManager.strs){
//                if (s.structure == str && s.track != null) {
//                    if (this.music != null && this.music.getVolume() <= 0.0F) {
//                        this.music = null;
//                    }
//                    if (this.bossfight != null && !this.mc.getSoundHandler().isPlaying(this.bossfight)) {
//                        this.bossfight = null;
//                        this.timeNextMusic = Math.min(MathHelper.nextInt(player.world.rand, s.track.minDelay(), s.track.maxDelay()), this.timeNextMusic);
//                    }
//                    this.timeNextMusic = Math.min(this.timeNextMusic, s.track.maxDelay());
//                    if (this.music == null && (this.bossfight != null && this.bossfight.getVolume() < 1.0F || this.bossfight == null && this.timeNextMusic-- <= 0)) {
//                        playBossfight(s.track.sound());
//                    }
//                }
//            }
        } else {
            stopMusic();
        }
    }

    public boolean playingMusic() {
        return this.music != null;
    }

    public void playMusic(SoundEvent sound) {
        this.music = new MusicAmbient(sound, SoundCategory.MUSIC, false);
        ((MusicAmbient) music).setVolume(1.0f);
        ((MusicAmbient) music).setTicks((short) 40);
        mc.getSoundHandler().play(music);
        this.timeNextMusic = Integer.MAX_VALUE;
    }

    public void playBossfight(SoundEvent sound) {
        this.bossfight = new MusicAmbient(sound, SoundCategory.MUSIC, false);
        ((MusicAmbient) bossfight).setVolume(1f);
        ((MusicAmbient) bossfight).setTicks((short) 40);
        mc.getSoundHandler().play(bossfight);
        this.timeNextMusic = Integer.MAX_VALUE;
    }

    public void stopMusic() {
        if (this.music != null) {
            ((MusicAmbient)this.music).shouldNotFade = false;
            this.timeNextMusic = 0;
        }
        if (this.bossfight != null) {
            ((MusicAmbient)this.bossfight).shouldNotFade = false;
            this.timeNextMusic = 0;
        }
    }
}
