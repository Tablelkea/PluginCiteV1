package org.plugins.plugincitev1.utils;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface CustomMessage {

    // TEAMS

    String PREFIX = "§6§l[§e§lCITE§6§l] §r";

    @NotNull Component TEAM_GUI_TITLE = Component.text(PREFIX + "§e§lTeams");
    @NotNull Component TEAM_LEAVE_CONFIRM_TITLE = Component.text(PREFIX + "§c§lLeave Team Confirmation");
    @NotNull Component TEAM_NOT_FOUND = Component.text(PREFIX + "§cTeam not found");

    @NotNull String LIST_TEAM_MEMBERS = PREFIX + "§7team §C%team% §7contain §6%size% §7players : §r!\n§7%players%";
    @NotNull String PLAYER_NOT_IN_TEAM = PREFIX + "§c%player% §cis not in a team!";
    @NotNull String PLAYER_IS_IN_TEAM = PREFIX + "§a%player% §7is in team: §r%team%";
    @NotNull String TARGET_PLAYER_NOT_FOUND = PREFIX + "§cPlayer §7%player% §cnot found!";
    @NotNull String KICK_PLAYER_FROM_TEAM_SUCCESS = PREFIX + "§aPlayer §7%player% §akicked from is team successfully!";
    @NotNull String FORCE_JOIN_TEAM_SUCCESS = PREFIX + "§aPlayer §7%player% §ajoined team §r%team% §asuccessfully!";
    @NotNull Component TARGET_NOT_SPECIFIED = Component.text(PREFIX + "§cPlease specify a target player.");
    @NotNull Component NO_TEAM_IN_LIST = Component.text(PREFIX + "§cNo teams found in the list.");
    @NotNull Component TEAM_HELP_MESSAGE = Component.text(PREFIX + "§cUsage: /teams <list/check/forceLeave/forceJoin>\n" +
            "§7- §e/list §7: List all teams.\n" +
            "§7- §e/list <team>§7: List all players in a specific team.\n" +
            "§7- §e/check <player>§7: Check player's team.\n" +
            "§7- §e/forceLeave <player>§7: Force a player to leave a team.\n" +
            "§7- §e/forceJoin <player> <team>§7: Force a player to join a team.");
}
