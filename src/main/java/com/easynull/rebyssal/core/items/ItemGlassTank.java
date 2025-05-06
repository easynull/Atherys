package com.easynull.rebyssal.core.items;

import com.easynull.rebyssal.core.Tabs;
import com.easynull.rebyssal.utils.EntryUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemGlassTank extends Item {
    public ItemGlassTank(int cap) {
        super(new Properties().maxStackSize(1).group(Tabs.main));
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity pl, Hand hand) {
        return ActionResult.resultConsume(this.getDefaultInstance());
    }
}
