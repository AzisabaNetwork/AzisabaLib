package net.azisaba.library.velocity.actor;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import com.velocitypowered.api.proxy.Player;
import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.permission.PermissionState;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class VelocityActor<S extends CommandSource> implements Actor {
    @NotNull
    public static Actor of(@NotNull CommandSource source) {
        if (source instanceof Player) {
            return new VelocityPlayerActor<>((Player) source);
        } else if (source instanceof ConsoleCommandSource) {
            return new VelocityConsoleActor<>((ConsoleCommandSource) source);
        } else {
            throw new IllegalArgumentException("Unmappable source " + source.getClass().getTypeName());
        }
    }

    protected final S source;

    protected VelocityActor(@NotNull S source) {
        this.source = Objects.requireNonNull(source);
    }

    @Override
    public boolean hasPermission(@NotNull String permission) {
        return source.hasPermission(permission);
    }

    @Override
    @NotNull
    public PermissionState getPermissionState(@NotNull String permission) {
        return PermissionState.valueOf(source.getPermissionValue(permission).name());
    }

    @Override
    public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        this.source.sendMessage(source, message, type);
    }

    @Override
    public void sendMessage(@NotNull String message) {
        sendMessage(LegacyComponentSerializer.legacySection().deserialize(message));
    }
}
