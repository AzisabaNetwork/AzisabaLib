package net.azisaba.library.common;

import net.azisaba.library.common.util.ClassUtil;
import org.jetbrains.annotations.NotNull;

public enum Environment {
    BUNGEECORD("BungeeCord", "net.md_5.bungee.api.ProxyServer"),
    PAPER("Paper", "com.destroystokyo.paper.PaperConfig"),
    SPIGOT1("Spigot (1.x-1.15)", "org.spigotmc.SpigotConfig"),
    SPIGOT2("Spigot (1.16+)", "org.spigotmc.SpigotConfig"),
    VELOCITY("Velocity", "com.velocitypowered.api.proxy.ProxyServer"),
    UNKNOWN("Unknown", "java.lang.Object"),
    ;

    private final String name;
    private final String serverClass;

    Environment(@NotNull String name, @NotNull String serverClass) {
        this.name = name;
        this.serverClass = serverClass;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public boolean isServerClassPresent() {
        return ClassUtil.isClassPresent(serverClass);
    }

    @NotNull
    public static Environment detect() {
        if (BUNGEECORD.isServerClassPresent() &&
                ClassUtil.isClassPresent("net.azisaba.library.bungee.BungeeServer")) {
            return BUNGEECORD;
        }
        if (PAPER.isServerClassPresent() &&
                ClassUtil.isClassPresent("net.kyori.adventure.text.Component") &&
                ClassUtil.isClassPresent("net.azisaba.library.paper.PaperServer")) {
            return PAPER;
        }
        if (SPIGOT1.isServerClassPresent()) {
            if (ClassUtil.isClassPresent("net.md_5.bungee.api.chat.hover.content.Text") &&
                    ClassUtil.isClassPresent("net.azisaba.library.spigot.modern.SpigotServer")) {
                return SPIGOT2;
            }
            return SPIGOT1;
        }
        if (VELOCITY.isServerClassPresent() &&
                ClassUtil.isClassPresent("net.azisaba.library.velocity.VelocityServer")) {
            return VELOCITY;
        }
        return UNKNOWN;
    }

    public boolean isProxy() {
        return this == BUNGEECORD || this == VELOCITY;
    }

    public boolean isServerImplementation() {
        return this == SPIGOT1 || this == SPIGOT2 || this == PAPER;
    }
}
