package com.easynull.rebyssal.client.sounds;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class MusicAmbient extends TickableSound {
    public int ticksPlayed;
    public boolean shouldNotFade = true;

    public MusicAmbient(SoundEvent soundIn, SoundCategory categoryIn, boolean repeat) {
        super(soundIn, categoryIn);
        this.repeat = repeat;
        if (repeat) {
            this.repeatDelay = 0;
        }
    }

    @Override
    public void tick() {
        if (this.ticksPlayed >= 0) {
            if (this.shouldNotFade) {
                ++this.ticksPlayed;
            } else {
                this.ticksPlayed -= 2;
            }
            this.setTicks((short) Math.min(this.ticksPlayed, 40));
            this.volume = Math.max(0.0F, Math.min((float)this.ticksPlayed / 40.0F, 1.0F));
        } else {
            this.finishPlaying();
        }
    }

    public void setVolume(float v){
        this.volume = v;
    }

    public void setTicks(short t){
        this.ticksPlayed = t;
    }
}
