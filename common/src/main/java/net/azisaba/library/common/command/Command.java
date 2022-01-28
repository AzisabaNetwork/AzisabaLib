package net.azisaba.library.common.command;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.permission.PermissionHolder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Command {
    public static final Component DEFAULT_PERMISSION_DENIED_MESSAGE =
            Component.text("I'm sorry, but you do not have permission to perform this command. " +
                    "Please contact the server administrators if you believe that this is in error.").color(NamedTextColor.RED);
    private final String name;
    private final String permission;
    private final String usage;
    private Component description = Component.text("(no description defined)").color(NamedTextColor.GRAY);

    public Command(@NotNull String name) {
        this(name, null, null);
    }

    public Command(@NotNull String name, @Nullable String permission) {
        this(name, permission, null);
    }

    public Command(@NotNull String name, @Nullable String permission, @Nullable String usage) {
        this.name = name;
        this.permission = permission;
        if (usage == null) {
            this.usage = "/<command> [args]";
        } else {
            if (usage.isEmpty()) {
                this.usage = "/<command>";
            } else {
                this.usage = Objects.requireNonNull(usage);
            }
        }
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
     * Returns the command usage.
     * @return usage
     */
    @NotNull
    public String getUsage() {
        return usage;
    }

    @Contract("_ -> this")
    public Command withDescription(@NotNull Component component) {
        this.description = component;
        return this;
    }

    @Contract("_ -> this")
    public Command withDescription(@NotNull ComponentLike component) {
        this.description = component.asComponent();
        return this;
    }

    @Contract("_ -> this")
    public Command withDescription(@NotNull String text) {
        this.description = Component.text(text);
        return this;
    }

    @NotNull
    public Component getDescription() {
        return description;
    }

    /**
     * Checks if permission holder has permission to execute the command.
     * @param permissionHolder permission holder
     * @return true if holder has permission or permission is null
     */
    public boolean hasPermission(@NotNull PermissionHolder permissionHolder) {
        return permission == null || permissionHolder.hasPermission(permission);
    }

    @NotNull
    public Component getPermissionDeniedMessage() {
        return DEFAULT_PERMISSION_DENIED_MESSAGE;
    }

    public abstract void execute(@NotNull Actor actor, @NotNull String@NotNull[] args);

    @NotNull
    public CompletableFuture<List<String>> suggestAsync(@NotNull Actor actor, @NotNull String@NotNull[] args) {
        return CompletableFuture.completedFuture(Collections.emptyList());
    }

    @NotNull
    public static List<String> filter(@NotNull List<String> list, @NotNull String s) {
        return list.stream().filter(filter(s)).collect(Collectors.toList());
    }

    @Contract(pure = true)
    public static @NotNull Predicate<? super String> filter(@NotNull String s) {
        return s1 -> s1.startsWith(s);
    }
}
