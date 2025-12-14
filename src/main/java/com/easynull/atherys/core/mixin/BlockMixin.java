package com.easynull.atherys.core.mixin;

import com.easynull.atherys.core.AeterianLang;
import com.easynull.atherys.registers.AsResearches;
import com.mw.nullcore.core.items.CustomableName;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public final class BlockMixin implements CustomableName {
    @Override
    public Component getItemName(String name, ItemStack stack) {
        boolean obfuscate = AsResearches.entries.stream().filter(entry -> !entry.isUnlocked()).flatMap(entry -> entry.items.stream()).anyMatch(item -> stack.getItem() == item);
        if (obfuscate) {
            return Component.literal(AeterianLang.translate(name));
        } else {
            return Component.literal(name);
        }
    }

    @Inject(method = "getName", at = @At("TAIL"), cancellable = true)
    private void as$getName(CallbackInfoReturnable<MutableComponent> cir) {
        Block self = (Block)(Object)this;
        String name = cir.getReturnValue().getString();
        boolean obfuscate = AsResearches.entries.stream().filter(entry -> !entry.isUnlocked()).flatMap(entry -> entry.items.stream()).anyMatch(item -> self.asItem() == item);
        if (obfuscate) {
            cir.setReturnValue(Component.literal(AeterianLang.translate(name)));
        } else {
            cir.setReturnValue(Component.literal(name));
        }
    }
}