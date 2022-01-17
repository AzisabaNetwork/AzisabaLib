package net.azisaba.library.common.actor;

import net.kyori.adventure.identity.Identity;
import org.jetbrains.annotations.NotNull;

public interface ConsoleActor extends Actor {
    @Override
    @NotNull
    default Type getType() {
        return Type.CONSOLE;
    }

    @Override
    @NotNull
    default Identity identity() {
        return Identity.nil();
    }
}
