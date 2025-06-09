package com.easynull.lethifer.core;

import com.easynull.lethifer.api.researches.ResearchSerializable;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.easynull.lethifer.Lethifer.ID;

public final class LRAttachments {
    public static final DeferredRegister<AttachmentType<?>> attachments = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ID);

    public static final Supplier<AttachmentType<ResearchSerializable>> researches = attachments.register("researches", ()-> AttachmentType.serializable(holder -> new ResearchSerializable()).copyOnDeath().build());
}
