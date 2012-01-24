package info.somethingodd.bukkit.OddGive;

import info.somethingodd.bukkit.OddItem.OddItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class OddGivePlayerListener implements Listener {
    private OddGive oddGive = null;

    public OddGivePlayerListener(OddGive oddGive) {
        this.oddGive = oddGive;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        ItemStack itemStack;
        try {
            itemStack = OddItem.getItemStack(event.getPlayer().getName());
            if (itemStack != null)
                oddGive.log.warning("Joining player with name " + event.getPlayer().getName() + " matches an OddItem alias! This may be confusing.");
            itemStack = OddItem.getItemStack(event.getPlayer().getDisplayName());
            if (itemStack != null)
                oddGive.log.warning("Joining player with displayname " + event.getPlayer().getDisplayName() + "matches an OddItem alias! This may be confusing.");
        } catch (IllegalArgumentException e) {
        }
        oddGive.calculate(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (oddGive.lists != null) oddGive.lists.remove(event.getPlayer());
    }
}
