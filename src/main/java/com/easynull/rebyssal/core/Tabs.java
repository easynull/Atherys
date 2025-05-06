package com.easynull.rebyssal.core;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class Tabs {
    public static final ItemGroup main = new ItemGroup("main") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Register.book.get());
        }
    };
}
