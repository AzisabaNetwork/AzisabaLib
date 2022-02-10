package net.azisaba.library.spigot.legacy;

import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.actor.ConsoleActor;
import net.azisaba.library.server.World;
import net.azisaba.library.server.actor.ServerPlayerActor;
import net.azisaba.library.spigot.legacy.actor.SpigotConsoleActor;
import net.azisaba.library.spigot.legacy.actor.SpigotPlayerActor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class Constructors {
    public static Function<Player, ServerPlayerActor> playerActorConstructor = SpigotPlayerActor::new;
    public static Function<ConsoleCommandSender, ConsoleActor> consoleActorConstructor = SpigotConsoleActor::new;
    public static Function<org.bukkit.World, @Nullable World> worldConstructor = SpigotWorld::of;

    @NotNull
    public static Actor createActor(@NotNull CommandSender sender) {
        if (sender instanceof Player) {
            return Constructors.playerActorConstructor.apply((Player) sender);
        } else if (sender instanceof ConsoleCommandSender) {
            return Constructors.consoleActorConstructor.apply((ConsoleCommandSender) sender);
        } else {
            throw new IllegalArgumentException("Unmappable sender " + sender.getClass().getTypeName());
        }
    }
}
