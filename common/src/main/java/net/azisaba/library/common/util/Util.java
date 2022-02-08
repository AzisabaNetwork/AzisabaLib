package net.azisaba.library.common.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Util {
    @Contract("_ -> new")
    @NotNull
    public static <T, R> Function<T, R> memorize(@NotNull Function<T, R> function) {
        return memorize(function, false);
    }

    @Contract("_, _ -> new")
    @NotNull
    public static <T, R> Function<T, R> memorize(@NotNull Function<T, R> function, boolean concurrent) {
        Objects.requireNonNull(function, "function cannot be null");
        if (concurrent) {
            return new Function<T, R>() {
                private final Map<T, R> map = new ConcurrentHashMap<>();

                @Override
                public R apply(T t) {
                    return map.computeIfAbsent(t, function);
                }
            };
        } else {
            return new Function<T, R>() {
                private final Map<T, R> map = new HashMap<>();

                @Override
                public R apply(T t) {
                    return map.computeIfAbsent(t, function);
                }
            };
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T @NotNull [] removeFirst(Class<T> clazz, T @NotNull [] arr) {
        T[] array = (T[]) Array.newInstance(clazz, arr.length - 1);
        System.arraycopy(arr, 1, array, 0, arr.length - 1);
        return array;
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (T t : iterable) {
            list.add(t);
        }
        return list;
    }
}
