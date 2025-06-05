package org.plugins.plugincitev1.models;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.plugins.plugincitev1.Main;
import org.plugins.plugincitev1.utils.CustomMessages;

public class Market {

    public static Inventory gui = Bukkit.createInventory(null, 54, CustomMessages.marketTitle);
    public static Location marketLocation;
    public static Boolean marketIsDeployed = false;

    /** Permet de créer le marché et de l'ouvrir pour le joueur */

    public static void createMarket(Player player) {
        if (marketIsDeployed) {
            player.sendMessage(CustomMessages.marketActuallyDeployMessage);
            return;
        }
        player.sendMessage(CustomMessages.marketCreationMessage);
        player.openInventory(gui);
    }

    /** Permet d'ouvrir le marché pour un joueur spécifique */

    public static void openMarket(Player player) {
        if(player.isOp()){
            player.openInventory(gui);
        }else{
            if(marketIsDeployed) {
                player.openInventory(gui);
            } else {
                player.sendMessage(CustomMessages.marketNotDeployMessage);
            }
        }
    }

    /** Permet de définir l'emplacement du marché */

    public static void loadMarketLocation(){
        FileConfiguration config = Main.instance.getConfig();

        String worldName = config.getString("market-location.world");
        int x = config.getInt("market-location.x");
        int y = config.getInt("market-location.y");
        int z = config.getInt("market-location.z");

        marketLocation = new Location(Bukkit.getWorld(worldName), x, y, z);
    }

    public static void deployMarket(Player player){
        if(!marketIsDeployed){
            player.sendMessage(CustomMessages.marketAlreadyDeployMessage);
        }else{
            marketLocation.getBlock().setType(Material.CHEST);
            Bukkit.broadcastMessage(CustomMessages.marketDeployMessage);
        }
    }

    /** Permet d'ajouter un objet au marché */

    public static void addItemToMarket(ItemStack itemStack, Player player) {
        gui.addItem(itemStack);
        player.sendMessage(CustomMessages.addedItemToMarketMessage.replace("{item}", itemStack.getType().toString()));
    }

    /** Permet de retirer un objet du marché */

    public static void removeItemFromMarket(String itemType, Player player){
        gui.remove(Material.valueOf(itemType));
        player.sendMessage(CustomMessages.removedItemFromMarketMessage.replace("{item}", itemType));
    }

    /** Permet de vérifier si le marché est déployé */

    public static void checkMarketStatus(Player player) {
        if (marketIsDeployed) {
            player.sendMessage(CustomMessages.marketDeployMessage);
        } else {
            player.sendMessage(CustomMessages.marketNotDeployMessage);
        }
    }

    /** Permet de retirer le marché */

    public static void removeMarket(Player player) {
        if (marketIsDeployed) {
            marketLocation.getBlock().setType(Material.AIR);
            marketIsDeployed = false;
            player.sendMessage(CustomMessages.marketDeployMessage);
        } else {
            player.sendMessage(CustomMessages.marketNotDeployMessage);
        }
    }
}
