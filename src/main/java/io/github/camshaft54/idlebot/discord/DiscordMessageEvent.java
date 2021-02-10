/*
 *    Copyright (C) 2020-2021 Camshaft54, MetalTurtle18
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.camshaft54.idlebot.discord;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.github.camshaft54.idlebot.IdleBot;
import io.github.camshaft54.idlebot.util.Messenger;
import io.github.camshaft54.idlebot.util.PersistentDataHandler;
import io.github.camshaft54.idlebot.util.enums.DataValues;
import io.github.camshaft54.idlebot.util.enums.MessageLevel;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class DiscordMessageEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Linking
        if (event.getChannelType() == ChannelType.PRIVATE && !event.getAuthor().isBot()) {
            Message message = event.getMessage();
            MessageChannel channel = event.getChannel();
            try {
                int code = Integer.parseInt(message.getContentRaw());
                Messenger.sendMessage(event.getAuthor().getAsTag() + " entered a code: " + code, MessageLevel.INFO);
                if (IdleBot.linkCodes.containsKey(code)) {
                    Player player = IdleBot.linkCodes.get(code);
                    PersistentDataHandler.setData(player, DataValues.DISCORD_ID.key(), event.getAuthor().getId());
                    channel.sendMessage("Successfully linked your Discord username to Minecraft username " + player.getDisplayName()).queue();
                    message.addReaction("U+1F517").queue();
                    Messenger.sendMessage(player, "Successfully linked your Minecraft username to Discord username " + event.getAuthor().getAsTag(), MessageLevel.INFO);
                    IdleBot.linkCodes.remove(code);
                    // Since the player just linked,
                    setDefaultSettings(player);
                } else {
                    channel.sendMessage("Invalid Code. To get code type `/idlebot link` in Minecraft").queue();
                }
            } catch (NumberFormatException nfe) {
                channel.sendMessage("Invalid Code. To get code type `/idlebot link` in Minecraft").queue();
                nfe.printStackTrace();
            }
        }

        // Whitelisting
//        if (event.getChannel().equals(DiscordAPIManager.whitelistChannel)) {
//            Message message = event.getMessage();
//            MessageChannel channel = event.getChannel();
//            if (message.getContentRaw().length() >= 3 && message.getContentRaw().trim().startsWith("!w ")) {
//                String playerName = message.getContentRaw().trim().substring(3);
//                String playerUUID = null;
//                try {
//                    URL mojangAPIURL = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
//                    URLConnection mojangAPIURLConnection = mojangAPIURL.openConnection();
//                    mojangAPIURLConnection.connect();
//                    JsonParser jp = new JsonParser();
//                    JsonObject jsonObject = jp.parse(new InputStreamReader((InputStream) mojangAPIURLConnection.getContent())).getAsJsonObject();
//                    playerUUID = jsonObject.get("id").getAsString();
//                    System.out.println(playerUUID);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                if (playerUUID == null) {
//                    channel.sendMessage("Can't find that player").queue();
//                } else {
//                    OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(playerUUID));
//                    if (player != null) {
//                        player.setWhitelisted(true);
//                        channel.sendMessage("Whitelisted " + player.getName()).queue();
//                        message.addReaction("U+2705").queue();
//                        Messenger.sendMessage("Whitelisted player " + player.getName() + " from Discord", MessageLevel.INFO);
//                    } else {
//                        channel.sendMessage("Can't find that player").queue();
//                    }
//                }
//            }
//        }
    }

    // This method is to set up default values for every player when they link their account
    private void setDefaultSettings(Player player) {
        PersistentDataHandler.setData(player, DataValues.AFK_TIME.key(), IdleBot.getConfigManager().DEFAULT_IDLE_TIME);
        PersistentDataHandler.setData(player, DataValues.AUTO_AFK.key(), true);
    }
}
