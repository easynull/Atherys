package com.easynull.lethifer.core.packets;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.commons.lang3.function.TriConsumer;

import java.util.function.Consumer;

public final class LRPacketsHandler {
    @SubscribeEvent
    private void registerNetworkPacket(final RegisterPayloadHandlersEvent event){
        final PayloadRegistrar reg = event.registrar("1");
        reg.playToServer(SyncResearchPacket.type, SyncResearchPacket.streamCodec, toServerHandler(SyncResearchPacket.Handler::handle));
    }

    private static <T extends CustomPacketPayload> IPayloadHandler<T> toServerHandler(TriConsumer<T, MinecraftServer, ServerPlayer> handler) {
        return (m, ctx) -> handler.accept(m, ctx.player().getServer(), (ServerPlayer) ctx.player());
    }
}
