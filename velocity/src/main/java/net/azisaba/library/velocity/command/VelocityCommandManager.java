package net.azisaba.library.velocity.command;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import net.azisaba.library.common.command.Command;
import net.azisaba.library.common.command.CommandManager;
import net.azisaba.library.velocity.actor.VelocityActor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VelocityCommandManager extends CommandManager {
    private final com.velocitypowered.api.command.CommandManager commandManager;

    public VelocityCommandManager(@NotNull ProxyServer server) {
        this.commandManager = server.getCommandManager();
    }

    @Override
    public boolean isCommandDefined(@NotNull String name) {
        return true;
    }

    @Contract("_ -> fail")
    @Override
    public @NotNull Command getCommandByName(@NotNull String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void registerCommand0(@NotNull Command command) {
        commandManager.register(command.getName(), new SimpleCommand() {
            @Override
            public void execute(Invocation invocation) {
                command.execute(VelocityActor.of(invocation.source()), invocation.arguments());
            }

            @Override
            public CompletableFuture<List<String>> suggestAsync(Invocation invocation) {
                return command.tabComplete(VelocityActor.of(invocation.source()), invocation.arguments());
            }
        });
    }
}
