package me.tofumc.tofumcaddon;

import io.github.thebusybiscuit.slimefun4.core.handlers.*;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MobIncapacitator extends SlimefunItem {
    public MobIncapacitator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe)
    {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {

        addItemHandler(onBlockPlace());

    }


    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(true)
        {
            @Override
            public void onPlayerPlace(BlockPlaceEvent event) {
                incapacitate(event, event.getBlock().toString());
            }
        };
    }

    private void incapacitate(BlockPlaceEvent event, String originalBlock)
    {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Block block = event.getBlock();
                if(block.toString().equals(originalBlock)) {
                    for (Entity en : block.getWorld().getEntities()) {
                        if (en instanceof Mob) {
                            Mob mob = (Mob) en;
                            double distance = mob.getLocation().distance(block.getLocation());
                            if (distance <= 5) {
                                if (mob.getMaxHealth() <= 20) {
                                    mob.setHealth(1);
                                }
                            }
                        }
                    }
                    incapacitate(event, originalBlock);
                    t.cancel();
                }
            }
        }, 5000);

    }



}
