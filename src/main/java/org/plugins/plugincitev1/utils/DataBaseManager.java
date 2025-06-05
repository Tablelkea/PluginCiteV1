package org.plugins.plugincitev1.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.plugins.plugincitev1.Main;
import org.plugins.plugincitev1.models.Teams;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static java.sql.DriverManager.getConnection;

public class DataBaseManager {

    /** Permet de sauvegarder dans la base de donées l'équipe d'un joueur */

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

    /** Permet de sauvegarder dans la base de donées une nouvelle catégorie */

    public static void saveNewCategory(String name, String description, String icon, int slot) {
        String sql = "INSERT INTO categories (Name, Description, Icon, Slot) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, icon);
            stmt.setInt(4, slot);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Permet de retirer de la base de donées une catégorie */

    public static void removeCategory(String name) {
        String sql = "DELETE FROM categories WHERE Name = ?";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Permet de sauvegarder dans la base de donées un nouvel item */

    public static void removeItem(String name) {
        String sql = "DELETE FROM minecraft_items WHERE Name = ?";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /** Permet de récupérer le prix d'un item depuis la base de données */

    public static int getItemPrice(String name) {
        String sql = "SELECT Price FROM minecraft_items WHERE Name = ?";
        int price = 0;

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                price = rs.getInt("Price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    /** Permet de récupérer le slot d'un item depuis la base de données */

    public static int getItemSlot(String name) {
        String sql = "SELECT Slot FROM minecraft_items WHERE Name = ?";
        int slot = 0;

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                slot = rs.getInt("Slot");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slot;
    }

    /** Permet de mettre à jour le prix d'un item dans la base de données */

    public static void updateItemPrice(String name, int newPrice) {
        String sql = "UPDATE minecraft_items SET Price = ? WHERE Name = ?";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setInt(1, newPrice);
            stmt.setString(2, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Permet de sauvegarder dans la base de donées un nouvel item */

    public static void saveNewItem(String name, String description, int price, String icon, int slot, String category) {
        String sql = "INSERT INTO minecraft_items (Name, Description, Price, Icon, Slot) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setInt(3, price);
            stmt.setString(4, icon);
            stmt.setInt(5, slot);
            stmt.setString(6, category);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Permet de retirer de la base de donées l'équipe d'un joueur */

    public static void removePlayerFromTeam(UUID playerUUID) {
        String sql = "DELETE FROM teams WHERE PlayerUniqueID = ?";

        try (PreparedStatement stmt = Main.connection.prepareStatement(sql)) {
            stmt.setString(1, playerUUID.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Permet de récupérer l'équipe d'un joueur depuis la base de données */

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

    /** Permet de fermer la connexion à la base de données */

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
        for(Player player : Bukkit.getOnlinePlayers()){
            Teams.loadTeamsFromDatabase(player);
        }
    }
}
