package com.easynull.lethifer.utils;

import com.easynull.lethifer.api.researches.Research;
import com.easynull.lethifer.api.researches.ResearchSerializable;
import com.easynull.lethifer.client.render.screen.book.Chapter;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.client.render.screen.book.Entry;
import com.easynull.lethifer.core.LRAttachments;
import com.easynull.lethifer.core.packets.SyncResearchPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

public final class ResearchUtils {

    public static Research getResearch(String name) {
        return LRResearches.researchById.get(name);
    }

    public static boolean isUnlocked(Player player, Research research) {
        ResearchSerializable rs = player.getData(LRAttachments.researches);
        return rs.isUnlocked(research);
    }

    public static void setState(ServerPlayer player, Research research, boolean unlock) {
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
        PacketDistributor.sendToPlayer(player, new SyncResearchPacket(player));
    }

    public static void setStateAll(ServerPlayer player, boolean unlock) {
        ResearchSerializable rs = player.getData(LRAttachments.researches);
        if (unlock) {
            rs.setUnlockAll();
        } else {
            rs.setLockAll();
        }
        PacketDistributor.sendToPlayer(player, new SyncResearchPacket(player));
    }

    public static Research getParent(Research children) {
        if(children.getParent() == null) return null;
        return children.getParent();
    }

    public static boolean parentIsUnlocked(Player player, Research children) {
        ResearchSerializable rs = player.getData(LRAttachments.researches);
        Research parent = getParent(children);
        if (parent != null) return rs.isUnlocked(parent);
        else return true;
    }
}
