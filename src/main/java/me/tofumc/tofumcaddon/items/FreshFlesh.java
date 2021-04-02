package me.tofumc.tofumcaddon.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
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
import org.bukkit.potion.PotionEffectType;

import java.util.Timer;
import java.util.TimerTask;

public class FreshFlesh extends SlimefunItem {
    public FreshFlesh(Category category, SlimefunItemStack item, RecipeType recipeType,ItemStack[] recipe)
    {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {

        ItemConsumptionHandler itemConsumptionHandler = this::onItemConsumption;
        addItemHandler(itemConsumptionHandler);


    }

    private void onItemConsumption(PlayerItemConsumeEvent event, Player player, ItemStack itemStack) {
        float saturation = player.getSaturation() - 10;
        player.setFoodLevel(20);
        player.setSaturation(saturation);

    }


}
