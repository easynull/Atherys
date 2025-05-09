package com.easynull.reabyssal.core;

import com.easynull.reabyssal.core.items.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.easynull.reabyssal.ReAbyssal.ID;

public final class Loader {
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(BuiltInRegistries.BLOCK, ID);
    public static final DeferredRegister.Items items = DeferredRegister.createItems(ID);
    public static final DeferredRegister<BlockEntityType<?>> be = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ID);

    public static DeferredItem<Item> book;

    public static void register(IEventBus bus){
        book = items.register("book", (r) -> new ItemBook(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, r))));
        items.register(bus);
        blocks.register(bus);
        be.register(bus);
    }
}
