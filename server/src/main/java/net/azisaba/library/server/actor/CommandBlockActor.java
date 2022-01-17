package net.azisaba.library.server.actor;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.server.WorldLocatable;
import net.kyori.adventure.identity.Identity;
import org.jetbrains.annotations.NotNull;

public interface CommandBlockActor extends Actor, WorldLocatable {
    @Override
    @NotNull
    default Type getType() {
        return Type.OTHER;
    }

    @Override
    @NotNull
    default Identity identity() {
        return Identity.nil();
    }
}
