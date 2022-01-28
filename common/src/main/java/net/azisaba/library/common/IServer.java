package net.azisaba.library.common;

import net.azisaba.library.common.actor.ConsoleActor;
import net.azisaba.library.common.actor.PlayerActor;
import net.azisaba.library.common.command.CommandManager;
import net.kyori.adventure.identity.Identified;
import net.kyori.adventure.identity.Identity;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IServer {
    @NotNull
    static IServer createFromClass(@NotNull @Language(value = "JAVA", prefix = "import ", suffix = ";") String clazz, @NotNull Class<?>@NotNull[] paramTypes, @NotNull Object@NotNull[] params) {
        try {
            Class<?> cl = Class.forName(clazz);
            Class<? extends IServer> iServerCl = cl.asSubclass(IServer.class);
            return iServerCl.getConstructor(paramTypes).newInstance(params);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the platform logger or plugin logger.
     * @return the logger
     */
    @NotNull
    Logger getLogger();

    @NotNull
    default Optional<? extends PlayerActor> getPlayer(@NotNull Identified identified) {
        return getPlayer(identified.identity().uuid());
    }

    @NotNull
    default Optional<? extends PlayerActor> getPlayer(@NotNull Identity identity) {
        return getPlayer(identity.uuid());
    }

    @NotNull
    Optional<? extends PlayerActor> getPlayer(@NotNull UUID uuid);

    @NotNull
    Optional<? extends PlayerActor> getPlayer(@NotNull String name);

    /**
     * Returns the connected players.
     * @return players
     */
    @NotNull
    Collection<? extends @NotNull PlayerActor> getPlayers();

    /**
     * Returns the console actor.
     * @return the console
     */
    @NotNull
    ConsoleActor getConsoleActor();

    /**
     * Returns the server name (e.g. Velocity).
     * @return the name
     */
    @NotNull
    String getServerName();

    /**
     * Returns the server software version.
     * @return the version
     */
    @NotNull
    String getServerVersion();

    /**
     * Returns the current online players count. This method might be more efficient than using {@link #getPlayers()},
     * depending on the implementation.
     * @return # of online players
     */
    default int getOnlinePlayersCount() {
        return getPlayers().size();
    }

    @NotNull
    CommandManager getCommandManager();

    @NotNull
    String getVersion();
}
