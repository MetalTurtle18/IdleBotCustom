package io.github.camshaft54.idlebot.commands.idlebotsubcommands;

import io.github.camshaft54.idlebot.IdleBot;
import io.github.camshaft54.idlebot.PersistentDataHandler;
import io.github.camshaft54.idlebot.commands.IdleBotCommand;
import org.bukkit.entity.Player;

public class SettingsCommand extends IdleBotCommand {

    @Override
    public String getCommandName() {
        return "settings";
    }

    @Override
    public void runCommand(Player player, String[] args) {
        switch (args[1]) {
            case "afkmode":
                if (args[2].equalsIgnoreCase("manual")) {
                    PersistentDataHandler.setData(player, "afkmode", "manual");
                }
                else if (args[2].equalsIgnoreCase("auto")) {
                    try {
                        if (Integer.parseInt(args[3]) >= 10 && Integer.parseInt(args[3]) <= 120) {
                            PersistentDataHandler.setData(player, "afkmode", "auto");
                            PersistentDataHandler.setData(player, "afktime", args[3]);
                        }
                        else {
                            // Say it has to be 10 seconds to 2 minutes
                            return;
                        }
                    }
                     catch (NumberFormatException nfe) {
                        // Send the player message about "you need a number 10 - 120"
                         return;
                     }
                }
                else {
                    // That isn't an option
                    return;
                }
                break;
            default:
                // Not a valid setting message
                break;

        }
    }
}
