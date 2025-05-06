package com.easynull.rebyssal.core.capability;

import com.easynull.rebyssal.client.render.book.Entry;
import com.easynull.rebyssal.utils.EntryUtils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class SetupCapability {
    @CapabilityInject(IEntriesCapability.class)
    public static final Capability<IEntriesCapability> cap = null;

    public static void setup() {
        CapabilityManager.INSTANCE.register(IEntriesCapability.class, new Capability.IStorage<>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IEntriesCapability> capability, IEntriesCapability instance, Direction direction) {
                CompoundNBT nbt = new CompoundNBT();
                ListNBT entryList = new ListNBT();
                for (Entry entry : instance.getEntries()) {
                    CompoundNBT entryNBT = new CompoundNBT();
                    entryNBT.putString("name", entry.name);
                    entryList.add(entryNBT);
                }
                nbt.put("entries", entryList);
                return nbt;
            }

            @Override
            public void readNBT(Capability<IEntriesCapability> capability, IEntriesCapability instance, Direction direction, INBT inbt) {
                if (inbt instanceof CompoundNBT nbt) {
                    ListNBT entryList = nbt.getList("entries", Constants.NBT.TAG_COMPOUND);
                    for (int i = 0; i < entryList.size(); i++) {
                        CompoundNBT entryNBT = entryList.getCompound(i);
                        instance.unlockEntry(EntryUtils.getEntry(entryNBT.getString("name")));
                    }
                }
            }
        }, EntriesCapability::new);
    }

    public static class EntriesCapProvider implements ICapabilityProvider {
        private final LazyOptional<IEntriesCapability> holder = LazyOptional.of(EntriesCapability::new);

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction direction) {
            return capability == SetupCapability.cap ? holder.cast() : LazyOptional.empty();
        }
    }
}
