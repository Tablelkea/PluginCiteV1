package org.plugins.plugincitev1.models;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.plugins.plugincitev1.utils.CustomItemStack;
import org.plugins.plugincitev1.utils.CustomMessages;
import org.plugins.plugincitev1.utils.DataBaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Teams {

    public static Boolean teamsSelectionLocked = false;

    /** Listes des joueurs dans chaque équipe */

    public static List<Player> redTeam = new ArrayList<>();
    public static List<Player> blueTeam = new ArrayList<>();
    public static List<Player> greenTeam = new ArrayList<>();
    public static List<Player> yellowTeam = new ArrayList<>();
    public static int guiSize = 27;

    public static Inventory gui = Bukkit.createInventory(null, guiSize, Component.text("Teams"));

    /** Permet d'ouvrir le menu de séléction d'équipe à un joueur spécifique */

    public static void openTeamsGui(Player player){

        if(teamsSelectionLocked) {
            player.sendMessage(Component.text("§cTeams selection is locked!"));
            return;
        }

        for(int i = 0; i < guiSize; i++){
            gui.setItem(i, CustomItemStack.GLASS_PANE);
        }

        gui.setItem(10, CustomItemStack.RED_TEAM);
        gui.setItem(12, CustomItemStack.BLUE_TEAM);
        gui.setItem(14, CustomItemStack.GREEN_TEAM);
        gui.setItem(16, CustomItemStack.YELLOW_TEAM);
        gui.setItem(22, CustomItemStack.LEAVE_TEAM);

        player.openInventory(gui);
    }

    /** Permet de verrouiller la selection d'une équipe */

    public static void lockTeamsSelection() {
        teamsSelectionLocked = true;
        Bukkit.broadcast(Component.text(CustomMessages.globalTeamsSelectionLocked));
        System.out.println("Teams selection is now locked!");
    }

    /** Permet aux joueurs de choisir leur équipe */

    public static void openTeamsSelection() {
        teamsSelectionLocked = false;
        Bukkit.broadcast(Component.text(CustomMessages.globalPrefix + "Teams selection is now open!"));
        System.out.println("Teams selection is now open!");
    }

    /** Liste tous les membres de l'équipe ROUGE */

    public static void listRedTeam(Player player) {
        Bukkit.broadcast(Component.text("§cRed Team§8: §6" + redTeam.size() + "§7 players"));
        redTeam.forEach(p -> player.sendMessage(Component.text(p.getName())));
    }

    /** Liste tous les membres de l'équipe BLEU */

    public static void listBlueTeam(Player player) {
        Bukkit.broadcast(Component.text("§9Blue Team§8: §6" + blueTeam.size() + "§7 players"));
        blueTeam.forEach(p -> player.sendMessage(Component.text(p.getName())));
    }

    /** Liste tous les membres de l'équipe VERT */

    public static void listGreenTeam(Player player) {
        Bukkit.broadcast(Component.text("§2Green Team§8: §6" + greenTeam.size() + "§7 players"));
        greenTeam.forEach(p -> player.sendMessage(Component.text(p.getName())));
    }

    /** Liste tous les membres de l'équipe JAUNE */

    public static void listYellowTeam(Player player) {
        Bukkit.broadcast(Component.text("§eYellow Team§8: §6" + yellowTeam.size() + "§7 players"));
        yellowTeam.forEach(p -> player.sendMessage(Component.text(p.getName())));
    }

    /** Permet à un joueur administrateur (op) de verifier l'équipe d'un joueur spécifique */

    public static void checkPlayer(Player sender, Player target){
        if(redTeam.contains(target)) {
            sender.sendMessage(Component.text("§c" + target.getName() + " is in the Red Team!"));
        } else if(blueTeam.contains(target)) {
            sender.sendMessage(Component.text("§9" + target.getName() + " is in the Blue Team!"));
        } else if(greenTeam.contains(target)) {
            sender.sendMessage(Component.text("§2" + target.getName() + " is in the Green Team!"));
        } else if(yellowTeam.contains(target)) {
            sender.sendMessage(Component.text("§e" + target.getName() + " is in the Yellow Team!"));
        } else {
            sender.sendMessage(Component.text("§c" + target.getName() + " is not in any team!"));
        }
        sender.sendMessage(Component.text(CustomMessages.globalPrefix + "§7You have checked " + target.getName() + "'s team status."));
    }

    /** Permet à un joueur administrateur (op) de forcer un joueur spécifique à rejoindre une équipe */

    public static void forceJoinTeam(Player sender, Player target, String teamName) {
        if(teamsSelectionLocked) {
            sender.sendMessage(Component.text(CustomMessages.teamsSelectionLocked));
            return;
        }

        switch (teamName.toLowerCase()) {
            case "red":
                redTeam.add(target);
                sender.sendMessage(Component.text(CustomMessages.globalPrefix + "§7You have added " + target.getName() + " to the Red Team!"));
                target.sendMessage(Component.text(CustomMessages.joinedRedTeam));
                break;
            case "blue":
                blueTeam.add(target);
                sender.sendMessage(Component.text(CustomMessages.globalPrefix + "§7You have added " + target.getName() + " to the Blue Team!"));
                target.sendMessage(Component.text(CustomMessages.joinedBlueTeam));
                break;
            case "green":
                greenTeam.add(target);
                sender.sendMessage(Component.text(CustomMessages.globalPrefix + "§7You have added " + target.getName() + " to the Green Team!"));
                target.sendMessage(Component.text(CustomMessages.joinedGreenTeam));
                break;
            case "yellow":
                yellowTeam.add(target);
                sender.sendMessage(Component.text(CustomMessages.globalPrefix + "§7You have added " + target.getName() + " to the Yellow Team!"));
                target.sendMessage(Component.text(CustomMessages.joinedYellowTeam));
                break;
            default:
                sender.sendMessage(Component.text(CustomMessages.wrongTeamName));
        }
    }

    /** Permet à un joueur administrateur (op) de forcer un joueur spécifique à quitter une équipe */

    public static void forceLeaveTeam(Player sender, Player target, String teamName) {
        if(teamsSelectionLocked) {
            sender.sendMessage(Component.text(CustomMessages.teamsSelectionLocked));
            return;
        }

        switch (teamName.toLowerCase()) {
            case "red":
                redTeam.remove(target);
                sender.sendMessage(Component.text(CustomMessages.globalPrefix + "§7You have removed " + target.getName() + " from the Red Team!"));
                target.sendMessage(Component.text(CustomMessages.teamLeaveMessage));
                break;
            case "blue":
                blueTeam.remove(target);
                sender.sendMessage(Component.text(CustomMessages.globalPrefix + "§7You have removed " + target.getName() + " from the Blue Team!"));
                target.sendMessage(Component.text(CustomMessages.teamLeaveMessage));
                break;
            case "green":
                greenTeam.remove(target);
                sender.sendMessage(Component.text(CustomMessages.globalPrefix + "§7You have removed " + target.getName() + " from the Green Team!"));
                target.sendMessage(Component.text(CustomMessages.teamLeaveMessage));
                break;
            case "yellow":
                yellowTeam.remove(target);
                sender.sendMessage(Component.text(CustomMessages.globalPrefix + "§7You have removed " + target.getName() + " from the Yellow Team!"));
                target.sendMessage(Component.text(CustomMessages.teamLeaveMessage));
                break;
            default:
                sender.sendMessage(Component.text(CustomMessages.wrongTeamName));
        }
    }

    /** Permet à un joueur de quitter son équipe */

    public static void leaveTeam(Player player){
        if(redTeam.contains(player)) {
            redTeam.remove(player);
            player.sendMessage(Component.text(CustomMessages.teamLeaveMessage));
        } else if(blueTeam.contains(player)) {
            blueTeam.remove(player);
            player.sendMessage(Component.text(CustomMessages.teamLeaveMessage));
        } else if(greenTeam.contains(player)) {
            greenTeam.remove(player);
            player.sendMessage(Component.text(CustomMessages.teamLeaveMessage));
        } else if(yellowTeam.contains(player)) {
            yellowTeam.remove(player);
            player.sendMessage(Component.text(CustomMessages.teamLeaveMessage));
        } else {
            player.sendMessage(CustomMessages.noTeamToLeave);
        }
    }

    /** Permet de récupérer les données des équipes depuis la base de données */

    public static void loadTeamsFromDatabase(Player player){
        UUID playerUUID = player.getUniqueId();
        String team = DataBaseManager.getTeamOfPlayer(playerUUID);
        if (team != null) {
            switch (team.toLowerCase()) {
                case "red":
                    Teams.redTeam.add(player);
                    break;
                case "blue":
                    Teams.blueTeam.add(player);
                    break;
                case "green":
                    Teams.greenTeam.add(player);
                    break;
                case "yellow":
                    Teams.yellowTeam.add(player);
                    break;
                default:
                    // Handle unknown team
                    System.out.println("Unknown team for player " + player.getName() + ": " + team);
                    break;
            }
        }
    }

}
