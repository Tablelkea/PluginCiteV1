package org.plugins.plugincitev1.models;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class Team {

    public static HashMap<String, Team> teamsList = new HashMap<>();

    String teamName;
    ItemStack teamIcon;
    List<Player> teamMembers;

    public Team(String teamName, ItemStack teamIcon, List<Player> teamMembers) {
        this.teamName = teamName;
        this.teamIcon = teamIcon;
        this.teamMembers = teamMembers;
        teamsList.put(teamName, this);
    }

    public ItemStack getTeamIcon() {
        return teamIcon;
    }

    public List<Player> getTeamMembers() {
        return teamMembers;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addPlayer(Player player){
        if (!teamMembers.contains(player)) {
            teamMembers.add(player);
        }
    }

    public void removePlayer(Player player) {
        teamMembers.remove(player);
    }

    public static Team getTeamByName(String teamName) {
        return teamsList.get(teamName);
    }
}
