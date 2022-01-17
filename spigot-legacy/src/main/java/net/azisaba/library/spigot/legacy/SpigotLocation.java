package net.azisaba.library.spigot.legacy;

import net.azisaba.library.server.Location;
import net.azisaba.library.server.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SpigotLocation implements Location {
    private final org.bukkit.Location location;

    public SpigotLocation(@NotNull org.bukkit.Location location) {
        this.location = Objects.requireNonNull(location, "location cannot be null");
    }

    @Override
    public @Nullable World getWorld() {
        return SpigotWorld.of(location.getWorld());
    }

    @Override
    public double getX() {
        return location.getX();
    }

    @Override
    public double getY() {
        return location.getY();
    }

    @Override
    public double getZ() {
        return location.getZ();
    }
}
