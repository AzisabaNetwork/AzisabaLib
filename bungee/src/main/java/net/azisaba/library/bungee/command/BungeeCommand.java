package net.azisaba.library.bungee.command;

import net.azisaba.library.bungee.actor.BungeeActor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

public class BungeeCommand extends Command implements TabExecutor {
    private final net.azisaba.library.common.command.Command command;

    public BungeeCommand(@NotNull net.azisaba.library.common.command.Command command) {
        super(command.getName(), command.getPermission());
        this.command = command;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        command.execute(BungeeActor.of(sender), args);
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return command.suggestAsync(BungeeActor.of(sender), args).join();
    }
}
