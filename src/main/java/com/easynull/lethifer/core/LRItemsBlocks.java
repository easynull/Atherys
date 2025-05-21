package com.easynull.lethifer.core;

import com.easynull.lethifer.core.blocks.ChargeObelisk;
import com.easynull.lethifer.core.items.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

import static com.easynull.lethifer.Lethifer.ID;

public final class LRItemsBlocks {
    private static final DeferredRegister.Blocks blocks = DeferredRegister.createBlocks(ID);
    private static final DeferredRegister.Items items = DeferredRegister.createItems(ID);
    private static final DeferredRegister<CreativeModeTab> creativeTabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ID);

    public static final DeferredItem<Item> leimoire = registerItem("leimoire", prop -> new Book(prop.stacksTo(1), 3500));
    public static final DeferredBlock<Block> teneviaLog = registerBlock("tenevia_log", prop -> new RotatedPillarBlock(prop.ignitedByLava()), Blocks.BAMBOO_BLOCK);
    public static final DeferredBlock<Block> strTeneviaLog = registerBlock("stripped_tenevia_log", prop -> new RotatedPillarBlock(prop.ignitedByLava()), Blocks.STRIPPED_BAMBOO_BLOCK);
    public static final DeferredBlock<Block> teneviaPlanks = registerBlock("tenevia_planks", prop -> new Block(prop.ignitedByLava()), Blocks.BAMBOO_PLANKS);
    public static final DeferredBlock<Block> teneviaStairs = registerBlock("tenevia_stairs", prop -> new StairBlock(teneviaPlanks.get().defaultBlockState(), prop.ignitedByLava()), Blocks.BAMBOO_STAIRS);
    public static final DeferredBlock<Block> teneviaSlab = registerBlock("tenevia_slab", prop -> new Block(prop.ignitedByLava()), Blocks.BAMBOO_SLAB);
    public static final DeferredBlock<Block> chargeObelisk = registerBlock("charge_obelisk", prop -> new ChargeObelisk(prop, 5000), Blocks.BLACKSTONE);
    public static final DeferredItem<Item> secPage = registerItem("secret_page", prop -> new SecretPage(prop.stacksTo(1).component(LRComponents.research, "nature")));
    public static final DeferredItem<Item> linguisterium = registerItem("linguisterium", prop -> new Linguisterium(prop.stacksTo(1)));

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> factory, Block block) {
        DeferredBlock<T> toReturn = blocks.register(name, () -> factory.apply((block != null ? BlockBehaviour.Properties.ofFullCopy(block) : BlockBehaviour.Properties.of()).setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(ID, name)))));
        registerItem(name, prop -> new BlockItem(toReturn.get(), prop));
        return toReturn;
    }

    public static <T extends Item> DeferredItem<T> registerItem(String name, Function<Item.Properties, T> factory) {
        return items.register(name, () -> factory.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(ID, name)))));
    }

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> main = creativeTabs.register("main", () -> CreativeModeTab.builder().title(Component.translatable("tab.main")).icon(leimoire::toStack)
            .displayItems((parameters, o) -> {
                o.accept(leimoire);
                o.accept(linguisterium);
                o.accept(chargeObelisk);
                o.accept(secPage);
            }).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> deco = creativeTabs.register("deco", () -> CreativeModeTab.builder().title(Component.translatable("tab.deco")).icon(teneviaPlanks::toStack).withSearchBar()
            .displayItems((parameters, o) -> {
                o.accept(teneviaLog);
                o.accept(strTeneviaLog);
                o.accept(teneviaPlanks);
                o.accept(teneviaStairs);
                o.accept(teneviaSlab);
            }).build());

    public static void register(IEventBus bus){
        blocks.register(bus);
        items.register(bus);
        creativeTabs.register(bus);
    }
}
