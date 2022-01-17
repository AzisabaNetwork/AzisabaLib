package net.azisaba.library.server;

import org.jetbrains.annotations.Nullable;

public interface NullableWorldGetter {
    /**
     * Returns the world where an object is located at.
     * @return the world or null if world is not defined
     */
    @Nullable
    World getWorld();
}
