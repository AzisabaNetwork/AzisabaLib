package net.azisaba.library.common;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public interface Logger {
    void info(@NotNull String message);
    void info(@NotNull String message, Object p1);
    void info(@NotNull String message, Object p1, Object p2);
    void info(@NotNull String message, Object... params);
    void info(@NotNull String message, Throwable throwable);
    void warn(@NotNull String message);
    void warn(@NotNull String message, Object p1);
    void warn(@NotNull String message, Object p1, Object p2);
    void warn(@NotNull String message, Object... params);
    void warn(@NotNull String message, Throwable throwable);
    void error(@NotNull String message);
    void error(@NotNull String message, Object p1);
    void error(@NotNull String message, Object p1, Object p2);
    void error(@NotNull String message, Object... params);
    void error(@NotNull String message, Throwable throwable);

    @Contract(value = "_ -> new", pure = true)
    static @NotNull Logger createFromJavaLogger(@NotNull java.util.logging.Logger logger) {
        return new Logger() {
            @NotNull
            private String format(@NotNull String msg) {
                int length = 0;
                while (msg.contains("{}")) {
                    msg = msg.replace("{}", "{" + length++ + "}");
                }
                return msg;
            }

            @Override
            public void info(@NotNull String message) {
                logger.log(Level.INFO, format(message));
            }

            @Override
            public void info(@NotNull String message, Object p1) {
                logger.log(Level.INFO, format(message), p1);
            }

            @Override
            public void info(@NotNull String message, Object p1, Object p2) {
                logger.log(Level.INFO, format(message), new Object[]{p1, p2});
            }

            @Override
            public void info(@NotNull String message, Object... params) {
                logger.log(Level.INFO, format(message), params);
            }

            @Override
            public void info(@NotNull String message, Throwable throwable) {
                logger.log(Level.INFO, format(message), throwable);
            }

            @Override
            public void warn(@NotNull String message) {
                logger.log(Level.WARNING, format(message));
            }

            @Override
            public void warn(@NotNull String message, Object p1) {
                logger.log(Level.WARNING, format(message), p1);
            }

            @Override
            public void warn(@NotNull String message, Object p1, Object p2) {
                logger.log(Level.WARNING, format(message), new Object[]{p1, p2});
            }

            @Override
            public void warn(@NotNull String message, Object... params) {
                logger.log(Level.WARNING, format(message), params);
            }

            @Override
            public void warn(@NotNull String message, Throwable throwable) {
                logger.log(Level.WARNING, format(message), throwable);
            }

            @Override
            public void error(@NotNull String message) {
                logger.log(Level.SEVERE, format(message));
            }

            @Override
            public void error(@NotNull String message, Object p1) {
                logger.log(Level.SEVERE, format(message), p1);
            }

            @Override
            public void error(@NotNull String message, Object p1, Object p2) {
                logger.log(Level.SEVERE, format(message), new Object[]{p1, p2});
            }

            @Override
            public void error(@NotNull String message, Object... params) {
                logger.log(Level.SEVERE, format(message), params);
            }

            @Override
            public void error(@NotNull String message, Throwable throwable) {
                logger.log(Level.SEVERE, format(message), throwable);
            }
        };
    }
}