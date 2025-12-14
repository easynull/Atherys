package com.easynull.atherys.registers.items;

import com.easynull.atherys.core.essential.AreElement;
import com.easynull.atherys.client.render.screen.book.BookScreen;
import com.mw.nullcore.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public final class AstralonItem extends Item implements AreElement {
    final long max;
    public AstralonItem(Properties prop, long max) {
        super(prop);
        this.max = max;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        Utils.Client.setSafeScreen(new BookScreen(player));
        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("tooltip.atherys.essential.current", getAre(stack), getMaxAre()));
    }

    @Override
    public long getMaxAre() {
        return max;
    }
}
