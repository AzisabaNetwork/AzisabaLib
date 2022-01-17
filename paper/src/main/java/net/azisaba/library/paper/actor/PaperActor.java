package net.azisaba.library.paper.actor;

import net.azisaba.library.spigot.legacy.actor.SpigotActor;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public abstract class PaperActor<S extends CommandSender> extends SpigotActor<S> {
    public PaperActor(@NotNull S sender) {
        super(sender);
    }

    @Override
    public void sendMessage(@NotNull Identity identity, @NotNull Component component, @NotNull MessageType messageType) {
        sender.sendMessage(identity, component, messageType);
    }
}
