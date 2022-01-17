package net.azisaba.library.server.block;

import net.azisaba.library.server.Chunk;
import net.azisaba.library.server.WorldLocatable;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a single block in a world, in the server. The block state might change between method calls.
 */
public interface Block extends WorldLocatable {
    boolean isEmpty();

    /**
     * Returns the platform-dependant block type name.
     * @return platform-dependant name
     */
    @NotNull
    String getPlatformTypeName();

    /**
     * Returns the platform-dependant block type object.
     * @return platform-dependant object
     */
    @NotNull
    Object getPlatformTypeObject();

    /**
     * Returns the redstone power being provided to this block
     * @return power level
     */
    int getRedstonePowerLevel();

    @NotNull
    default Chunk getChunk() {
        return getLocation().getChunk();
    }

    /**
     * Returns the snapshot of the current block state.
     * @return captured block
     */
    @NotNull
    CapturedBlock captureBlock();
}
