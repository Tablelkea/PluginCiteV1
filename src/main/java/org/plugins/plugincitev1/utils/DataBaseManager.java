package org.plugins.plugincitev1.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.plugins.plugincitev1.Main;
import org.plugins.plugincitev1.models.Teams;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DataBaseManager {

    /**
     * Permet de sauvegarder dans la base de donées l'équipe d'un joueur
     */

    public static void savePlayerToTeam(UUID playerUUID, String teamId) {
        String sql = "INSERT INTO teams (PlayerUniqueID, Team, Date) VALUES (?, ?, CURDATE())";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, playerUUID.toString());
            stmt.setString(2, teamId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de retirer de la base de donées l'équipe d'un joueur
     */

    public static void removePlayerFromTeam(UUID playerUUID) {
        String sql = "DELETE FROM teams WHERE PlayerUniqueID = ?";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, playerUUID.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de récupérer l'équipe d'un joueur depuis la base de données
     */

    public static String getTeamOfPlayer(UUID playerUUID) {
        String sql = "SELECT Team FROM teams WHERE PlayerUniqueID = ?";
        String team = null;

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, playerUUID.toString());
            var rs = stmt.executeQuery();
            if (rs.next()) {
                team = rs.getString("Team");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return team;
    }

    /**
     * Permet de fermer la connexion à la base de données
     */

    public static void disconnectFromDatabase() {
        try {
            if (Main.connection != null && !Main.connection.isClosed()) {
                Main.connection.close();
                Main.instance.getLogger().info("Connexion MySQL fermée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loopOnlinePlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Teams.loadTeamsFromDatabase(player);
        }
    }

    public static void addItemToMarket(ItemStack itemStack) {
        String sql = "INSERT INTO market_items (Name, Description, Type, Price, QuantityToSell) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setObject(1, itemStack);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeItemFromMarket(String itemType) {
        String sql = "DELETE FROM market_items WHERE Type = ?";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, itemType);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadMarketItems(Inventory market) {
        String sql = "SELECT * FROM market_items";
        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                double price = rs.getDouble("Price");
                int quantityToSell = rs.getInt("QuantityToSell");

                market.addItem(new ItemBuilder(Material.valueOf(type), 1, name, List.of(description)).toItemStack());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Material[] getMarketItems() {
        String sql = "SELECT Type FROM market_items";
        Material[] items = new Material[0];

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("Type");
                items = appendToArray(items, Material.valueOf(type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    private static Material[] appendToArray(Material[] items, Material material) {
        Material[] newArray = new Material[items.length + 1];
        System.arraycopy(items, 0, newArray, 0, items.length);
        newArray[items.length] = material;
        return newArray;
    }
}
