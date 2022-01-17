package net.azisaba.library.server;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Represents a single rectangle region.
 */
public interface RectangleRegion extends Region {
    @Contract(value = "_, _ -> new", pure = true)
    static @NotNull RectangleRegion create(@NotNull Location firstEdge, @NotNull Location secondEdge) {
        return new RectangleRegion() {
            @Override
            public @NotNull Location getFirstEdge() {
                return firstEdge;
            }

            @Override
            public @NotNull Location getSecondEdge() {
                return secondEdge;
            }
        };
    }

    @NotNull
    Location getFirstEdge();

    @NotNull
    Location getSecondEdge();

    @Override
    default @NotNull Collection<Location> getLocations() {
        return Collections.unmodifiableList(Arrays.asList(getFirstEdge(), getSecondEdge()));
    }

    @Override
    default boolean isInsideRegion(@NotNull Location location) {
        Location firstEdge = getFirstEdge();
        Location secondEdge = getSecondEdge();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        double x1 = firstEdge.getX();
        double y1 = firstEdge.getY();
        double z1 = firstEdge.getZ();
        double x2 = secondEdge.getX();
        double y2 = secondEdge.getY();
        double z2 = secondEdge.getZ();
        if ((x >= x1 && x <= x2) || (x >= x2 && x <= x1)) {
            if ((y >= y1 && y <= y2) || (y >= y2 && y <= y1)) {
                return (z >= z1 && z <= z2) || (z >= z2 && z <= z1);
            }
        }
        return false;
    }
}
