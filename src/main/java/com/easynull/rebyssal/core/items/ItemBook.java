package com.easynull.rebyssal.core.items;

import com.easynull.rebyssal.client.render.book.BookScreen;
import com.easynull.rebyssal.core.Tabs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public final class ItemBook extends Item {
    public ItemBook() {
        super(new Properties().maxStackSize(1).group(Tabs.main));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity pl, Hand hand) {
        if (world.isRemote()) {
            Minecraft.getInstance().displayGuiScreen(new BookScreen());
            pl.playSound(SoundEvents.ITEM_BOOK_PUT, 1f, 1f);
        }
        return ActionResult.resultConsume(this.getDefaultInstance());
    }
}
