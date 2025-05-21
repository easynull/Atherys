package com.easynull.lethifer.core.items;

import com.easynull.lethifer.client.render.PageScreen;
import com.easynull.lethifer.core.LRComponents;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public final class Linguisterium extends Item {
    public Linguisterium(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("tooltip.lethifer.linguisterium"));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        return InteractionResult.SUCCESS;
    }
}
