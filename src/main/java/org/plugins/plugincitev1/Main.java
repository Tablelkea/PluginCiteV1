package org.plugins.plugincitev1;

import org.bukkit.plugin.java.JavaPlugin;
import org.plugins.plugincitev1.commands.MarketCommand;
import org.plugins.plugincitev1.commands.TeamsCommand;
import org.plugins.plugincitev1.listeners.TeamsListeners;
import org.plugins.plugincitev1.utils.DataBaseManager;

import java.sql.Connection;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static Connection connection;
    public static Main instance;

    @Override
    public void onEnable() {

        DataBaseManager.connectToDatabase();
        DataBaseManager.loopOnlinePlayers();
        instance = this;

        // Plugin startup logic
        Objects.requireNonNull(getCommand("teams")).setExecutor(new TeamsCommand());
        Objects.requireNonNull(getCommand("market")).setExecutor(new MarketCommand());

        getServer().getPluginManager().registerEvents(new TeamsListeners(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DataBaseManager.disconnectFromDatabase();
    }
}
