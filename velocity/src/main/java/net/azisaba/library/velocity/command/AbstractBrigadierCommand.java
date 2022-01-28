package net.azisaba.library.velocity.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import net.azisaba.library.common.Server;
import net.azisaba.library.common.actor.PlayerActor;
import net.azisaba.library.velocity.VelocityServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public abstract class AbstractBrigadierCommand {
    public abstract LiteralArgumentBuilder<CommandSource> createBuilder();

    @Contract(value = "-> new", pure = true)
    @NotNull
    public BrigadierCommand createCommand() {
        return new BrigadierCommand(createBuilder());
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull LiteralArgumentBuilder<CommandSource> literal(@NotNull String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static <T> @NotNull RequiredArgumentBuilder<CommandSource, T> argument(@NotNull String name, @NotNull ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    @Contract(pure = true)
    @NotNull
    public static <S> String getString(@NotNull CommandContext<S> context, @NotNull String name) {
        return StringArgumentType.getString(context, name);
    }

    @Contract(pure = true)
    public static @NotNull SuggestionProvider<CommandSource> suggestPlayers() {
        return (source, builder) -> suggest(Server.getPlayers().stream().map(PlayerActor::getName), builder);
    }

    @Contract(pure = true)
    public static @NotNull SuggestionProvider<CommandSource> suggestServers() {
        return (source, builder) ->
                suggest(
                        ((VelocityServer) Server.getServer())
                                .getServer()
                                .getAllServers()
                                .stream()
                                .map(RegisteredServer::getServerInfo)
                                .map(ServerInfo::getName),
                        builder
                );
    }

    @NotNull
    public static CompletableFuture<Suggestions> suggest(@NotNull Stream<String> suggestions, @NotNull SuggestionsBuilder builder) {
        String input = builder.getRemaining().toLowerCase(Locale.ROOT);
        suggestions.filter((suggestion) -> matchesSubStr(input, suggestion.toLowerCase(Locale.ROOT))).forEach(builder::suggest);
        return builder.buildFuture();
    }

    public static boolean matchesSubStr(@NotNull String input, @NotNull String suggestion) {
        for(int i = 0; !suggestion.startsWith(input, i); ++i) {
            i = suggestion.indexOf('_', i);
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    public static int sendMessageMissingPlayer(@NotNull CommandSource source, @NotNull String playerName) {
        source.sendMessage(Component.text("Player not found: " + playerName).color(NamedTextColor.RED));
        return 0;
    }

    public static int sendMessageMissingServer(@NotNull CommandSource source, @NotNull String serverName) {
        source.sendMessage(Component.text("Server not found: " + serverName).color(NamedTextColor.RED));
        return 0;
    }
}
