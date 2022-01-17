package net.azisaba.library.bungee;

import net.azisaba.library.bungee.actor.BungeeConsoleActor;
import net.azisaba.library.bungee.actor.BungeePlayerActor;
import net.azisaba.library.bungee.command.BungeeCommandManager;
import net.azisaba.library.common.IServer;
import net.azisaba.library.common.Logger;
import net.azisaba.library.common.actor.ConsoleActor;
import net.azisaba.library.common.actor.PlayerActor;
import net.azisaba.library.common.command.CommandManager;
import net.md_5.bungee.api.ProxyServer;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BungeeServer implements IServer {
    private final ProxyServer server;
    private final Logger logger;
    private final BungeeCommandManager commandManager;

    public BungeeServer(@NotNull ProxyServer server, @NotNull java.util.logging.Logger logger) {
        this.server = server;
        this.logger = Logger.createFromJavaLogger(logger);
        this.commandManager = new BungeeCommandManager(server);
    }

    @NotNull
    public ProxyServer getServer() {
        return server;
    }

    @Override
    public @NotNull Logger getLogger() {
        return logger;
    }

    @Override
    public @NotNull Optional<? extends PlayerActor> getPlayer(@NotNull UUID uuid) {
        return Optional.ofNullable(server.getPlayer(uuid)).map(BungeePlayerActor::new);
    }

    @Override
    public @NotNull Optional<? extends PlayerActor> getPlayer(@NotNull String name) {
        return Optional.ofNullable(server.getPlayer(name)).map(BungeePlayerActor::new);
    }

    @Override
    public @NotNull Collection<? extends @NotNull PlayerActor> getPlayers() {
        return server.getPlayers().stream().map(BungeePlayerActor::new).collect(Collectors.toList());
    }

    @Override
    public @NotNull ConsoleActor getConsoleActor() {
        return new BungeeConsoleActor<>(server.getConsole());
    }

    @Override
    public @NotNull String getServerName() {
        return server.getName();
    }

    @Override
    public @NotNull String getServerVersion() {
        return server.getVersion();
    }

    @Override
    public int getOnlinePlayersCount() {
        return server.getOnlineCount();
    }

    @Override
    public @NotNull CommandManager getCommandManager() {
        return commandManager;
    }
}
