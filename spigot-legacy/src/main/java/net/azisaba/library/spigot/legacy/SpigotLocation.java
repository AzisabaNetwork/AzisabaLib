package net.azisaba.library.spigot.legacy;

import net.azisaba.library.server.Location;
import net.azisaba.library.server.World;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Contract;
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

    @Override
    public float getYaw() {
        return location.getYaw();
    }

    @Override
    public float getPitch() {
        return location.getPitch();
    }

    @Contract("_ -> new")
    @NotNull
    public static org.bukkit.Location toBukkit(@NotNull Location location) {
        org.bukkit.World world = null;
        if (location.hasWorld()) {
            world = Bukkit.getWorld(location.getWorld(true).getName());
        }
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();
        return new org.bukkit.Location(world, x, y, z, yaw, pitch);
    }
}
