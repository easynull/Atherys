package com.easynull.atherys.registers;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.easynull.atherys.Atherys.ID;

public final class AsEntities {
    private static final DeferredRegister<EntityType<?>> entities = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ID);

}
