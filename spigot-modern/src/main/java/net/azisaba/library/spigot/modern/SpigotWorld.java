package net.azisaba.library.spigot.modern;

import org.bukkit.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpigotWorld extends net.azisaba.library.spigot.legacy.SpigotWorld {
    public SpigotWorld(@NotNull World world) {
        super(world);
    }

    @Override
    public int getMinHeight() {
        return world.getMinHeight();
    }

    @Contract("null -> null; !null -> !null")
    public static SpigotWorld of(@Nullable org.bukkit.World world) {
        if (world == null) return null;
        return new SpigotWorld(world);
    }
}
