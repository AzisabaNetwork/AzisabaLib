package net.azisaba.library.common.util;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public final class ClassUtil {
    @NotNull
    public static final Function<String, Boolean> CLASS_PRESENT = Util.memorize(s -> {
        try {
            Class.forName(s);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }, true);

    public static boolean isClassPresent(@NotNull String clazz) {
        return CLASS_PRESENT.apply(clazz);
    }
}
