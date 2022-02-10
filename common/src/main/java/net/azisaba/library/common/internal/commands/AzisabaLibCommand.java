package net.azisaba.library.common.internal.commands;

import net.azisaba.library.common.Environment;
import net.azisaba.library.common.Server;
import net.azisaba.library.common.actor.Actor;
import net.azisaba.library.common.command.Command;
import net.azisaba.library.common.command.CommandSubCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public class AzisabaLibCommand extends CommandSubCommand {
    public AzisabaLibCommand() {
        super(getCommandName(), "azisabalib.use_command");
        withSubCommands(
                new Command("info", "azisabalib.command.info", "") {
                    @Override
                    public void execute(@NotNull Actor actor, @NotNull String @NotNull [] args) {
                        actor.sendMessage("");
                        actor.sendMessage(
                                Component.text("")
                                        .append(Component.text("AzisabaLib").color(NamedTextColor.AQUA))
                                        .append(Component.text(" v").color(NamedTextColor.YELLOW))
                                        .append(Component.text(Server.getVersion()).color(NamedTextColor.GREEN))
                        );
                        TextColor platformColor;
                        Environment env = Environment.detect();
                        if (env == Environment.PAPER) {
                            platformColor = NamedTextColor.WHITE;
                        } else if (env == Environment.SPIGOT1 || env == Environment.SPIGOT2) {
                            platformColor = NamedTextColor.GOLD;
                        } else if (env == Environment.VELOCITY) {
                            platformColor = NamedTextColor.BLUE;
                        } else if (env == Environment.BUNGEECORD) {
                            platformColor = NamedTextColor.YELLOW;
                        } else {
                            platformColor = NamedTextColor.GRAY;
                        }
                        actor.sendMessage(
                                Component.text("")
                                        .append(Component.text("Detected Server Brand: ").color(NamedTextColor.DARK_AQUA))
                                        .append(Component.text(env.getName()).color(platformColor))
                        );
                        actor.sendMessage(
                                Component.text("")
                                        .append(Component.text("Server Version: ").color(NamedTextColor.DARK_AQUA))
                                        .append(Component.text(Server.getServerName()).color(platformColor))
                                        .append(Component.text(" - ").color(NamedTextColor.WHITE))
                                        .append(Component.text(Server.getServerVersion()).color(NamedTextColor.LIGHT_PURPLE))
                        );
                    }
                }.withDescription("Shows some information like server version")
        );
    }

    private static String prefixForCommand() {
        Environment env = Environment.detect();
        if (env == Environment.BUNGEECORD) {
            return "b";
        } else if (env == Environment.VELOCITY) {
            return "v";
        }
        return "";
    }

    @NotNull
    public static String getCommandName() {
        return prefixForCommand() + "azisabalib";
    }
}
