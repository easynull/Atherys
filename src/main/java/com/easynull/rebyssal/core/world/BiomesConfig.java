package com.easynull.rebyssal.core.world;

import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public final class BiomesConfig {
    private static BiomeAmbience darklandsEffect(){
        return new BiomeAmbience.Builder().setFogColor(56269).setWaterColor(14745518).setWaterFogColor(329011).withSkyColor(0).withFoliageColor(0x17375c).withGrassColor(0x17375c).setParticle(new ParticleEffectAmbience(ParticleTypes.MYCELIUM, 0.15f)).build();
    }

    public static Biome configDarklandsForest(){
        BiomeGenerationSettings.Builder biomeGen = new BiomeGenerationSettings.Builder().withSurfaceBuilder(SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.WATER.getDefaultState())));
        DefaultBiomeFeatures.withCavesAndCanyons(biomeGen);
        DefaultBiomeFeatures.withForestGrass(biomeGen);
        DefaultBiomeFeatures.withBadlandsGrass(biomeGen);
        MobSpawnInfo.Builder mobs = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();
        return new Biome.Builder().precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.2f).scale(0.02f).temperature(1.6f).downfall(0f).setEffects(darklandsEffect()).withMobSpawnSettings(mobs.copy()).withGenerationSettings(biomeGen.build()).build();
    }

    public static Biome configDarklandsMountains(){
        BiomeGenerationSettings.Builder biomeGen = new BiomeGenerationSettings.Builder().withSurfaceBuilder(SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.WATER.getDefaultState())));
        DefaultBiomeFeatures.withCavesAndCanyons(biomeGen);
        DefaultBiomeFeatures.withForestGrass(biomeGen);
        DefaultBiomeFeatures.withBadlandsGrass(biomeGen);
        MobSpawnInfo.Builder mobs = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();
        return new Biome.Builder().precipitation(Biome.RainType.RAIN).category(Biome.Category.EXTREME_HILLS).depth(10f).scale(0.007f).temperature(-1f).downfall(1f).setEffects(darklandsEffect()).withMobSpawnSettings(mobs.copy()).withGenerationSettings(biomeGen.build()).build();
    }
}
