package com.easynull.lethifer.api;

import net.minecraft.network.chat.Component;

public interface Research {
    boolean isUnlocked();
    String getName();
    String getCipher();
}
