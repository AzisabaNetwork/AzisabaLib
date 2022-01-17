package net.azisaba.library.spigot.legacy;

import net.azisaba.library.server.Chunk;
import net.azisaba.library.server.World;
import org.jetbrains.annotations.NotNull;

public class SpigotChunk implements Chunk {
    private final org.bukkit.Chunk chunk;
    private final World world;
    private final int x;
    private final int z;

    public SpigotChunk(@NotNull org.bukkit.Chunk chunk) {
        this.chunk = chunk;
        this.world = new SpigotWorld(chunk.getWorld());
        this.x = chunk.getX();
        this.z = chunk.getZ();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public boolean isLoaded() {
        return chunk.isLoaded();
    }

    @Override
    public @NotNull World getWorld() {
        return world;
    }

    @Override
    public boolean isSlimeChunk() {
        return chunk.isSlimeChunk();
    }

    @Override
    public boolean load(boolean generate) {
        return chunk.load(generate);
    }

    @Override
    public boolean unload(boolean save) {
        return chunk.unload(save);
    }
}
