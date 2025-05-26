package com.easynull.lethifer.core;

import com.easynull.lethifer.core.worldgen.functions.ResearchRandomlyFunc;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.easynull.lethifer.Lethifer.ID;

public final class LRWorldGen {
    private static DeferredRegister<LootItemFunctionType<?>> biomes = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, ID);


    public static void register(IEventBus bus){
        biomes.register(bus);
    }
}
