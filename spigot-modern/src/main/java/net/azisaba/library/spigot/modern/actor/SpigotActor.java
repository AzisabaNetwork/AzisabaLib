package net.azisaba.library.spigot.modern.actor;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.spigot.legacy.actor.CommandSenderHolder;
import net.azisaba.library.spigot.modern.util.ComponentUtil;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public abstract class SpigotActor<S extends CommandSender> extends net.azisaba.library.spigot.legacy.actor.SpigotActor<S> implements Actor {
    protected SpigotActor(@NotNull S sender) {
        super(sender);
    }

    @Override
    public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        sender.spigot().sendMessage(ComponentUtil.toBaseComponent(message));
    }
}
