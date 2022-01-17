package net.azisaba.library.bungee.actor;

import net.azisaba.library.common.actor.PlayerActor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;
import java.util.UUID;

public class BungeePlayerActor<S extends ProxiedPlayer> extends BungeeActor<S> implements PlayerActor {
    public BungeePlayerActor(@NotNull S sender) {
        super(sender);
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return sender.getUniqueId();
    }

    @Override
    public @NotNull String getName() {
        return sender.getName();
    }

    @Override
    public @NotNull SocketAddress getRemoteAddress() {
        return sender.getSocketAddress();
    }
}
