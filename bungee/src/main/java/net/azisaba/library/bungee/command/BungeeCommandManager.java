package net.azisaba.library.bungee.command;

import net.azisaba.library.bungee.actor.BungeeActor;
import net.azisaba.library.bungee.plugin.BungeePlugin;
import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.command.Command;
import net.azisaba.library.common.command.CommandManager;
import net.azisaba.library.common.util.Util;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

public class BungeeCommandManager extends CommandManager {
    private final ProxyServer server;

    public BungeeCommandManager(@NotNull ProxyServer server) {
        this.server = server;
    }

    @Override
    public boolean isCommandDefined(@NotNull String name) {
        return true;
    }

    @Override
    public @NotNull Command getCommandByName(@NotNull String name) {
        net.md_5.bungee.api.plugin.Command command = null;
        for (Map.Entry<String, net.md_5.bungee.api.plugin.Command> entry : server.getPluginManager().getCommands()) {
            if (name.equals(entry.getKey())) {
                command = entry.getValue();
                break;
            }
        }
        if (command == null) throw new NoSuchElementException("Command " + name + " does not exist");
        net.md_5.bungee.api.plugin.Command finalCommand = command;
        return new Command(finalCommand.getName(), finalCommand.getPermission()) {
            @Override
            public void execute(@NotNull Actor actor, @NotNull String @NotNull [] args) {
                finalCommand.execute(((BungeeActor<?>) actor).getSender(), args);
            }

            @Override
            public @NotNull CompletableFuture<List<String>> suggestAsync(@NotNull Actor actor, @NotNull String @NotNull [] args) {
                if (finalCommand instanceof TabExecutor) {
                    Iterable<String> result = ((TabExecutor) finalCommand).onTabComplete(((BungeeActor<?>) actor).getSender(), args);
                    return CompletableFuture.completedFuture(Util.toList(result));
                } else {
                    return super.suggestAsync(actor, args);
                }
            }
        };
    }

    @Override
    protected void registerCommand0(@NotNull Command command) {
        server.getPluginManager().registerCommand(BungeePlugin.plugin, new BungeeCommand(command));
    }
}
