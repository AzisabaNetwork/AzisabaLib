package net.azisaba.library.spigot.legacy.block;

import net.azisaba.library.server.Location;
import net.azisaba.library.server.World;
import net.azisaba.library.server.block.Block;
import net.azisaba.library.server.block.CapturedBlock;
import net.azisaba.library.spigot.legacy.SpigotLocation;
import net.azisaba.library.spigot.legacy.SpigotWorld;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class SpigotBlock implements Block {
    private final org.bukkit.block.Block block;

    public SpigotBlock(@NotNull org.bukkit.block.Block block) {
        this.block = block;
    }

    @NotNull
    public org.bukkit.block.Block getBlock() {
        return block;
    }

    @Override
    public @NotNull Location getLocation() {
        return new SpigotLocation(block.getLocation());
    }

    @Override
    public @NotNull World getWorld() {
        return new SpigotWorld(block.getWorld());
    }

    @Override
    public boolean isEmpty() {
        Material type = block.getType();
        return type.name().endsWith("_AIR") || type.name().equals("AIR");
    }

    @Override
    public @NotNull String getPlatformTypeName() {
        return block.getType().name();
    }

    @Override
    public @NotNull Object getPlatformTypeObject() {
        return block.getType();
    }

    @Override
    public int getRedstonePowerLevel() {
        return block.getBlockPower();
    }

    @Override
    public @NotNull CapturedBlock captureBlock() {
        return new SpigotCapturedBlock(block.getState(), block.getBlockPower());
    }
}
