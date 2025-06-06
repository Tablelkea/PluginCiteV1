package org.plugins.plugincitev1.managers;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.plugins.plugincitev1.models.Team;
import org.plugins.plugincitev1.utils.CustomMessage;

import static org.plugins.plugincitev1.gui.TeamGUI.teamGui;

public class TeamManager {

    /** Ajout d'une nouvelle équipe */

    public static void newTeam(Team teams){
        teamGui.addItem(teams.getTeamIcon());
    }

    /** Liste tous les joueurs dans une équipe */

    public static void listAllPlayersInTeam(String teamName, Player player) {
        if (Team.teamsList.containsKey(teamName)) {
            Team team = Team.teamsList.get(teamName);
            player.sendMessage(CustomMessage.LIST_TEAM_MEMBERS
                    .replace("%team%", teamName)
                    .replace("%size%", String.valueOf(team.getTeamMembers().size()))
                    .replace("%players%", team.getTeamMembers().stream()
                            .map(Player::getName)
                            .reduce((a, b) -> a + ", " + b)
                            .orElse("")));
        } else {
            player.sendMessage(CustomMessage.TEAM_NOT_FOUND);
        }
    }

    /** Vérifie si un joueur est dans une équipe */

    public static void checkPlayerTeam(Player player) {
        if (Team.teamsList.values().stream().noneMatch(team -> team.getTeamMembers().contains(player))) {
            player.sendMessage(CustomMessage.PLAYER_NOT_IN_TEAM.replace("%player%", player.getName()));
        } else {
            player.sendMessage(Component.text("You are in a team!"));
            // recupere le nom de l'équipe du joueur
            String teamName = Team.teamsList.values().stream()
                    .filter(team -> team.getTeamMembers().contains(player))
                    .map(Team::getTeamName)
                    .findFirst()
                    .orElse("Unknown Team");
            player.sendMessage(CustomMessage.PLAYER_IS_IN_TEAM
                    .replace("%team%", teamName)
                    .replace("%player%", player.getName()));
        }
    }

    /** Ajoute un joueur à une équipe */

    public static void openTeamGUI(Player player) {
        player.openInventory(teamGui);
    }

    /** Liste toutes les équipes */

    public static void listAllTeams(Player player) {
        if (Team.teamsList.isEmpty()) {
            player.sendMessage(CustomMessage.NO_TEAM_IN_LIST);
        } else {
            StringBuilder teamsList = new StringBuilder("Teams List:\n");
            Team.teamsList.forEach((name, team) -> teamsList.append(name).append(" - Members: ").append(team.getTeamMembers().size()).append("\n"));
            player.sendMessage(teamsList.toString());
        }
    }
}
