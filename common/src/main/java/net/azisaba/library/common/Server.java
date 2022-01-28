package net.azisaba.library.common;

import net.azisaba.library.common.actor.ConsoleActor;
import net.azisaba.library.common.actor.PlayerActor;
import net.azisaba.library.common.command.CommandManager;
import net.azisaba.library.common.internal.commands.AzisabaLibCommand;
import net.kyori.adventure.identity.Identified;
import net.kyori.adventure.identity.Identity;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class Server {
    private Server() {
        throw new AssertionError();
    }

    private static IServer server;

    public static void setServer(@NotNull IServer server) {
        if (Server.server != null) throw new IllegalStateException("Cannot redefine server singleton");
        Server.server = Objects.requireNonNull(server, "server is null");
        getCommandManager().registerCommand(new AzisabaLibCommand());
        getLogger().info("AzisabaLib initialized on {} ({} - {})", Environment.detect().getName(), getServerName(), getServerVersion());
    }

    @NotNull
    public static IServer getServer() {
        return Objects.requireNonNull(server, "AzisabaLib not initialized");
    }

    /**
     * Returns the platform logger or plugin logger.
     * @return the logger
     */
    @NotNull
    public static Logger getLogger() {
        return server.getLogger();
    }

    @NotNull
    public static Optional<? extends PlayerActor> getPlayer(@NotNull Identified identified) {
        return server.getPlayer(identified.identity().uuid());
    }

    @NotNull
    public static Optional<? extends PlayerActor> getPlayer(@NotNull Identity identity) {
        return server.getPlayer(identity.uuid());
    }

    @NotNull
    public static Optional<? extends PlayerActor> getPlayer(@NotNull UUID uuid) {
        return server.getPlayer(uuid);
    }

    @NotNull
    public static Optional<? extends PlayerActor> getPlayer(@NotNull String name) {
        return server.getPlayer(name);
    }

    /**
     * Returns the connected players.
     * @return players
     */
    @NotNull
    public static Collection<? extends @NotNull PlayerActor> getPlayers() {
        return server.getPlayers();
    }

    /**
     * Returns the console actor.
     * @return the console
     */
    @NotNull
    public static ConsoleActor getConsoleActor() {
        return server.getConsoleActor();
    }

    /**
     * Returns the server name (e.g. Velocity).
     * @return the name
     */
    @NotNull
    public static String getServerName() {
        return server.getServerName();
    }

    /**
     * Returns the server software version.
     * @return the version
     */
    @NotNull
    public static String getServerVersion() {
        return server.getServerVersion();
    }

    /**
     * Returns the current online players count. This method might be more efficient than using {@link #getPlayers()},
     * depending on the implementation.
     * @return # of online players
     */
    public static int getOnlinePlayersCount() {
        return server.getOnlinePlayersCount();
    }

    /**
     * Returns the command manager which can be used to register command.
     * @return command manager
     */
    @NotNull
    public static CommandManager getCommandManager() {
        return server.getCommandManager();
    }

    /**
     * Gets the AzisabaLib version.
     * @return version
     */
    @NotNull
    public static String getVersion() {
        return server.getVersion();
    }
}
