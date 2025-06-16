package com.easynull.atherys.core.worldgen;

import com.easynull.atherys.client.render.screen.book.Entry;
import com.easynull.atherys.core.ASComponents;
import com.easynull.atherys.core.ASResearches;
import com.easynull.atherys.core.ASWorldGen;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

public class RandomResearchFunction extends LootItemConditionalFunction {
    public static final MapCodec<RandomResearchFunction> codec = RecordCodecBuilder.mapCodec(instance -> commonFields(instance).apply(instance, RandomResearchFunction::new));

    public RandomResearchFunction(List<LootItemCondition> conditions) {
        super(conditions);
    }

    @Override
    public LootItemFunctionType<RandomResearchFunction> getType() {
        return ASWorldGen.randomResearch.get();
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        List<Entry> entries = ASResearches.entries.stream().filter(entry -> !entry.primal).toList();
        if(!entries.isEmpty()) {
            RandomSource rand = context.getRandom();
            if(rand.nextFloat() < 1f) {
                Entry entry = entries.get(rand.nextInt(entries.size()));
                ItemStack resultStack = new ItemStack(stack.getItem());
                resultStack.set(ASComponents.research, entry.getID());
                return resultStack;
            }
        }
        return stack;
    }
}
