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

import lombok.Getter;

@SuppressWarnings("SpellCheckingInspection")
public enum CommandTabCompletion {
    AFKMODE(new String[]{"auto", "manual"}),
    AFKTIME(),
    ALERT(new String[]{"damage", "death", "xlocation", "zlocation", "xp", "inventory"}, new String[]{"true", "false"}),
    CHANNEL(new String[]{"public", "private"}),
    CLEARDATA(),
    LINK(),
    LOCATION(new String[]{"x", "z"}),
    AFK(new String[]{"true", "false"}),
    UNLINK(),
    INFO(),
    XPLEVEL();

    @Getter final String[][] args;

    CommandTabCompletion(String[]... args) {
        this.args = args;
    }

    public static CommandTabCompletion get(String s) {
        for (CommandTabCompletion command : CommandTabCompletion.values()) {
            if (command.name().equalsIgnoreCase(s)) {
                return command;
            }
        }
        return null;
    }
}
