package net.azisaba.library.common.command;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.permission.PermissionHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class Command {
    private final String name;
    private final String permission;

    public Command(@NotNull String name, @Nullable String permission) {
        this.name = name;
        this.permission = permission;
    }

    /**
     * Returns the command name.
     * @return name
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Returns the permission required to execute the command.
     * @return permission or null if not defined
     */
    @Nullable
    public String getPermission() {
        return permission;
    }

    /**
     * Checks if permission holder has permission to execute the command.
     * @param permissionHolder permission holder
     * @return true if holder has permission or permission is null
     */
    public boolean hasPermission(@NotNull PermissionHolder permissionHolder) {
        return permission == null || permissionHolder.hasPermission(permission);
    }

    public abstract void execute(@NotNull Actor actor, @NotNull String@NotNull[] args);

    @NotNull
    public CompletableFuture<List<String>> suggestAsync(@NotNull Actor actor, @NotNull String@NotNull[] args) {
        return CompletableFuture.completedFuture(Collections.emptyList());
    }
}
