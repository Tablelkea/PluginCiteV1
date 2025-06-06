package org.plugins.plugincitev1;

import org.bukkit.plugin.java.JavaPlugin;
import org.plugins.plugincitev1.commands.TeamsCommand;

import java.util.Objects;

public final class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        Objects.requireNonNull(getCommand("teams")).setExecutor(new TeamsCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
