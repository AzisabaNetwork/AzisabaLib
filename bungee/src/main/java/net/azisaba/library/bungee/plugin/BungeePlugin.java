package net.azisaba.library.bungee.plugin;

import net.azisaba.library.bungee.BungeeServer;
import net.azisaba.library.common.Server;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeePlugin extends Plugin {
    public static BungeePlugin plugin;

    @Override
    public void onLoad() {
        plugin = this;
        Server.setServer(new BungeeServer(getProxy(), getLogger()));
    }
}
