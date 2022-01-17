package net.azisaba.library.common.connection;

import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Represents a something that holds a connection.
 */
public interface ConnectionHolder {
    /**
     * Gets the holder's remote address as {@link SocketAddress}.
     * @throws RuntimeException if unable to get remote address for any reason
     * @return socket address
     */
    @NotNull
    SocketAddress getRemoteAddress();

    /**
     * Gets the holder's remote address as {@link InetSocketAddress}.
     * @throws RuntimeException if unable to get remote address for any reason
     * @throws IllegalArgumentException if holder's remote address isn't instance of {@link InetSocketAddress}
     * @return inet socket address
     */
    @NotNull
    default InetSocketAddress getInetRemoteAddress() throws IllegalArgumentException {
        SocketAddress address = getRemoteAddress();
        if (address instanceof InetSocketAddress) return (InetSocketAddress) address;
        throw new IllegalArgumentException("Remote address isn't instance of InetSocketAddress");
    }
}
