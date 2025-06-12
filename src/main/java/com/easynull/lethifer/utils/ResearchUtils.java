package com.easynull.lethifer.utils;

import com.easynull.lethifer.api.researches.Research;
import com.easynull.lethifer.api.researches.ResearchSerializable;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.core.LRAttachments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public final class ResearchUtils {

    public static Research getResearch(String name) {
        return LRResearches.researchById.get(name);
    }

    public static boolean isUnlocked(Player player, Research research) {
        ResearchSerializable rs = player.getData(LRAttachments.researches);
        return rs.unResearches.contains(research);
    }

    public static void setState(Player player, Research research, boolean unlock) {
        ResearchSerializable rs = player.getData(LRAttachments.researches);
        if (unlock) {
            if(rs.unResearches.contains(research)) return;
            rs.setUnlock(research);
            player.displayClientMessage(Component.translatable("message.lethifer.research.unlock", Component.literal("[").append(research.getName()).append(Component.literal("]"))).withStyle(ChatFormatting.GOLD), true);
        } else {
            if(!rs.unResearches.contains(research)) return;
            rs.setLock(research);
            player.displayClientMessage(Component.translatable("message.lethifer.research.lock", Component.literal("[").append(research.getName()).append(Component.literal("]"))).withStyle(ChatFormatting.RED), true);
        }
    }

    public static void setStateAll(Player player, boolean unlock) {
        ResearchSerializable rs = player.getData(LRAttachments.researches);
        if (unlock) {
            rs.setUnlockAll();
        } else {
            rs.setLockAll();
        }
    }

    public static Research getParent(Research child) {
        return child.getParent();
    }

    public static boolean isUnlockedParent(Player player, Research child) {
        Research parent = getParent(child);
        if (parent != null) return isUnlocked(player, parent);
        else return true;
    }
}
