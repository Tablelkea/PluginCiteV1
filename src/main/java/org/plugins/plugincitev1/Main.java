package org.plugins.plugincitev1;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugins.plugincitev1.commands.MarketCommand;
import org.plugins.plugincitev1.commands.TeamsCommand;
import org.plugins.plugincitev1.listeners.TeamsListeners;
import org.plugins.plugincitev1.utils.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import static java.sql.DriverManager.getConnection;

public final class Main extends JavaPlugin {

    public static Connection connection;
    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        connectToDatabase();
        DataBaseManager.loopOnlinePlayers();

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

    public static void connectToDatabase() {
        FileConfiguration config = instance.getConfig();
        String host = config.getString("database.host");
        String port = config.getString("database.port");
        String database = config.getString("database.name");
        String user = config.getString("database.user");
        String password = config.getString("database.password");

        try {
            connection = getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }
}
