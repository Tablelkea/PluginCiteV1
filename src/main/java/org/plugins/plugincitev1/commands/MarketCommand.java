package org.plugins.plugincitev1.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.plugins.plugincitev1.models.Market;
import org.plugins.plugincitev1.utils.DataBaseManager;
import org.plugins.plugincitev1.utils.ItemBuilder;

import java.util.List;


public class MarketCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if(sender instanceof Player player){

            if(player.isOp()){ // Si le joueur est op alors...

                if(args.length == 0) {
                    Market.openMarket(player);
                }
                switch (args[0].toLowerCase()) {
                    case "create":
                        Market.createMarket(player);
                        break;
                    case "deploy":
                        Market.deployMarket(player);
                        break;
                    case  "additem":
                        if(args.length < 5){
                            Material material = Material.getMaterial(args[1].toUpperCase());
                            int price = Integer.parseInt(args[2]);
                            int quantity = Integer.parseInt(args[3]);
                            ItemStack newItem = new ItemBuilder(material, 1, material.name(), List.of("§7Price: §a" + price + " flocons", "§7Quantity: §a" + quantity)).toItemStack();
                            DataBaseManager.addItemToMarket(newItem);
                            Market.addItemToMarket(newItem, player);
                        }
                        break;
                    case "removeitem":
                        if(args.length < 3){
                            String materialName = args[1].toUpperCase();
                            DataBaseManager.removeItemFromMarket(materialName);
                            Market.removeItemFromMarket(materialName, player);
                        }
                        break;
                    case "checkstatus":
                        Market.checkMarketStatus(player);
                        break;
                    case "removemarket":
                        Market.removeMarket(player);
                        break;
                    default:
                        player.sendMessage("§cUsage: /market <create|open|setlocation|deploy>");
                        break;
                }

            }

        }

        return false;
    }

    @Override
    public @NotNull java.util.List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return List.of("create", "open", "setlocation", "deploy", "additem", "removeitem");
        }
        if (args.length >= 2 && args[0].equalsIgnoreCase("additem")) {
            if(args.length == 3){
                for(Material material : Material.values()) {
                    if (material.isItem()) {
                        return List.of(material.name());
                    }
                }
            }
        }else if (args.length >= 2 && args[0].equalsIgnoreCase("removeitem")) {
            for(Material material : DataBaseManager.getMarketItems()){
                return List.of(material.name());
            }
        }


        return List.of();
    }
}
