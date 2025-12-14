package com.easynull.atherys.registers;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.easynull.atherys.Atherys.ID;

public final class AsComponents {
    public static final DeferredRegister.DataComponents components = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ID);

    public static Supplier<DataComponentType<Long>> essential = components.registerComponentType("are", builder -> builder.persistent(Codec.LONG).networkSynchronized(ByteBufCodecs.LONG));
    public static Supplier<DataComponentType<String>> research = components.registerComponentType("research", builder -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.fromCodec(Codec.STRING)));
}
