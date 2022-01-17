package net.azisaba.library.server;

import net.azisaba.library.server.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Represents a chunk in a world.
 */
public interface Chunk extends WorldGetter {
    /**
     * Returns the block at provided location.
     * @param x x position (relative to chunk, 0-15)
     * @param y y position (world position)
     * @param z z position (relative to chunk, 0-15)
     * @return the block
     * @throws IllegalArgumentException if provided position is out of range
     */
    @NotNull
    default Block getBlockAt(int x, int y, int z) {
        if (x < 0 || x > 15) throw new IllegalArgumentException("Provided x (" + x + ") is out of range");
        if (z < 0 || z > 15) throw new IllegalArgumentException("Provided z (" + z + ") is out of range");
        if (y < getWorld().getMinHeight() || y > getWorld().getMaxHeight()) {
            throw new IllegalArgumentException("Provided y (" + y + ") is out of range (min: " + getWorld().getMinHeight() + ", max: " + getWorld().getMaxHeight() + ")");
        }
        return getWorld().getBlockAt((getX() << 4) + x, y, (getZ() << 4) + z);
    }

    /**
     * Returns the X position of the chunk.
     * @return x position
     */
    int getX();

    /**
     * Returns the Z position of the chunk.
     * @return z position
     */
    int getZ();

    /**
     * Checks if the requested chunk is loaded
     * @return whether the chunk is loaded
     */
    boolean isLoaded();

    /**
     * Returns the world where the chunk is located at.
     * @return the world
     */
    @Override
    @NotNull
    World getWorld();

    /**
     * Checks if this chunk can spawn slimes without being a swamp biome.
     * @return true if slime chunk and slime is able to spawn in this chunk
     */
    @SuppressWarnings("RedundantCast")
    default boolean isSlimeChunk() {
        long seed = getWorld().getSeed();
        int x = getX();
        int z = getZ();
        Random random = new Random(seed +
                (int) (x * x * 0x4c1906) +
                (int) (x * 0x5ac0db) +
                (int) (z * z) * 0x4307a7L +
                (int) (z * 0x5f24f) ^ 0x3ad8025f);
        return random.nextInt(10) == 0;
    }

    /**
     * Loads the chunk.
     * @param generate whether to generate the chunk if the chunk doesn't exist
     * @return true if loaded successfully, false otherwise
     */
    boolean load(boolean generate);

    /**
     * Loads the chunk and generates if the chunk doesn't exist already.
     * @return true if loaded successfully, false otherwise
     */
    default boolean load() {
        return load(true);
    }

    /**
     * Unloads the chunk.
     * @param save Controls whether the chunk is saved
     * @return true if unloaded successfully, false otherwise
     */
    boolean unload(boolean save);

    /**
     * Unloads the chunk and saves the data to disk.
     * @return true if unloaded successfully, false otherwise
     */
    default boolean unload() {
        return unload(true);
    }

    /**
     * Returns the edge of the chunk. X and Z positions are at the minimum position in the chunk and Y position is the
     * minimum height of the world.
     * @return edge of the chunk
     */
    @NotNull
    default Location getFirstEdge() {
        World world = getWorld();
        return Location.createSimpleLocation(world, getX() << 4, world.getMinHeight(), getZ() << 4);
    }

    /**
     * Returns the edge of the chunk. X and Z positions are at the maximum position in the chunk and  Y position is the
     * maximum height of the world.
     * @return edge of the chunk
     */
    @NotNull
    default Location getSecondEdge() {
        World world = getWorld();
        return Location.createSimpleLocation(world, (getX() << 4) + 15, world.getMaxHeight(), (getZ() << 4) + 15);
    }

    /**
     * Converts chunk to a rectangle region.
     * @return rectangle region
     */
    @NotNull
    default RectangleRegion toRectangleRegion() {
        return RectangleRegion.create(getFirstEdge(), getSecondEdge());
    }
}
