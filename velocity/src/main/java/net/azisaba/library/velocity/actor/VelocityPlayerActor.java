package net.azisaba.library.velocity.actor;

import com.velocitypowered.api.proxy.Player;
import net.azisaba.library.common.actor.PlayerActor;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.UUID;

public class VelocityPlayerActor<S extends Player> extends VelocityActor<S> implements PlayerActor {
    public VelocityPlayerActor(@NotNull S player) {
        super(player);
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return source.getUniqueId();
    }

    @Override
    public @NotNull String getName() {
        return source.getUsername();
    }

    @Override
    public @NotNull SocketAddress getRemoteAddress() {
        return source.getRemoteAddress();
    }

    @Override
    public @NotNull InetSocketAddress getInetRemoteAddress() {
        return source.getRemoteAddress();
    }
}
