package net.azisaba.library.server.block;

import jdk.nashorn.internal.ir.annotations.Immutable;
import net.azisaba.library.server.WorldLocatable;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a single block in a world, in the server. Unlike {@link Block}, the block state will not change between
 * method calls.
 */
@Immutable
public interface CapturedBlock extends WorldLocatable, Block {
    /**
     * Always returns the same instance. Does nothing.
     * @return this instance
     */
    @Override
    default @NotNull CapturedBlock captureBlock() {
        return this;
    }
}
