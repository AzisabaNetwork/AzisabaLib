package net.azisaba.library.spigot.legacy.actor;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface CommandSenderHolder<S extends CommandSender> {
    @NotNull
    S getSender();
}
