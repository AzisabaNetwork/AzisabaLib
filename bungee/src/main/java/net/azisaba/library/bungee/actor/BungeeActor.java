package net.azisaba.library.bungee.actor;

import net.azisaba.library.bungee.BungeeServer;
import net.azisaba.library.bungee.util.ComponentUtil;
import net.azisaba.library.common.AzisabaLib;
import net.azisaba.library.common.actor.Actor;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class BungeeActor<S extends CommandSender> implements Actor {
    @NotNull
    public static Actor of(@NotNull CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return new BungeePlayerActor<>((ProxiedPlayer) sender);
        } else if (sender == ((BungeeServer) AzisabaLib.getServer()).getServer().getConsole()) {
            return AzisabaLib.getServer().getConsoleActor();
        } else {
            throw new IllegalArgumentException("Unmappable sender " + sender.getClass().getTypeName());
        }
    }

    protected final S sender;

    protected BungeeActor(@NotNull S sender) {
        this.sender = Objects.requireNonNull(sender);
    }

    @NotNull
    public S getSender() {
        return sender;
    }

    @Override
    public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        sender.sendMessage(ComponentUtil.toBaseComponent(message));
    }

    @Override
    public void sendMessage(@NotNull String message) {
        sender.sendMessage(TextComponent.fromLegacyText(message));
    }

    @Override
    public boolean hasPermission(@NotNull String permission) {
        return sender.hasPermission(permission);
    }
}
