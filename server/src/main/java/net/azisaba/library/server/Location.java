package net.azisaba.library.server;

import net.azisaba.library.server.block.Block;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface Location {
    /**
     * Creates a new location instance that doesn't depend on platform.
     * @param world the world
     * @param x x position
     * @param y y position
     * @param z z position
     * @return the location
     */
    @Contract(value = "_, _, _, _ -> new", pure = true)
    static @NotNull Location createSimpleLocation(@Nullable World world, double x, double y, double z) {
        return createSimpleLocation(world, x, y, z, 0, 0);
    }

    /**
     * Creates a new location instance that doesn't depend on platform.
     * @param world the world
     * @param x x position
     * @param y y position
     * @param z z position
     * @param yaw the yaw
     * @param pitch the pitch
     * @return the location
     */
    @Contract(value = "_, _, _, _, _, _ -> new", pure = true)
    static @NotNull Location createSimpleLocation(@Nullable World world, double x, double y, double z, float yaw, float pitch) {
        return new Location() {
            @Override
            public @Nullable World getWorld() {
                return world;
            }

            @Override
            public double getX() {
                return x;
            }

            @Override
            public double getY() {
                return y;
            }

            @Override
            public double getZ() {
                return z;
            }

            @Override
            public float getYaw() {
                return yaw;
            }

            @Override
            public float getPitch() {
                return pitch;
            }
        };
    }

    static int floor(double num) {
        final int floor = (int) num;
        return floor == num ? floor : floor - (int) (Double.doubleToRawLongBits(num) >>> 63);
    }

    /**
     * Returns the world of the location. May returns null if world is not loaded or was not provided.
     * @return the world
     */
    @Nullable
    World getWorld();

    @Contract("true -> !null")
    @Nullable
    default World getWorld(boolean notNull) {
        if (!notNull) return getWorld();
        return Objects.requireNonNull(getWorld(), "Parameter \"notNull\" is true, but getWorld() returned null");
    }

    /**
     * Checks whether the world is set for the location.
     * @return true if world is set and present, false otherwise
     */
    default boolean hasWorld() {
        return getWorld() != null;
    }

    /**
     * Get the X pos.
     * @return x
     */
    double getX();

    /**
     * Get the Y pos.
     * @return y
     */
    double getY();

    /**
     * Get the Z pos.
     * @return z
     */
    double getZ();

    float getYaw();

    float getPitch();

    /**
     * Returns the floored block X pos.
     * @return block x
     */
    default int getBlockX() {
        return floor(getX());
    }

    /**
     * Returns the floored block Y pos.
     * @return block y
     */
    default int getBlockY() {
        return floor(getY());
    }

    /**
     * Returns the floored block Z pos.
     * @return block z
     */
    default int getBlockZ() {
        return floor(getZ());
    }

    /**
     * Returns the chunk X pos.
     * @return chunk x
     */
    default int getChunkX() {
        return getBlockX() >> 4;
    }

    /**
     * Returns the chunk Z pos.
     * @return chunk z
     */
    default int getChunkZ() {
        return getBlockZ() >> 4;
    }

    /**
     * Returns the chunk where the block is located at.
     * @return the chunk
     * @throws NullPointerException if world is null
     */
    @NotNull
    default Chunk getChunk() {
        return getWorld(true).getChunkAt(this);
    }

    /**
     * Gets the block for the location.
     * @return the block
     * @throws NullPointerException if world is null
     */
    @NotNull
    default Block getBlock() {
        return getWorld(true).getBlockAt(this);
    }
}
