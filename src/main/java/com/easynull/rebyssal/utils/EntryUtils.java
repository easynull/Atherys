package com.easynull.rebyssal.utils;

import com.easynull.rebyssal.client.render.book.Entries;
import com.easynull.rebyssal.client.render.book.Entry;
import com.easynull.rebyssal.core.capability.SetupCapability;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class EntryUtils {

    public static Entry getEntry(String name){
        for(Entry es : Entries.entries){
            if (es.name.equals(name)){
                return es;
            }
        }
        return null;
    }

    public static List<Entry> getEntries() {
        return Entries.entries;
    }

//    public static List<Entry> getUnlockedEntries(PlayerEntity player) {
//        final List<Entry> entryList = new ArrayList<>();
//        player.getCapability(SetupCapability.cap).ifPresent(cap -> entryList.addAll(cap.getEntries()));
//        return entryList;
//    }

    public static void unlockEntry(PlayerEntity player, Entry entry){
        player.getCapability(SetupCapability.cap).ifPresent(cap -> cap.unlockEntry(entry));
    }

    public static void lockEntry(PlayerEntity player, Entry entry){
        player.getCapability(SetupCapability.cap).ifPresent(cap -> cap.lockEntry(entry));
    }
}
