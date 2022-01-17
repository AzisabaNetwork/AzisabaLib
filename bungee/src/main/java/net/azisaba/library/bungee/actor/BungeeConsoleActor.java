package net.azisaba.library.bungee.actor;

import net.azisaba.library.common.actor.ConsoleActor;
import net.md_5.bungee.api.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BungeeConsoleActor<S extends CommandSender> extends BungeeActor<S> implements ConsoleActor {
    public BungeeConsoleActor(@NotNull S sender) {
        super(sender);
    }
}
