package net.azisaba.library.spigot.modern;

import net.azisaba.library.common.Logger;
import net.azisaba.library.common.actor.ConsoleActor;
import net.azisaba.library.common.actor.PlayerActor;
import net.azisaba.library.common.command.CommandManager;
import net.azisaba.library.server.IDedicatedServer;
import net.azisaba.library.server.World;
import net.azisaba.library.server.actor.ServerPlayerActor;
import net.azisaba.library.spigot.legacy.actor.SpigotActors;
import net.azisaba.library.spigot.legacy.command.SpigotCommandManager;
import net.azisaba.library.spigot.modern.actor.SpigotConsoleActor;
import net.azisaba.library.spigot.modern.actor.SpigotPlayerActor;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpigotServer implements IDedicatedServer {
    protected final Server server;
    protected final Logger logger;
    protected final SpigotCommandManager commandManager;

    public SpigotServer(@NotNull Server server, @NotNull java.util.logging.Logger logger) {
        this.server = server;
        this.logger = Logger.createFromJavaLogger(logger);
        this.commandManager = new SpigotCommandManager(server);
        SpigotActors.playerActorConstructor = SpigotPlayerActor::new;
        SpigotActors.consoleActorConstructor = SpigotConsoleActor::new;
    }

    @Override
    public @NotNull Collection<? extends @NotNull ServerPlayerActor> getPlayers() {
        return server.getOnlinePlayers().stream().map(SpigotPlayerActor::new).collect(Collectors.toList());
    }

    @Override
    public @NotNull Logger getLogger() {
        return logger;
    }

    @Override
    public @NotNull Optional<? extends PlayerActor> getPlayer(@NotNull UUID uuid) {
        return Optional.ofNullable(server.getPlayer(uuid)).map(SpigotPlayerActor::new);
    }

    @Override
    public @NotNull Optional<? extends PlayerActor> getPlayer(@NotNull String name) {
        return Optional.ofNullable(server.getPlayerExact(name)).map(SpigotPlayerActor::new);
    }

    @Override
    public @NotNull List<World> getWorlds() {
        return server.getWorlds().stream().map(SpigotWorld::new).collect(Collectors.toList());
    }

    @Override
    public @NotNull ConsoleActor getConsoleActor() {
        return new SpigotConsoleActor<>(server.getConsoleSender());
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
    public @NotNull CommandManager getCommandManager() {
        return commandManager;
    }
}
