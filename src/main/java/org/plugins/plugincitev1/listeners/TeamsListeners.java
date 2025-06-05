package org.plugins.plugincitev1.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.plugins.plugincitev1.models.Teams;
import org.plugins.plugincitev1.utils.CustomMessages;
import org.plugins.plugincitev1.utils.DataBaseManager;

public class TeamsListeners implements Listener {

    /** Permet de vérifier le clic dans un inventaire spécifique et de réaliser des actions en fonctions */

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        Inventory clickedInventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();

        if(clickedInventory == Teams.gui){

            Material clickedItem = event.getCurrentItem() != null ? event.getCurrentItem().getType() : null;

            event.setCancelled(true);

            if (clickedItem == Material.STRUCTURE_VOID) {
                Teams.leaveTeam(player); // Ignore clicks on empty slots
                DataBaseManager.removePlayerFromTeam(player.getUniqueId());
            }

            if (Teams.redTeam.contains(player) || Teams.blueTeam.contains(player) || Teams.greenTeam.contains(player) || Teams.yellowTeam.contains(player)) {
                player.sendMessage(CustomMessages.alreadyInTeam);
                return;
            }

            switch (clickedItem) {
                case RED_BANNER:
                    Teams.redTeam.add(player);
                    DataBaseManager.savePlayerToTeam(player.getUniqueId(), "red");
                    player.sendMessage(CustomMessages.joinedRedTeam);
                    break;
                case BLUE_BANNER:
                    Teams.blueTeam.add(player);
                    DataBaseManager.savePlayerToTeam(player.getUniqueId(), "blue");
                    player.sendMessage(CustomMessages.joinedBlueTeam);
                    break;
                case GREEN_BANNER:
                    Teams.greenTeam.add(player);
                    DataBaseManager.savePlayerToTeam(player.getUniqueId(), "green");
                    player.sendMessage(CustomMessages.joinedGreenTeam);
                    break;
                case YELLOW_BANNER:
                    Teams.yellowTeam.add(player);
                    DataBaseManager.savePlayerToTeam(player.getUniqueId(), "yellow");
                    player.sendMessage(CustomMessages.joinedYellowTeam);
                    break;
                case null:
                    break; // Ignore clicks on empty slots
                default:
                    break;
            }

        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Teams.loadTeamsFromDatabase(player);
        event.joinMessage(Component.text(CustomMessages.joinMessage.replace("%player%", player.getName())));
    }

}
