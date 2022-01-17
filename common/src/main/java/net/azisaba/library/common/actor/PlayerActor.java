package net.azisaba.library.common.actor;

import net.azisaba.library.common.connection.ConnectionHolder;
import net.kyori.adventure.identity.Identity;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PlayerActor extends Actor, ConnectionHolder {
    @Override
    @NotNull
    default Type getType() {
        return Type.PLAYER;
    }

    /**
     * Gets an uuid of the player.
     * @return uuid
     */
    @NotNull
    UUID getUniqueId();

    @Override
    default @NotNull Identity identity() {
        return Identity.identity(getUniqueId());
    }

    /**
     * Returns the player name which cannot be spoofed by plugins.
     * @return player name
     */
    @NotNull
    String getName();
}
