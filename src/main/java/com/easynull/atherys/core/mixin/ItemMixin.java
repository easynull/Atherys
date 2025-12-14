package com.easynull.atherys.core.mixin;

import com.easynull.atherys.core.AeterianLang;
import com.easynull.atherys.registers.AsResearches;
import com.mw.nullcore.core.items.CustomableName;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public final class ItemMixin implements CustomableName {
    @Override
    public Component getItemName(String name, ItemStack stack) {
        boolean obfuscate = AsResearches.entries.stream().filter(entry -> !entry.isUnlocked()).flatMap(entry -> entry.items.stream()).anyMatch(item -> stack.getItem() == item);
        if (obfuscate) {
            return Component.literal(AeterianLang.translate(name));
        } else {
            return Component.literal(name);
        }
    }
}
