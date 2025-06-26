package com.easynull.atherys.core.items;

import com.easynull.atherys.api.researches.Research;
import com.easynull.atherys.client.render.screen.PageScreen;
import com.easynull.atherys.core.ASComponents;
import com.easynull.atherys.core.ASResearches;
import com.easynull.atherys.utils.ResearchUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class PageItem extends Item {
    public PageItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.literal(String.format("\"%s\"", Component.translatable("entry." + stack.get(ASComponents.research)).getString())));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.get(ASComponents.research).contains("empty")) return InteractionResult.FAIL;
        Research res = ResearchUtils.getResearch(stack.get(ASComponents.research));
        if (!ResearchUtils.isUnlockedParent(player, res)) return InteractionResult.FAIL;
        if (!res.isUnlocked()) {
            if(ResearchUtils.getResearch("aeterian_lang").isUnlocked() && level.isClientSide){
                PageScreen.instance.open(res, stack, player);
            }
            if (res.equals(ASResearches.aeterianLang)){
                ResearchUtils.setState(player, res, true);
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        if (stack.get(ASComponents.research).contains("empty")) {
            return 64;
        }
        return 1;
    }
}
