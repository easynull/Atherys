package com.easynull.atherys.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Map;

public final class BlockUtils {
    public static void saveBlock(Block block, CompoundTag nbt){
        BlockState state = block.defaultBlockState();
        nbt.putString("block", BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString());
        if(!state.getValues().isEmpty()){
            CompoundTag propsNBT = new CompoundTag();
            for(Map.Entry<Property<?>, Comparable<?>> map : state.getValues().entrySet()){
                String value = getPropertyValueName(map.getKey(), map.getValue());
                propsNBT.putString(map.getKey().getName(), value);
            }
            nbt.put("properties", propsNBT);
        }
    }
    public static Block loadBlock(CompoundTag tag) {
        ResourceLocation blockId = ResourceLocation.parse(tag.getString("block"));
        Block block = BuiltInRegistries.BLOCK.getValue(blockId);
        if (tag.contains("properties")) {
            CompoundTag propertiesTag = tag.getCompound("properties");
            for (String key : propertiesTag.getAllKeys()) {
                Property<?> property = block.getStateDefinition().getProperty(key);
                if (property != null) {
                    block = setPropertyValue(block, property, propertiesTag.getString(key));
                }
            }
        }
        return block;
    }
    private static <T extends Comparable<T>> String getPropertyValueName(Property<T> property, Comparable<?> value) {
        return property.getName((T) value);
    }
    private static <T extends Comparable<T>> Block setPropertyValue(Block block, Property<T> property, String valueName) {
        return property.getValue(valueName).map(value -> block.getStateDefinition().any().setValue(property, value)).orElse(block.defaultBlockState()).getBlock();
    }
}
