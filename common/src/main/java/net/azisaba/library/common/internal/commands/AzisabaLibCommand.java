package net.azisaba.library.common.internal.commands;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.command.Command;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AzisabaLibCommand extends Command {
    public AzisabaLibCommand() {
        super("azisabalib", "azisabalib.use_command");
    }

    @Override
    public void execute(@NotNull Actor actor, @NotNull String @NotNull [] args) {
    }

    @Override
    public @NotNull CompletableFuture<List<String>> tabComplete(@NotNull Actor actor, @NotNull String @NotNull [] args) {
        return super.tabComplete(actor, args);
    }
}
