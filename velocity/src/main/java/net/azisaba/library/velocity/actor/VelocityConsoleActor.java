package net.azisaba.library.velocity.actor;

import com.velocitypowered.api.proxy.ConsoleCommandSource;
import net.azisaba.library.common.actor.ConsoleActor;
import org.jetbrains.annotations.NotNull;

public class VelocityConsoleActor<S extends ConsoleCommandSource> extends VelocityActor<S> implements ConsoleActor {
    public VelocityConsoleActor(@NotNull S source) {
        super(source);
    }
}
