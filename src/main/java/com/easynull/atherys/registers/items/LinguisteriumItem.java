package com.easynull.atherys.registers.items;

import com.easynull.atherys.client.render.screen.LinguisteriumScreen;
import com.easynull.atherys.registers.AsResearches;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public final class LinguisteriumItem extends Item {
    public LinguisteriumItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("tooltip.atherys.linguisterium"));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if(AsResearches.aeterianLang.isUnlocked() && level.isClientSide) LinguisteriumScreen.instance.open();
        return InteractionResult.SUCCESS;
    }
}
