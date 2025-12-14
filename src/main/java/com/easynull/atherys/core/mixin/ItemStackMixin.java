package com.easynull.atherys.core.mixin;

import com.easynull.atherys.core.AeterianLang;
import com.easynull.atherys.registers.AsResearches;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public final class ItemStackMixin {
    @Inject(method = "getTooltipLines", at = @At("RETURN"), cancellable = true)
    private void getTooltip(Item.TooltipContext tooltipContext, Player player, TooltipFlag tooltipFlag, CallbackInfoReturnable<List<Component>> cir) {
        List<Component> original = cir.getReturnValue();
        ItemStack stack = (ItemStack) (Object) this;
        boolean obfuscate = AsResearches.entries.stream().filter(entry -> !entry.isUnlocked()).flatMap(entry -> entry.items.stream()).anyMatch(item -> stack.getItem() == item);
        if (obfuscate) {
            original.replaceAll(c -> {
                if (original.getFirst().contains(c)) return c;
                return Component.literal(AeterianLang.translate(c.getString())).withStyle(c.getStyle());
            });
            cir.setReturnValue(original);
        } else {
            cir.setReturnValue(original);
        }
    }
}
