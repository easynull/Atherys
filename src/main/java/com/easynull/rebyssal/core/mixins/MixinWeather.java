package com.easynull.rebyssal.core.mixins;

import com.easynull.rebyssal.core.Register;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class MixinWeather {

    @Shadow @Final
    public boolean isRemote;

    @Shadow public abstract BiomeManager getBiomeManager();

    @Inject(method = "isRainingAt", at = @At("HEAD"), cancellable = true)
    public void setWeather(BlockPos pos, CallbackInfoReturnable<Boolean> ctx) {
        Biome biome = getBiomeManager().getBiome(pos);
        if(isRemote && biome == Register.darklandsForest.get() || biome == Register.darklandsMountains.get()) {
            ctx.setReturnValue(true);
        }
    }
}
