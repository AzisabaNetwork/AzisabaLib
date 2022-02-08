package net.azisaba.library.common.command;

import org.jetbrains.annotations.NotNull;

public abstract class CommandManager {
    /**
     * Checks if the command by provided name is defined (can be registered).
     * @param name command name
     * @return true if defined, false otherwise
     */
    public abstract boolean isCommandDefined(@NotNull String name);

    /**
     * Gets a command by its name.
     * @param name command name
     * @return command
     * @throws java.util.NoSuchElementException if a command is not registered with provided name
     * @throws UnsupportedOperationException if the operation is not supported in current platform
     */
    @NotNull
    public abstract Command getCommandByName(@NotNull String name);

    /**
     * Registers a command.
     * @param command command
     */
    public void registerCommand(@NotNull Command command) {
        if (!isCommandDefined(command.getName())) {
            throw new IllegalStateException("Command '" + command.getName() + "' is not defined");
        }
        registerCommand0(command);
    }

    protected abstract void registerCommand0(@NotNull Command command);
}
