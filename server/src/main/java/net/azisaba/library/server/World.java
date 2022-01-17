package net.azisaba.library.server;

import net.azisaba.library.server.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface World extends WorldGetter {
    @Override
    default @NotNull World getWorld() {
        return this;
    }

    /**
     * Returns the name of the world.
     * @return world name
     */
    @NotNull
    String getName();

    /**
     * Returns the seed of the world.
     * @return seed
     */
    long getSeed();

    /**
     * Returns the chunk at provided chunk position.
     * @param chunkX chunk X position
     * @param chunkZ chunk Z position
     * @return the chunk
     */
    @NotNull
    Chunk getChunkAt(int chunkX, int chunkZ);

    /**
     * Returns the chunk at provided location.
     * @param location the location
     * @return the chunk
     */
    @NotNull
    default Chunk getChunkAt(@NotNull Location location) {
        return getChunkAt(location.getChunkX(), location.getChunkZ());
    }

    /**
     * Returns the chunk at provided location.
     * @param locatable any locatable object
     * @return the chunk
     */
    @NotNull
    default Chunk getChunkAt(@NotNull Locatable locatable) {
        return getChunkAt(locatable.getLocation());
    }

    /**
     * Returns the list of loaded chunks.
     * @return chunks
     */
    @NotNull
    Collection<Chunk> getLoadedChunks();

    /**
     * Returns the block at provided location.
     * @param x x position
     * @param y y position
     * @param z z position
     * @return the block
     */
    @NotNull
    Block getBlockAt(int x, int y, int z);

    /**
     * Returns the block at provided location.
     * @param location the location
     * @return the block
     */
    @NotNull
    default Block getBlockAt(@NotNull Location location) {
        return getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /**
     * Returns the block at provided location.
     * @param locatable any locatable object
     * @return the block
     */
    @NotNull
    default Block getBlockAt(@NotNull Locatable locatable) {
        return getBlockAt(locatable.getLocation());
    }

    /**
     * Returns the maximum height of the world.
     * @return maximum height
     */
    int getMaxHeight();

    /**
     * Returns the minimum height of the world.
     * @return minimum height
     */
    int getMinHeight();

    @NotNull
    default Location getLocationAt(double x, double y, double z) {
        return Location.createSimpleLocation(this, x, y, z);
    }
}
