package com.easynull.lethifer.core.worldgen.functions;

import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.core.LRWorldGen;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

public final class ResearchRandomlyFunc extends LootItemConditionalFunction {
//    public static final MapCodec<ResearchRandomlyFunc> codec = RecordCodecBuilder.mapCodec(p_344688_ -> commonFields(p_344688_).and(p_344688_.group(RegistryCodecs.homogeneousList(LRResearches.entries).optionalFieldOf("options").forGetter(p_344687_ -> p_344687_.options), Codec.BOOL.optionalFieldOf("only_compatible", Boolean.valueOf(true)).forGetter(p_344689_ -> p_344689_.onlyCompatible))).apply(p_344688_, EnchantRandomlyFunction::new));
    public ResearchRandomlyFunc() {
        super(null);
    }

    @Override
    public LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return null;
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        return null;
    }
}
