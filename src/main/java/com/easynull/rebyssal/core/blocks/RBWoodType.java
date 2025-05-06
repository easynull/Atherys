package com.easynull.rebyssal.core.blocks;

import net.minecraft.block.WoodType;

public class RBWoodType extends WoodType {
    public RBWoodType(String type) {
        super(type);
    }

    public static final RBWoodType dark = new RBWoodType("dark");
}
