package com.easynull.lethifer.core.packets;

import com.easynull.lethifer.core.LRAttachments;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public record SyncResearchPacket(CompoundTag nbt, UUID uuid) implements CustomPacketPayload {
    public static final Type<SyncResearchPacket> type = new Type<>(ResourceLocation.fromNamespaceAndPath("lethifer", "sync_researches"));
    public static final StreamCodec<FriendlyByteBuf, SyncResearchPacket> streamCodec = CustomPacketPayload.codec(SyncResearchPacket::write, SyncResearchPacket::new);

    public SyncResearchPacket(FriendlyByteBuf buffer){
        this(buffer.readNbt(), buffer.readUUID());
    }

    public SyncResearchPacket(Player player){
        this(player.getData(LRAttachments.researches).serializeNBT(player.registryAccess()), player.getUUID());
    }

    private void write(FriendlyByteBuf buffer) {
        buffer.writeNbt(nbt);
        buffer.writeUUID(uuid);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return type;
    }

    public static class Handler {
        public static void handle(SyncResearchPacket packet, MinecraftServer server, ServerPlayer sPlayer) {
            CompoundTag nbt = packet.nbt();
            server.execute(()-> {
                sPlayer.getData(LRAttachments.researches).deserializeNBT(sPlayer.registryAccess(), nbt);
                System.out.print("Put!");
            });
        }
    }
}