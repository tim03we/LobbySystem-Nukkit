package tim03we.lobbysystem;

/*
 * Copyright (c) 2019 tim03we  < https://github.com/tim03we >
 * Discord: tim03we | TP#9129
 *
 * This software is distributed under "GNU General Public License v3.0".
 * This license allows you to use it and/or modify it but you are not at
 * all allowed to sell this plugin at any cost. If found doing so the
 * necessary action required would be taken.
 *
 * LobbySystem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License v3.0 for more details.
 *
 * You should have received a copy of the GNU General Public License v3.0
 * along with this program. If not, see
 * <https://opensource.org/licenses/GPL-3.0>.
 */

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class LobbySystem extends PluginBase {

    @Override
    public void onEnable() {
        saveResource("settings.yml");
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    public Config settings() {
        return new Config(getDataFolder() + "/settings.yml", Config.YAML);
    }

    public void giveItems(Player player, String explode)
    {
        player.getInventory().clearAll();
        String[] get = explode.split("-");
        player.getInventory().setItem(Integer.parseInt(get[3]), Item.get(Integer.parseInt(get[0]), Integer.parseInt(get[1]), Integer.parseInt(get[2])).setCustomName(get[4]));
    }
}
