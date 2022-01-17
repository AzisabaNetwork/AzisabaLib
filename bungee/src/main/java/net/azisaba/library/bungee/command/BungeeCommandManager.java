package net.azisaba.library.bungee.command;

import net.azisaba.library.bungee.plugin.BungeePlugin;
import net.azisaba.library.common.command.Command;
import net.azisaba.library.common.command.CommandManager;
import net.md_5.bungee.api.ProxyServer;
import org.jetbrains.annotations.NotNull;

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
        throw new UnsupportedOperationException(); // not exactly "unsupported" but should be implemented later
    }

    @Override
    protected void registerCommand0(@NotNull Command command) {
        server.getPluginManager().registerCommand(BungeePlugin.plugin, new BungeeCommand(command));
    }
}
