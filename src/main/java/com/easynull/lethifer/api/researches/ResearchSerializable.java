package com.easynull.lethifer.api.researches;

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

public final class ResearchSerializable implements INBTSerializable<CompoundTag> {
    public Set<Research> unResearches = new HashSet<>();

    public ResearchSerializable() {
        for (Research research : LRResearches.researchById.values()) {
            if (research.primal) setUnlock(research);
        }
    }

    public boolean isUnlocked(Research research) {
        return unResearches.contains(research);
    }

    public void setUnlock(Research research) {
        unResearches.add(research);
    }

    public void setUnlockAll() {
        unResearches.addAll(LRResearches.researchById.values());
    }

    public void setLock(Research research) {
        unResearches.remove(research);
    }

    public void setLockAll() {
        unResearches.removeIf(research -> !research.primal);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        ListTag unlocked = new ListTag();
        for(Research ur : unResearches){
            if(ur != null) unlocked.add(StringTag.valueOf(ur.getID()));
        }
        CompoundTag nbt = new CompoundTag();
        nbt.put("unlocked", unlocked);
        System.out.print("Saved: " + nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        setLockAll();
        if(nbt.contains("unlocked")){
            ListTag researches = nbt.getList("unlocked", Tag.TAG_STRING);
            for (int i = 0; i < researches.size(); i++) {
                Research research = ResearchUtils.getResearch(researches.getString(i));
                if (research != null && !research.primal) {
                    setUnlock(research);
                }
            }
        }
        System.out.print("Writed: " + nbt);
    }
}
