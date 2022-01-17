package net.azisaba.library.common.actor;

import net.azisaba.library.common.permission.PermissionHolder;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identified;
import org.jetbrains.annotations.NotNull;

public interface Actor extends Identified, PermissionHolder, Audience {
    @NotNull
    default Type getType() {
        return Type.OTHER;
    }

    void sendMessage(@NotNull String message);

    enum Type {
        PLAYER,
        CONSOLE,
        OTHER,
    }
}
