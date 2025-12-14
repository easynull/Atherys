package com.easynull.atherys.registers;

import com.easynull.atherys.registers.blocks.ChargeObeliskBlock;
import com.easynull.atherys.registers.blocks.RunicSpringBlock;
import com.easynull.atherys.registers.blocks.UmbriliteCrystalBlock;
import com.easynull.atherys.registers.items.*;
import com.mw.nullcore.core.holders.OuterBlock;
import com.mw.nullcore.core.holders.OuterCreativeTab;
import com.mw.nullcore.core.holders.OuterItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.easynull.atherys.Atherys.ID;

public final class AsElements {
    private static final OuterItem items = OuterItem.create(ID);
    private static final OuterBlock blocks = OuterBlock.create(ID, items);
    private static final OuterCreativeTab creativeTabs = OuterCreativeTab.create(ID);

    public static final DeferredItem<Item> astralon = items.registerItem("astralon", prop -> new AstralonItem(prop.stacksTo(1).rarity(Rarity.EPIC), 3500));
    public static final DeferredBlock<Block> teneviaLog = blocks.registerBlock("tenevia_log", prop -> new RotatedPillarBlock(prop.ignitedByLava()), Blocks.BAMBOO_BLOCK);
    public static final DeferredBlock<Block> strTeneviaLog = blocks.registerBlock("stripped_tenevia_log", prop -> new RotatedPillarBlock(prop.ignitedByLava()), Blocks.STRIPPED_BAMBOO_BLOCK);
    public static final DeferredBlock<Block> teneviaPlanks = blocks.registerBlock("tenevia_planks", prop -> new Block(prop.ignitedByLava()), Blocks.BAMBOO_PLANKS);
    public static final DeferredBlock<Block> teneviaStairs = blocks.registerBlock("tenevia_stairs", prop -> new StairBlock(teneviaPlanks.get().defaultBlockState(), prop.ignitedByLava()), Blocks.BAMBOO_STAIRS);
    public static final DeferredBlock<Block> teneviaSlab = blocks.registerBlock("tenevia_slab", prop -> new Block(prop.ignitedByLava()), Blocks.BAMBOO_SLAB);
    public static final DeferredBlock<Block> umbriliteOre = blocks.registerBlock("umbrilite_ore", Block::new, Blocks.IRON_ORE);
    public static final DeferredBlock<Block> umbriliteCrystal = blocks.registerBlock("umbrilite_crystal", prop -> new UmbriliteCrystalBlock(prop.sound(SoundType.AMETHYST_CLUSTER)), Blocks.AMETHYST_BLOCK);
    public static final DeferredItem<Item> umbriliteDust = items.registerItem("umbrilite_dust", Item::new);
    public static final DeferredBlock<Block> chargeObelisk = blocks.registerBlock("charge_obelisk", prop -> new ChargeObeliskBlock(prop, 3000), Blocks.BLACKSTONE);
    public static final DeferredItem<Item> ancientPage = items.registerItem("ancient_page", prop -> new PageItem(prop.component(AsComponents.research, "empty").rarity(Rarity.RARE)));
    public static final DeferredItem<Item> linguisterium = items.registerItem("linguisterium", prop -> new LinguisteriumItem(prop.stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredBlock<Block> runicSpring = blocks.registerBlock("runic_spring", RunicSpringBlock::new, Blocks.RAW_IRON_BLOCK);

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> main = creativeTabs.registerTab("main", Component.translatable("tab.main"), astralon::toStack, astralon, linguisterium, umbriliteDust, umbriliteCrystal, chargeObelisk, ancientPage, runicSpring);
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> deco = creativeTabs.registerTab("deco", Component.translatable("tab.deco"), teneviaLog::toStack, teneviaLog, umbriliteOre);

    public static void register(IEventBus bus){
        blocks.register(bus);
        items.register(bus);
        creativeTabs.register(bus);
    }
}
