package net.azisaba.library.spigot.legacy.plugin;

import net.azisaba.library.common.Environment;
import net.azisaba.library.common.IServer;
import net.azisaba.library.common.Server;
import net.azisaba.library.spigot.legacy.SpigotServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SpigotPlugin extends JavaPlugin {
    @Override
    public void onLoad() {
        Environment env = Environment.detect();
        if (env == Environment.PAPER) {
            // Paper
            Server.setServer(IServer.createFromClass("net.azisaba.library.paper.PaperServer", new Class[] { org.bukkit.Server.class, Logger.class }, new Object[] { getServer(), getLogger() }));
        } else if (env == Environment.SPIGOT2) {
            // Spigot (1.16+)
            Server.setServer(IServer.createFromClass("net.azisaba.library.spigot.modern.SpigotServer", new Class[] { org.bukkit.Server.class, Logger.class }, new Object[] { getServer(), getLogger() }));
        } else if (env == Environment.SPIGOT1) {
            // Spigot (Legacy)
            Server.setServer(new SpigotServer(getServer(), getLogger()));
        } else {
            throw new UnsupportedOperationException("Unsupported platform " + getServer().getName());
        }
    }
}
