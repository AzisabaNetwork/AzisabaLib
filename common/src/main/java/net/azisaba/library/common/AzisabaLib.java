package net.azisaba.library.common;

import org.jetbrains.annotations.NotNull;

public final class AzisabaLib {
    @NotNull
    public static IServer getServer() {
        return Server.getServer();
    }
}
