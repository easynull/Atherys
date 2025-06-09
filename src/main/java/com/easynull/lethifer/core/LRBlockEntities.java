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

    public static Supplier<BlockEntityType<UmbriliteCrystalBE>> umbriliteCrystals = bes.register("umbrilite_crystal", ()-> new BlockEntityType<>(UmbriliteCrystalBE::new, umbriliteCrystal.get()));
    public static Supplier<BlockEntityType<ChargeObeliskBE>> chargeObelisks = bes.register("charge_obelisk", ()-> new BlockEntityType<>(ChargeObeliskBE::new, chargeObelisk.get()));
    public static Supplier<BlockEntityType<RunicSpringBE>> runicSprings = bes.register("runic_spring", ()-> new BlockEntityType<>(RunicSpringBE::new, runicSpring.get()));
}
