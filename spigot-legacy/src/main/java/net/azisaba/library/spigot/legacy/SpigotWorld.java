package net.azisaba.library.spigot.legacy;

import net.azisaba.library.server.Chunk;
import net.azisaba.library.server.World;
import net.azisaba.library.server.block.Block;
import net.azisaba.library.spigot.legacy.block.SpigotBlock;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpigotWorld implements World {
    protected final org.bukkit.World world;

    public SpigotWorld(@NotNull org.bukkit.World world) {
        this.world = Objects.requireNonNull(world, "world cannot be null");
    }

    @Override
    public @NotNull String getName() {
        return world.getName();
    }

    @Override
    public long getSeed() {
        return world.getSeed();
    }

    @Override
    public @NotNull Chunk getChunkAt(int chunkX, int chunkZ) {
        return new SpigotChunk(world.getChunkAt(chunkX, chunkZ));
    }

    @Override
    public @NotNull Collection<Chunk> getLoadedChunks() {
        return Stream.of(world.getLoadedChunks()).map(SpigotChunk::new).collect(Collectors.toList());
    }

    @Override
    public @NotNull Block getBlockAt(int x, int y, int z) {
        return new SpigotBlock(world.getBlockAt(x, y, z));
    }

    @Override
    public int getMaxHeight() {
        return world.getMaxHeight();
    }

    @Override
    public int getMinHeight() {
        return 0;
    }

    @Contract("null -> null; !null -> !null")
    public static SpigotWorld of(@Nullable org.bukkit.World world) {
        if (world == null) return null;
        return new SpigotWorld(world);
    }
}
