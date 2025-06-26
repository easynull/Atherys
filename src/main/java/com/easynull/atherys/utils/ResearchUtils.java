package com.easynull.atherys.utils;

import com.easynull.atherys.api.researches.Research;
import com.easynull.atherys.api.researches.ResearchSerializable;
import com.easynull.atherys.core.ASResearches;
import com.easynull.atherys.core.ASAttachments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public final class ResearchUtils {

    public static Research getResearch(String name) {
        return ASResearches.researches.get(name);
    }

    public static boolean isUnlocked(Player player, Research research) {
        ResearchSerializable rs = player.getData(ASAttachments.researches);
        return rs.unResearches.contains(research);
    }

    public static void setState(Player player, Research research, boolean unlock) {
        ResearchSerializable rs = player.getData(ASAttachments.researches);
        if (unlock) {
            if(rs.unResearches.contains(research)) return;
            rs.setUnlock(research);
            player.displayClientMessage(Component.translatable("message.atherys.research.unlock", Component.literal(String.format("[%s]", research.getName()))).withStyle(ChatFormatting.GOLD), true);
        } else {
            if(!rs.unResearches.contains(research)) return;
            rs.setLock(research);
            player.displayClientMessage(Component.translatable("message.atherys.research.lock", Component.literal(String.format("[%s]", research.getName()))).withStyle(ChatFormatting.RED), true);
        }
    }

    public static void setStateAll(Player player, boolean unlock) {
        ResearchSerializable rs = player.getData(ASAttachments.researches);
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
