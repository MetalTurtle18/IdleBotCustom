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

package io.github.camshaft54.idlebot.util.enums;

@SuppressWarnings("SpellCheckingInspection")
public enum DataValues {
    AFK_TIME("afktime"),
    AUTO_AFK("autoafk"),
    DISCORD_ID("discordid"),
    IS_SET_AFK("setafk"),
    DAMAGE_ALERT("damagealert"),
    DEATH_ALERT("deathalert"),
    LOCATION_ALERT_X("locationcheckx"),
    LOCATION_ALERT_Z("locationcheckz"),
    LOCATION_X_DESIRED("locationx"),
    LOCATION_Z_DESIRED("locationz"),
    LOCATION_X_DIRECTION("locationxdir"), // e/w; example: player is at x = 100, wants alert x = 500, value = "e"
    LOCATION_Z_DIRECTION("locationzdir"), // s/n; example: player is at z = 100, wants alert z = -500, value = "n"
    EXPERIENCE_ALERT("xpalert"),
    EXPERIENCE_LEVEL_DESIRED("xpleveldesired"),
    INVENTORY_FULL_ALERT("inventoryalert"),
    DIRECT_MESSAGE_MODE("dmmode");

    private final String key;

    DataValues(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
