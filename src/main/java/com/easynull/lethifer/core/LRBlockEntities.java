package com.easynull.lethifer.core;

import com.easynull.lethifer.core.blocks.entities.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.easynull.lethifer.Lethifer.ID;
import static com.easynull.lethifer.core.LRItemsBlocks.*;

public final class LRBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> bes = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ID);

    public static Supplier<BlockEntityType<ChargeObelisk>> chargeObelisks = bes.register("charge_obelisk", ()-> new BlockEntityType<>(ChargeObelisk::new, chargeObelisk.get()));
}
