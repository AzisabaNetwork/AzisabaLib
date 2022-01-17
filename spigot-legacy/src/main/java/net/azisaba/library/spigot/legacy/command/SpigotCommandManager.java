package net.azisaba.library.spigot.legacy.command;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.command.Command;
import net.azisaba.library.common.command.CommandManager;
import net.azisaba.library.common.permission.PermissionHolder;
import net.azisaba.library.spigot.legacy.actor.CommandSenderHolder;
import net.azisaba.library.spigot.legacy.actor.SpigotActors;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SpigotCommandManager extends CommandManager {
    private final Server server;

    public SpigotCommandManager(@NotNull Server server) {
        this.server = server;
    }

    @Override
    public boolean isCommandDefined(@NotNull String name) {
        return server.getPluginCommand(name) != null;
    }

    @Override
    public @NotNull Command getCommandByName(@NotNull String name) {
        PluginCommand command = server.getPluginCommand(name);
        return new Command(command.getName(), command.getPermission()) {
            @Override
            public void execute(@NotNull Actor actor, @NotNull String @NotNull [] args) {
                command.execute(((CommandSenderHolder<?>) actor).getSender(), command.getName(), args);
            }

            @Override
            public @NotNull CompletableFuture<List<String>> suggestAsync(@NotNull Actor actor, @NotNull String @NotNull [] args) {
                return CompletableFuture.supplyAsync(() -> command.tabComplete(((CommandSenderHolder<?>) actor).getSender(), command.getName(), args));
            }

            @Override
            public @Nullable String getPermission() {
                return command.getPermission();
            }

            @Override
            public boolean hasPermission(@NotNull PermissionHolder permissionHolder) {
                String permission = getPermission();
                if ((permission == null) || (permission.length() == 0)) {
                    return true;
                }
                for (String p : permission.split(";")) {
                    if (permissionHolder.hasPermission(p)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    @Override
    protected void registerCommand0(@NotNull Command command) {
        server.getPluginCommand(command.getName()).setPermission(command.getPermission());
        server.getPluginCommand(command.getName()).setExecutor((sender, bukkitCommand, label, args) -> {
            command.execute(SpigotActors.create(sender), args);
            return true;
        });
        server.getPluginCommand(command.getName()).setTabCompleter((sender, bukkitCommand, alias, args) ->
                command.suggestAsync(SpigotActors.create(sender), args).join()
        );
    }
}
