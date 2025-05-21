package com.easynull.lethifer.api;

import com.easynull.lethifer.core.LRResearches;
import com.easynull.lethifer.utils.ResearchUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.HashSet;
import java.util.Set;

public class ResearchStorage implements INBTSerializable<Tag> {
    Set<Research> unResearches = new HashSet<>();

    public boolean isUnlocked(Research research) {
        return unResearches.contains(research);
    }

    public void setUnlock(Research research) {
        if(unResearches.contains(research)) return;
        unResearches.add(research);
    }

    public void setUnlockAll() {
        unResearches.clear();
        unResearches.addAll(LRResearches.chapters);
        unResearches.addAll(LRResearches.entries);
    }

    public void setLock(Research research) {
        if(!unResearches.contains(research)) return;
        unResearches.remove(research);
    }

    public void setLockAll() {
        unResearches.clear();
    }

    public void setData(ResearchStorage storage){
        unResearches = storage.unResearches;
    }

    @Override
    public @UnknownNullability Tag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        ListTag list = new ListTag();
        unResearches.forEach(r -> list.add(StringTag.valueOf(r.getName())));
        nbt.put("unlocked", list);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, Tag tag) {
        if(tag instanceof CompoundTag nbt) {
            ListTag list = nbt.getList("unlocked", Tag.TAG_STRING);
            list.forEach(r -> {
                LRResearches.chapters.forEach(c -> setUnlock(ResearchUtils.getResearch(r.getAsString())));
                LRResearches.entries.forEach(e -> setUnlock(ResearchUtils.getResearch(r.getAsString())));
            });
        }
    }
}
