package com.easynull.lethifer.core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.easynull.lethifer.Lethifer.ID;

public final class LREntities {
    private static final DeferredRegister<EntityType<?>> entities = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ID);

}
