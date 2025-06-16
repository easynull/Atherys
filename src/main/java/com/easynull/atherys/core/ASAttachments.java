package com.easynull.atherys.core;

import com.easynull.atherys.api.researches.ResearchSerializable;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.easynull.atherys.Atherys.ID;

public final class ASAttachments {
    public static final DeferredRegister<AttachmentType<?>> attachments = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ID);

    public static final Supplier<AttachmentType<ResearchSerializable>> researches = attachments.register("researches", ()-> AttachmentType.serializable(holder -> new ResearchSerializable()).copyOnDeath().build());
}
