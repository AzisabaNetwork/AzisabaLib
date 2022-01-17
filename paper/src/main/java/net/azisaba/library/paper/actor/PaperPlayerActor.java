package net.azisaba.library.paper.actor;

import net.azisaba.library.server.Location;
import net.azisaba.library.server.actor.ServerPlayerActor;
import net.azisaba.library.spigot.legacy.SpigotLocation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;
import java.util.UUID;

public class PaperPlayerActor<S extends Player> extends PaperActor<S> implements ServerPlayerActor {
    public PaperPlayerActor(@NotNull S sender) {
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
        SocketAddress address = sender.getAddress();
        if (address == null) throw new RuntimeException("Remote address is null");
        return address;
    }

    @Override
    public @NotNull Location getLocation() {
        return new SpigotLocation(sender.getLocation());
    }
}
