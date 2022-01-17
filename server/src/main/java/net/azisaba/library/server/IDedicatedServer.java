package net.azisaba.library.server;

import net.azisaba.library.common.IServer;
import net.azisaba.library.server.actor.ServerPlayerActor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface IDedicatedServer extends IServer {
    @Override
    @NotNull
    Collection<? extends @NotNull ServerPlayerActor> getPlayers();

    /**
     * Returns the currently loaded worlds.
     * @return worlds
     */
    @NotNull
    List<World> getWorlds();
}
