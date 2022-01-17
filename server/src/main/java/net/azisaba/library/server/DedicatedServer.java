package net.azisaba.library.server;

import net.azisaba.library.common.Server;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class DedicatedServer {
    private DedicatedServer() {
        throw new AssertionError();
    }

    @Contract(pure = true)
    @NotNull
    public static IDedicatedServer getServer() {
        return (IDedicatedServer) Server.getServer();
    }
}
