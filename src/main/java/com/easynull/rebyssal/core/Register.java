package com.easynull.rebyssal.core;

import com.easynull.rebyssal.core.blocks.RBWoodType;
import com.easynull.rebyssal.core.world.BiomesConfig;
import com.easynull.rebyssal.core.items.*;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.function.Supplier;

import static com.easynull.rebyssal.Rebyssal.ID;

public final class Register {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ID);
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ID);
    public static final DeferredRegister<ContainerType<?>> GUIS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ID);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ID);
    public static final DeferredRegister<SurfaceBuilder<?>> SB = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, ID);
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, ID);
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, ID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ID);

    public static RegistryObject<Item> book, glassTank;
    public static RegistryObject<Block> darkLog, sDarkLog, darkPlanks, darkRock;
    public static RegistryObject<SoundEvent> etheriaBG, velandiaBG, winterhillsBG, multiportalOpen;
    public static RegistryObject<Biome> darklandsForest, darklandsMountains;

    public static void register(IEventBus bus) {
        book = ITEMS.register("book", ItemBook::new);
        glassTank = ITEMS.register("glass_tank", ()-> new ItemGlassTank(1000));
        darkLog = regBlock("dark_log", ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)), Tabs.main);
        sDarkLog = regBlock("stripped_dark_log", ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)), Tabs.main);
        darkPlanks = regBlock("dark_planks", ()-> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)), Tabs.main);
        darkRock = regBlock("dark_rock", ()-> new Block(AbstractBlock.Properties.from(Blocks.STONE)), Tabs.main);

        etheriaBG = SOUNDS.register("etheria_bg", ()-> new SoundEvent(new ResourceLocation(ID, "etheria.bg")));
        velandiaBG = SOUNDS.register("velandia_bg", ()-> new SoundEvent(new ResourceLocation(ID, "velandia.bg")));
        winterhillsBG = SOUNDS.register("winterhills_bg", ()-> new SoundEvent(new ResourceLocation(ID, "winterhills.bg")));
        multiportalOpen = SOUNDS.register("multiportal_open", ()-> new SoundEvent(new ResourceLocation(ID, "multiportal.open")));

        darklandsForest = BIOMES.register("darklands_forest", BiomesConfig::configDarklandsForest);
        darklandsMountains = BIOMES.register("darklands_mountains", BiomesConfig::configDarklandsMountains);

        BLOCKS.register(bus);
        ITEMS.register(bus);
        TILES.register(bus);
        GUIS.register(bus);
        ENTITIES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
        BIOMES.register(bus);
        SB.register(bus);
        STRUCTURES.register(bus);
        SOUNDS.register(bus);
    }

    public static void regWoodTypes(){
        RBWoodType.register(RBWoodType.dark);
        AxeItem.BLOCK_STRIPPING_MAP.put(darkLog.get(), sDarkLog.get());
    }

    public static <T extends Block> RegistryObject<T> regBlock(String name, Supplier<T> block, ItemGroup tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(toReturn.get(), tab != null ? new Item.Properties().group(tab) : new Item.Properties()));
        return toReturn;
    }
}
