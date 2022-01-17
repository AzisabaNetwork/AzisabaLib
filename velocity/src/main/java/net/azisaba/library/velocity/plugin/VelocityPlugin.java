package net.azisaba.library.velocity.plugin;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.azisaba.library.common.Server;
import net.azisaba.library.velocity.VelocityServer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Plugin(id = "azisabalib", name = "AzisabaLib", version = "@YOU_SHOULD_NOT_SEE_THIS_AS_VERSION@",
        url = "https://github.com/AzisabaNetwork", authors = "Azisaba Network")
public class VelocityPlugin {
    @Inject
    public VelocityPlugin(@NotNull ProxyServer server, @NotNull Logger logger) {
        Server.setServer(new VelocityServer(server, logger));
    }
}
