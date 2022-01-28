package net.azisaba.library.common.command;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Represents a command which contains sub commands to execute.
 */
public class CommandSubCommand extends Command {
    private CommandSubCommand parent;
    protected final Map<String, Command> subCommands = new HashMap<>();

    public CommandSubCommand(@NotNull String name) {
        super(name, null, null);
    }

    public CommandSubCommand(@NotNull String name, @Nullable String permission) {
        super(name, permission, null);
    }

    public CommandSubCommand(@NotNull String name, @Nullable String permission, @Nullable String usage) {
        super(name, permission, usage);
    }

    @NotNull
    public Map<String, Command> getSubCommands() {
        return subCommands;
    }

    @Contract("_ -> this")
    public CommandSubCommand withSubCommands(@NotNull Command... commands) {
        for (Command command : commands) {
            if (command instanceof CommandSubCommand) {
                if (((CommandSubCommand) command).parent != null) {
                    throw new IllegalStateException("parent is already defined (you tried to use the same CommandSubCommand instance twice)");
                }
                ((CommandSubCommand) command).parent = this;
            }
            subCommands.put(command.getName(), command);
        }
        return this;
    }

    @Override
    public void execute(@NotNull Actor actor, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sendCommandList(actor);
            return;
        }
        String name = args[0];
        Command command = subCommands.get(name);
        if (command == null) {
            sendCommandList(actor);
            return;
        }
        if (!command.hasPermission(actor)) {
            actor.sendMessage(command.getPermissionDeniedMessage());
            return;
        }
        command.execute(actor, Util.removeFirst(String.class, args));
    }

    public void sendCommandList(@NotNull Actor actor) {
        actor.sendMessage(Component.text("----------------------------------------").color(NamedTextColor.AQUA));
        for (Command command : subCommands.values()) {
            if (command.hasPermission(actor)) {
                sendCommandInfo(actor, command);
            }
        }
        actor.sendMessage(Component.text("----------------------------------------").color(NamedTextColor.AQUA));
    }

    public void sendCommandInfo(@NotNull Actor actor, @NotNull Command command) {
        String usage = command.getUsage().replace("<command>", buildCommandTree(command));
        Component component = Component.text()
                .append(Component.text(usage).color(NamedTextColor.GOLD))
                .append(Component.text(" - ").color(NamedTextColor.WHITE))
                .append(command.getDescription())
                .build();
        actor.sendMessage(component);
    }

    /**
     * Visits all parent commands and returns the string.
     * @param command the command that will be inserted at the last. May be null.
     * @return the command like <code>parent_of parent_of parent_of parent_of this command</code>
     */
    @NotNull
    public String buildCommandTree(@Nullable Command command) {
        List<String> visited = new ArrayList<>();
        if (command != null) {
            visited.add(command.getName());
        }
        CommandSubCommand current = this;
        while (current != null) {
            visited.add(current.getName());
            current = current.parent;
        }
        Collections.reverse(visited);
        return String.join(" ", visited);
    }

    @Override
    public @NotNull CompletableFuture<List<String>> suggestAsync(@NotNull Actor actor, @NotNull String @NotNull [] args) {
        if (!hasPermission(actor)) return CompletableFuture.completedFuture(Collections.emptyList());
        if (args.length == 0) return CompletableFuture.completedFuture(Collections.singletonList(getName()));
        if (args.length >= 2) {
            Command command = subCommands.get(args[0]);
            if (command != null) {
                return command.suggestAsync(actor, Util.removeFirst(String.class, args));
            }
            return CompletableFuture.completedFuture(Collections.emptyList());
        }
        List<String> list = new ArrayList<>();
        subCommands.forEach((name, command) -> {
            if (command.hasPermission(actor) && name.startsWith(args[0])) {
                list.add(name);
            }
        });
        return CompletableFuture.completedFuture(list);
    }
}
