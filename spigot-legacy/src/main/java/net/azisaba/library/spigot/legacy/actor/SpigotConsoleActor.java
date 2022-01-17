package net.azisaba.library.spigot.legacy.actor;

import net.azisaba.library.common.actor.ConsoleActor;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class SpigotConsoleActor<S extends ConsoleCommandSender> extends SpigotActor<S> implements ConsoleActor {
    public SpigotConsoleActor(@NotNull S sender) {
        super(sender);
    }
}
