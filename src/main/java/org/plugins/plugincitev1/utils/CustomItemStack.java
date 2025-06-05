package org.plugins.plugincitev1.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface CustomItemStack {

    /** Liste tous les objets customisés */

    ItemStack GLASS_PANE = new ItemBuilder(
            org.bukkit.Material.GRAY_STAINED_GLASS_PANE, 1,
            " ", null).toItemStack();
    ItemStack RED_TEAM = new ItemBuilder(
            org.bukkit.Material.RED_BANNER, 1,
            "§4§lRed Team", List.of("§cJoin the red team!")).toItemStack();
    ItemStack BLUE_TEAM = new ItemBuilder(
            org.bukkit.Material.BLUE_BANNER, 1,
            "§9§lBlue Team", List.of("§3Join the blue team!")).toItemStack();
    ItemStack GREEN_TEAM = new ItemBuilder(
            org.bukkit.Material.GREEN_BANNER, 1,
            "§2§lGreen Team", List.of("§aJoin the green team!")).toItemStack();
    ItemStack YELLOW_TEAM = new ItemBuilder(
            org.bukkit.Material.YELLOW_BANNER, 1,
            "§6§lYellow Team", List.of("§eJoin the yellow team!")).toItemStack();
    ItemStack LEAVE_TEAM = new ItemBuilder(
            Material.STRUCTURE_VOID, 1,
            "§c§lLeave Team", List.of("§4Leave your current team!")).toItemStack();
}
