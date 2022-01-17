package net.azisaba.library.spigot.legacy.actor;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.spigot.legacy.util.ComponentUtil;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class SpigotActor<S extends CommandSender> implements Actor, CommandSenderHolder<S> {
    protected final S sender;

    protected SpigotActor(@NotNull S sender) {
        this.sender = Objects.requireNonNull(sender);
    }

    @Override
    @NotNull
    public S getSender() {
        return sender;
    }

    @Override
    public boolean hasPermission(@NotNull String permission) {
        return sender.hasPermission(permission);
    }

    @Override
    public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        sender.spigot().sendMessage(ComponentUtil.toBaseComponent(message));
    }

    @Override
    public void sendMessage(@NotNull String message) {
        sender.sendMessage(message);
    }
}
