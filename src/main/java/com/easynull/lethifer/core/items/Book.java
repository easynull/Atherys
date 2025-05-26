package com.easynull.lethifer.core.items;

import com.easynull.lethifer.api.essential.NEssential;
import com.easynull.lethifer.client.render.screen.book.BookScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public final class Book extends Item implements NEssential {
    final int maxEnergy;
    public Book(Properties prop, int maxEnergy) {
        super(prop);
        this.maxEnergy = maxEnergy;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        BookScreen.instance.open();
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("tooltip.lethifer.energy.current", getEnergy(stack), maxEnergy));
    }

    @Override
    public int getMaxEnergy() {
        return maxEnergy;
    }
}
