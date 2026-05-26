package com.raa.filter.mixin;

import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerStatusPacketListenerImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(ServerStatusPacketListenerImpl.class)
public class ServerStatusPacketListenerMixin {
    @Shadow @Final private MinecraftServer server;

    @Redirect(
        method = "handleStatusRequest(Lnet/minecraft/network/protocol/status/ServerboundStatusRequestPacket;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;getStatus()Lnet/minecraft/network/protocol/status/ServerStatus;")
    )
    private ServerStatus raa$stripPlayerSample(MinecraftServer server) {
        ServerStatus original = server.getStatus();
        if (original == null) return null;

        return new ServerStatus(
            original.description(),
            original.players().map(players -> new ServerStatus.Players(
                players.max(),
                players.online(),
                List.of()
            )),
            original.version(),
            original.favicon(),
            original.enforcesSecureChat()
        );
    }
}
