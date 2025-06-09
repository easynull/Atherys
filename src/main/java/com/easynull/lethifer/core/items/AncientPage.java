package com.easynull.lethifer.core.items;

import com.easynull.lethifer.api.researches.Research;
import com.easynull.lethifer.client.render.screen.PageScreen;
import com.easynull.lethifer.core.LRComponents;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class AncientPage extends Item {
    public AncientPage(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("entry." + stack.get(LRComponents.research)));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.get(LRComponents.research).contains("empty")) return InteractionResult.FAIL;
        Research res = ResearchUtils.getResearch(stack.get(LRComponents.research));
        if (!ResearchUtils.parentIsUnlocked(player, res)) return InteractionResult.FAIL;
        if (!ResearchUtils.isUnlocked(player, res) && player instanceof ServerPlayer sp) {
            if (res.equals(LRResearches.letherianLang)) ResearchUtils.setState(sp, res, true);
            else PageScreen.instance.open(res, stack, sp);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        if (stack.get(LRComponents.research).contains("empty")) {
            return 64;
        }
        return 1;
    }
}
