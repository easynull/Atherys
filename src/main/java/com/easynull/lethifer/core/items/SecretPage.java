package com.easynull.lethifer.core.items;

import com.easynull.lethifer.api.attachments.Research;
import com.easynull.lethifer.client.render.screen.PageScreen;
import com.easynull.lethifer.core.LRComponents;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SecretPage extends Item {
    public SecretPage(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("tooltip.lethifer.page"));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Research res = ResearchUtils.getResearch(stack.get(LRComponents.research));
        if(!level.isClientSide) return InteractionResult.FAIL;
        if(stack.get(LRComponents.research).contains("full")){
            ResearchUtils.setStateAll(player, true);
            return InteractionResult.SUCCESS;
        } else if(!ResearchUtils.isUnlocked(player, res)){
            if(res.equals(LRResearches.letherianLang)) ResearchUtils.setState(player, res, true);
            else PageScreen.instance.open(res, stack);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}
