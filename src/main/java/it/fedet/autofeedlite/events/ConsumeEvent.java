package it.fedet.autofeedlite.events;

import it.fedet.autofeedlite.utils.Foods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class ConsumeEvent implements Listener {
    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if (!item.getType().isEdible()) return;

        autoConsume(event.getPlayer(), item);
        event.setCancelled(true);
    }

    private void autoConsume(Player player, ItemStack item) {
        int playerFoodLevel = player.getFoodLevel();
        if (playerFoodLevel >= 20) return;

        ItemStack itemInHand = player.getItemInHand();
        int itemInHandAmount = itemInHand.getAmount();
        if (itemInHandAmount <= 0) return;

        player.setFoodLevel(playerFoodLevel + Foods.valueOf(item.getType().toString()).getSaturation());
        if ((itemInHandAmount - 1 > 0)) itemInHand.setAmount(itemInHandAmount - 1);
        else player.setItemInHand(null);

        autoConsume(player, item);
    }
}
