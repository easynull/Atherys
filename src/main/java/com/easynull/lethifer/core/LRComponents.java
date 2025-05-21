package com.easynull.lethifer.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.easynull.lethifer.Lethifer.ID;

public final class LRComponents {
    public static final DeferredRegister.DataComponents components = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ID);

    public static Supplier<DataComponentType<NBTComponent>> nbt = components.registerComponentType("nbt", builder -> builder.persistent(NBTComponent.codec).networkSynchronized(ByteBufCodecs.fromCodec(NBTComponent.codec)));
    public static Supplier<DataComponentType<Integer>> energy = components.registerComponentType("ne", builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT));
    public static Supplier<DataComponentType<String>> research = components.registerComponentType("research", builder -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.fromCodec(Codec.STRING)));

    public record NBTComponent(CompoundTag nbt){
        public static final Codec<NBTComponent> codec = RecordCodecBuilder.create(instance -> instance.group(CompoundTag.CODEC.fieldOf("nbt").forGetter(NBTComponent::nbt)).apply(instance, NBTComponent::new));
    }
}
