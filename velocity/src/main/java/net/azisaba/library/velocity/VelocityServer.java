package net.azisaba.library.velocity;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.azisaba.library.common.IServer;
import net.azisaba.library.common.Logger;
import net.azisaba.library.common.actor.ConsoleActor;
import net.azisaba.library.common.actor.PlayerActor;
import net.azisaba.library.common.command.CommandManager;
import net.azisaba.library.common.internal.util.ProxyUtil;
import net.azisaba.library.velocity.actor.VelocityConsoleActor;
import net.azisaba.library.velocity.actor.VelocityPlayerActor;
import net.azisaba.library.velocity.command.VelocityCommandManager;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class VelocityServer implements IServer {
    private final ProxyServer server;
    private final Logger logger;
    private final VelocityCommandManager commandManager;

    public VelocityServer(@NotNull ProxyServer server, @NotNull org.slf4j.Logger logger) {
        this.server = server;
        this.logger = ProxyUtil.newProxyWithInstance(Logger.class, logger);
        this.commandManager = new VelocityCommandManager(server);
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
        return server.getPlayer(uuid).map(VelocityPlayerActor::new);
    }

    @Override
    public @NotNull Optional<? extends PlayerActor> getPlayer(@NotNull String name) {
        return server.getPlayer(name).map(VelocityPlayerActor::new);
    }

    @Override
    public @NotNull Collection<? extends @NotNull PlayerActor> getPlayers() {
        Set<PlayerActor> players = new HashSet<>();
        for (Player player : server.getAllPlayers()) {
            players.add(new VelocityPlayerActor<>(player));
        }
        return Collections.unmodifiableSet(players);
    }

    @Override
    public @NotNull ConsoleActor getConsoleActor() {
        return new VelocityConsoleActor<>(server.getConsoleCommandSource());
    }

    @Override
    public @NotNull String getServerName() {
        return server.getVersion().getName();
    }

    @Override
    public @NotNull String getServerVersion() {
        return server.getVersion().getVersion();
    }

    @Override
    public int getOnlinePlayersCount() {
        return server.getPlayerCount();
    }

    @Override
    public @NotNull CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public @NotNull String getVersion() {
        return server
                .getPluginManager()
                .getPlugin("azisabalib")
                .orElseThrow(AssertionError::new)
                .getDescription()
                .getVersion()
                .orElse("Unknown");
    }
}
