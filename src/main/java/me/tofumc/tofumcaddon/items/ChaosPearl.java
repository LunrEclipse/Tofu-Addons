package me.tofumc.tofumcaddon.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;
import org.bukkit.potion.PotionEffect;

public class ChaosPearl extends SlimefunItem {
    public ChaosPearl(Category category, SlimefunItemStack item, RecipeType recipeType,ItemStack[] recipe)
    {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {

        ItemUseHandler itemUseHandler = this::onItemUseRightClick;
        addItemHandler(itemUseHandler);


    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {
        event.cancel();
        Player player = event.getPlayer();
        PlayerInventory pi = player.getInventory();
        if(player.getItemInHand().getAmount() == 1)
        {
            pi.setItem(pi.getHeldItemSlot(), new ItemStack(Material.AIR, 0));
        }
        else
        {
            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
        }
        int x = player.getLocation().getBlockX() - ( (int)(Math.random() * 2000) - 1000 );
        int z = player.getLocation().getBlockZ() - ( (int)(Math.random() * 2000) - 1000 );
        int y = player.getLocation().getBlockY();
        player.teleport(new Location(player.getWorld(),x, y, z));
    }


}
