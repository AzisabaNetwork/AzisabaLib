package net.azisaba.library.spigot.legacy.block;

import net.azisaba.library.server.Location;
import net.azisaba.library.server.block.CapturedBlock;
import net.azisaba.library.spigot.legacy.SpigotLocation;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

public class SpigotCapturedBlock implements CapturedBlock {
    private final Location location;
    private final Material type;
    private final int redstonePowerLevel;

    public SpigotCapturedBlock(@NotNull BlockState blockState, int redstonePowerLevel) {
        this.location = new SpigotLocation(blockState.getLocation());
        this.type = blockState.getType();
        this.redstonePowerLevel = redstonePowerLevel;
    }

    @Override
    public @NotNull Location getLocation() {
        return location;
    }

    @Override
    public boolean isEmpty() {
        return type == Material.AIR || type.name().endsWith("_AIR");
    }

    @Override
    public @NotNull String getPlatformTypeName() {
        return type.name();
    }

    @Override
    public @NotNull Object getPlatformTypeObject() {
        return type;
    }

    @Override
    public int getRedstonePowerLevel() {
        return redstonePowerLevel;
    }
}
