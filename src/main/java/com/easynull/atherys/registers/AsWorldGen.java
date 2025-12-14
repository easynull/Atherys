package com.easynull.atherys.registers;

import com.easynull.atherys.registers.worldgen.RandomResearchFunction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.easynull.atherys.Atherys.ID;

public final class AsWorldGen {
    private static final DeferredRegister<LootItemFunctionType<?>> lootFunctions = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, ID);

    public static final DeferredHolder<LootItemFunctionType<?>, LootItemFunctionType<RandomResearchFunction>> randomResearch = lootFunctions.register("random_research", ()-> new LootItemFunctionType<>(RandomResearchFunction.codec));

    public static void register(IEventBus bus){
        lootFunctions.register(bus);
    }
}
