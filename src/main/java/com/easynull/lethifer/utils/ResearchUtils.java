package com.easynull.lethifer.utils;

import com.easynull.lethifer.api.attachments.Research;
import com.easynull.lethifer.api.attachments.ResearchStorage;
import com.easynull.lethifer.client.render.screen.book.Chapter;
import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.client.render.screen.book.Entry;
import com.easynull.lethifer.core.LRAttachments;
import net.minecraft.world.entity.player.Player;

public final class ResearchUtils {

    public static Research getResearch(String name) {
        for (Chapter c : LRResearches.chapters) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        for (Entry e : LRResearches.entries) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static boolean isUnlocked(Player player, Research research) {
        ResearchStorage rs = player.getData(LRAttachments.researches);
        return rs.isUnlocked(research);
    }

    public static void setState(Player player, Research research, boolean unlock) {
        ResearchStorage rs = player.getData(LRAttachments.researches);
        if (unlock) {
            rs.setUnlock(research);
        } else {
            rs.setLock(research);
        }
    }

    public static void setStateAll(Player player, boolean unlock) {
        ResearchStorage rs = player.getData(LRAttachments.researches);
        if (unlock) {
            rs.setUnlockAll();
        } else {
            rs.setLockAll();
        }
    }
}
