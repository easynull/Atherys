package com.easynull.atherys.core;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.easynull.atherys.Atherys.ID;

public final class ASComponents {
    public static final DeferredRegister.DataComponents components = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ID);

    public static Supplier<DataComponentType<CompoundTag>> nbt = components.registerComponentType("nbt", builder -> builder.persistent(CompoundTag.CODEC).networkSynchronized(ByteBufCodecs.fromCodec(CompoundTag.CODEC)));
    public static Supplier<DataComponentType<Integer>> essential = components.registerComponentType("le", builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT));
    public static Supplier<DataComponentType<String>> research = components.registerComponentType("research", builder -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.fromCodec(Codec.STRING)));
}
