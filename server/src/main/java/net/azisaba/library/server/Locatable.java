package net.azisaba.library.server;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Locatable extends NullableWorldGetter {
    @NotNull
    Location getLocation();

    @Nullable
    default World getWorld() {
        return getLocation().getWorld();
    }
}
