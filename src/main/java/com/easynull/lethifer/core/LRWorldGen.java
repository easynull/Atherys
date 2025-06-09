package com.easynull.lethifer.core;

import com.easynull.lethifer.core.worldgen.RandomResearchFunction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static com.easynull.lethifer.Lethifer.ID;

public class LRWorldGen {
    private static final DeferredRegister<LootItemFunctionType<?>> lootFunctions = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, ID);

    public static final DeferredHolder<LootItemFunctionType<?>, LootItemFunctionType<RandomResearchFunction>> randomResearch = lootFunctions.register("random_research", ()-> new LootItemFunctionType<>(RandomResearchFunction.codec));

    public static void register(IEventBus bus){
        lootFunctions.register(bus);
    }
}
