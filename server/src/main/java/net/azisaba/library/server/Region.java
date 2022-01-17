package net.azisaba.library.server;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Represents a region which contains two or more {@link Location}s as edges.
 */
public interface Region {
    /**
     * Returns the list of containing locations.
     * @return locations
     */
    @NotNull
    Collection<Location> getLocations();

    boolean isInsideRegion(@NotNull Location location);
}
