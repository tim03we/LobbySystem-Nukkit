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

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.event.inventory.InventoryTransactionEvent;
import cn.nukkit.event.player.*;

public class EventListener implements Listener {

    LobbySystem system;

    public EventListener(LobbySystem system)
    {
        this.system = system;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        event.getPlayer().setGamemode(system.settings().getInt("gamemode"));
        for (String item : system.settings().getStringList("items")) {
            system.giveItems(event.getPlayer(), item);
        }
        if (system.settings().getString("messages.join").equals("")) event.setJoinMessage("");
        else event.setJoinMessage(system.settings().getString("messages.join").replace("{player}", event.getPlayer().getName()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        if (system.settings().getString("messages.quit").equals("")) event.setQuitMessage("");
        else event.setQuitMessage(system.settings().getString("messages.quit").replace("{player}", event.getPlayer().getName()));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event)
    {
        if(system.settings().getBoolean("events.break")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event)
    {
        if(system.settings().getBoolean("events.place")) event.setCancelled(true);
    }

    @EventHandler
    public void onExhaust(PlayerFoodLevelChangeEvent event)
    {
        if(system.settings().getBoolean("events.hunger")) event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryMove(InventoryTransactionEvent event)
    {
        if(system.settings().getBoolean("events.inv-move")) event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event)
    {
        if(system.settings().getBoolean("events.drop")) event.setCancelled(true);
    }

    @EventHandler
    public void onPickUp(InventoryPickupItemEvent event)
    {
        if(system.settings().getBoolean("events.pick")) event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        int itemid = event.getItem().getId();
        int itemmeta = event.getItem().getDamage();
        String itemname = event.getItem().getName();
        for (String item : system.settings().getStringList("items")) {
            String[] get = item.split("-");
            if(itemid == Integer.parseInt(get[0]) && itemmeta == Integer.parseInt(get[1]) && itemname.equals(get[4])) system.getServer().dispatchCommand(event.getPlayer(), get[5].replace("{player}", event.getPlayer().getName()));
        }
    }
}
