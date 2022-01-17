package net.azisaba.library.spigot.legacy.actor;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.actor.ConsoleActor;
import net.azisaba.library.server.actor.ServerPlayerActor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class SpigotActors {
    public static Function<Player, ServerPlayerActor> playerActorConstructor = SpigotPlayerActor::new;
    public static Function<ConsoleCommandSender, ConsoleActor> consoleActorConstructor = SpigotConsoleActor::new;

    @NotNull
    public static Actor create(@NotNull CommandSender sender) {
        if (sender instanceof Player) {
            return SpigotActors.playerActorConstructor.apply((Player) sender);
        } else if (sender instanceof ConsoleCommandSender) {
            return SpigotActors.consoleActorConstructor.apply((ConsoleCommandSender) sender);
        } else {
            throw new IllegalArgumentException("Unmappable sender " + sender.getClass().getTypeName());
        }
    }
}
