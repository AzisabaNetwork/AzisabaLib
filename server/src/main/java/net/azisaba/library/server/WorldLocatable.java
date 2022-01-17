package net.azisaba.library.server;

import org.jetbrains.annotations.NotNull;

/**
 * Similar to {@link Locatable}, but {@link #getWorld()} never returns null.
 */
public interface WorldLocatable extends Locatable, WorldGetter {
    @NotNull
    default World getWorld() {
        return getLocation().getWorld(true);
    }
}
