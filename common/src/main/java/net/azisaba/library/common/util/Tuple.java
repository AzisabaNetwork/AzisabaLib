package net.azisaba.library.common.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Tuple<A, B, C> {
    public final A first;
    public final B second;
    public final C third;

    private Tuple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Contract(value = "_, _, _ -> new", pure = true)
    @NotNull
    public static <A, B, C> Tuple<A, B, C> of(A first, B second, C third) {
        return new Tuple<>(first, second, third);
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;
        Tuple<?, ?, ?> tuple = (Tuple<?, ?, ?>) o;
        return Objects.equals(first, tuple.first) && Objects.equals(second, tuple.second) && Objects.equals(third, tuple.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
