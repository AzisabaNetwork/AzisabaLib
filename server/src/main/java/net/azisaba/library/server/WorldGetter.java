package net.azisaba.library.server;

import org.jetbrains.annotations.NotNull;

public interface WorldGetter extends NullableWorldGetter {
    /**
     * Returns the world where an object is located at.
     * @return the world
     */
    @Override
    @NotNull
    World getWorld();
}
