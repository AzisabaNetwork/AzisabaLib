package net.azisaba.library.server;

import org.jetbrains.annotations.NotNull;

public interface NamespacedKey {
    @NotNull
    String getNamespace();

    @NotNull
    String getPath();

    @NotNull
    static NamespacedKey create(@NotNull String namespace, @NotNull String path) {
        return new NamespacedKey() {
            @Override
            public @NotNull String getNamespace() {
                return namespace;
            }

            @Override
            public @NotNull String getPath() {
                return path;
            }
        };
    }
}
