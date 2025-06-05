package org.plugins.plugincitev1.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plugins.plugincitev1.models.Teams;
import org.plugins.plugincitev1.utils.CustomMessages;

import java.util.List;

public class TeamsCommand implements CommandExecutor, TabCompleter {

    /** Commande de gestion de tout le system d'équipe (/teams) */

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player) {
            if (player.isOp()) {
                if (args.length == 0) {
                    Teams.openTeamsGui(player);
                } else {
                    switch (args[0]) {
                        case "end" -> Teams.lockTeamsSelection();
                        case "open" -> Teams.openTeamsSelection();
                        case "look" -> {

                            switch (args[1]) {
                                case "red" -> Teams.listRedTeam(player);
                                case "blue" -> Teams.listBlueTeam(player);
                                case "green" -> Teams.listGreenTeam(player);
                                case "yellow" -> Teams.listYellowTeam(player);
                                default -> player.sendMessage(Component.text(CustomMessages.wrongTeamName));
                            }
                        }
                        case "check" -> {
                            Player target = Bukkit.getPlayer(args[1]);
                            Teams.checkPlayer(player, target);

                        }
                        case "add" -> {
                            String teamName = args[1].toLowerCase();
                            Player target = Bukkit.getPlayer(args[2]);
                            Teams.forceJoinTeam(player, target, teamName);
                        }
                        case "remove" -> {
                            String teamName = args[1].toLowerCase();
                            Player target = Bukkit.getPlayer(args[2]);
                            Teams.forceLeaveTeam(player, target, teamName);
                        }
                        case "help" -> player.sendMessage(Component.text(CustomMessages.teamsCommandHelp));
                        default -> player.sendMessage(Component.text(CustomMessages.wrongCommandUsage));
                    }
                }
            }else{
                Teams.openTeamsGui(player);
            }

            return false;
        }
        return false;
    }

    /** Permet de définir les auto-complétions à l'execution d'une commande */

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return List.of("end", "open", "look", "check", "add", "remove");
        } else if (args.length == 2 && (args[0].equals("look") || args[0].equals("add") || args[0].equals("remove"))) {
            return List.of("red", "blue", "green", "yellow");
        }
        return null;
    }
}
