package com.easynull.reabyssal.core.items;

import com.easynull.reabyssal.client.render.book.BookScreen;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ItemBook extends Item {
    public ItemBook(Properties prop) {
        super(prop);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        BookScreen.instance.openTo(player);
        return InteractionResult.SUCCESS;
    }
}
