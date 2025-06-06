package org.plugins.plugincitev1.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.plugins.plugincitev1.managers.TeamManager;
import org.plugins.plugincitev1.models.Team;
import org.plugins.plugincitev1.utils.CustomMessage;

public class TeamsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player) {

            if (player.isOp()) {
                if (args.length == 0) {
                    TeamManager.openTeamGUI(player);
                    return true;
                }
                switch (args[0]) {
                    case "list" -> {
                        if (args.length > 1) {
                            TeamManager.listAllPlayersInTeam(args[1], player);
                        } else {
                            TeamManager.listAllTeams(player);
                        }
                    }
                    case "check" -> TeamManager.checkPlayerTeam(Bukkit.getPlayer(args[1]));
                    case "forceLeave" -> {
                        Player targetPlayer = Bukkit.getPlayer(args[1]);
                        if(Team.teamsList.get(args[2]) == null) {
                            player.sendMessage(CustomMessage.TEAM_NOT_FOUND);
                            return false;
                        }
                        Team.getTeamByName(args[2]).removePlayer(targetPlayer);
                    }
                    case "forceJoin" -> {
                        if (args.length < 3) {
                            player.sendMessage(Component.text("Usage: /team forceJoin <player> <team>"));
                            return true;
                        }
                        Player targetPlayer = Bukkit.getPlayer(args[1]);
                        if (Team.getTeamByName(args[2]) == null) {
                            player.sendMessage(CustomMessage.TEAM_NOT_FOUND);
                            return false;
                        }
                        Team.getTeamByName(args[2]).addPlayer(targetPlayer);
                    }
                    default -> player.sendMessage(CustomMessage.TEAM_HELP_MESSAGE);
                }
            }else{
                TeamManager.openTeamGUI(player);
            }
        }return true;
    }
}
