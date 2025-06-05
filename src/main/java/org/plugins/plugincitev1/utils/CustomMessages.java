package org.plugins.plugincitev1.utils;

public interface CustomMessages {

    /** Liste tous les messages customisés */

    String prefix = "§7[§6§lCité§7] §r";
    String globalPrefix = "§7[§c§lCité§7] §3§l";
    String teamsSelectionLocked = prefix + "§cTeams selection is locked!";
    String globalTeamsSelectionLocked = globalPrefix + "Teams selection is now locked !";
    String wrongTeamName = prefix + "§cWrong team name! Please use §6/teams <red|blue|green|yellow>§c.";

    String joinedRedTeam = prefix + "§cYou have joined the Red Team!";
    String joinedBlueTeam = prefix + "§9You have joined the Blue Team!";
    String joinedGreenTeam = prefix + "§2You have joined the Green Team!";
    String joinedYellowTeam = prefix + "§eYou have joined the Yellow Team!";

    String teamLeaveMessage = prefix + "§7You have left the team!";

    String alreadyInTeam = prefix + "§cYou are already in a team! Please leave your current team before joining another one.";
    String noTeamToLeave = prefix + "§cYou are not in any team!";

    String addedNewCategory = globalPrefix + "§aYou have added a new category!";
    String removedCategory = globalPrefix + "§cYou have removed a category!";
    String addedNewItem = globalPrefix + "§aYou have added a new item!";
    String removedItem = globalPrefix + "§cYou have removed an item!";

    String itemDescription = "§6✦ §7price: §a%price% flocons§6\n \n▶ §7click to buy this item!";
    String succesPriceChange = globalPrefix + "§aYou have successfully changed the price of the item!";
    String itemNotFound = globalPrefix + "§cItem not found! Please check the item name and try again.";
    String teamsCommandHelp = globalPrefix + "§7Teams command help:\n" +
            "§6/teams §7- Open the teams GUI\n" +
            "§6/teams end §7- Lock the teams selection\n" +
            "§6/teams open §7- Open the teams selection\n" +
            "§6/teams look <red|blue|green|yellow> §7- List players in a specific team\n" +
            "§6/teams check <player> §7- Check a player's team\n" +
            "§6/teams add <team> <player> §7- Force a player to join a team";
    String wrongCommandUsage = globalPrefix + "§cWrong command usage! Please use the correct syntax. (/teams help)";
    String joinMessage = "§8[§2+§8] §7%player%";
}
