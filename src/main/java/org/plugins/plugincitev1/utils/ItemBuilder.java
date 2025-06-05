package org.plugins.plugincitev1.utils;


import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    // CODE ISSU DU DU SERVEUR ELENOX

    /** Fonction permettant de créer un objet customisé */

    private ItemStack item;
    private ItemMeta itemM;

    /** Constructeur pour créer un ItemBuilder avec un Material, une quantité, un nom et une liste de lore */

    public ItemBuilder(Material material, int amont, String displayname, List<String> lore) {
        this.item = new ItemStack(material, amont);
        this.itemM = this.item.getItemMeta();
        this.itemM.setDisplayName(displayname);
        this.itemM.setLore(lore);
        this.item.setItemMeta(this.itemM);
    }

    /** Constructeur pour créer un ItemBuilder avec un ItemStack, une quantité, un nom et une liste de lore */

    public ItemBuilder(ItemStack itemStack, int amont, String displayname, List<String> lore) {
        this.item = itemStack;
        this.item.setAmount(amont);
        this.itemM = this.item.getItemMeta();
        this.itemM.setDisplayName(displayname);
        this.itemM.setLore(lore);
        this.item.setItemMeta(this.itemM);
    }

    /** Constructeur pour créer un ItemBuilder avec un Material, une quantité, un nom et une liste de lore en utilisant Component */

    public ItemBuilder(Material material, int amont, Component displayname, List<Component> lore) {
        this.item = new ItemStack(material, amont);
        this.itemM = this.item.getItemMeta();
        this.itemM.displayName(displayname);
        this.itemM.lore(lore);
        this.item.setItemMeta(this.itemM);
    }

    /** Constructeur pour créer un ItemBuilder avec un ItemStack, une quantité, un nom et une liste de lore en utilisant Component */

    public ItemBuilder(ItemStack itemStack, int amont, Component displayname, List<Component> lore) {
        this.item = itemStack;
        this.item.setAmount(amont);
        this.itemM = this.item.getItemMeta();
        this.itemM.displayName(displayname);
        this.itemM.lore(lore);
        this.item.setItemMeta(this.itemM);
    }

    /** Constructeur pour créer un ItemBuilder avec un Material, une quantité et un nom */

    public ItemBuilder(Material material, int amont, String displayname) {
        this((Material)material, amont, (String)displayname, (List)null);
    }

    /** methode pour recuperer l'ItemStack  */

    public ItemStack getItem() {
        return item;
    }

    /** methode pour definir l'ItemStack */

    public void setItem(ItemStack item) {
        this.item = item;
    }

    /** methode pour recuperer l'ItemMeta */

    public ItemMeta getItemM() {
        return itemM;
    }

    /** methode pour definir l'ItemMeta */

    public void setItemM(ItemMeta itemM) {
        this.itemM = itemM;
    }

    /** methode pour definir l'ItemStack */

    public ItemStack toItemStack() {
        return this.item;
    }

}
