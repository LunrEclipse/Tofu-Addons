package me.tofumc.tofumcaddon;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class TofuMCAddon extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        NamespacedKey categoryID = new NamespacedKey(this, "TofuMC");
        CustomItem categoryItem = new CustomItem(Material.QUARTZ_BLOCK, "&4Custom Items for TofuMC");

        Category category = new Category(categoryID, categoryItem);

        ItemStack[] NORECIPE = {null, null, null, null, null, null, null, null, null};

        //Bit
        SlimefunItemStack bitStack = new SlimefunItemStack("TOFUMC_BIT", Material.GHAST_TEAR, "§fBit", "");
        ItemStack[] bitRecipe = {
                getItem("TOFU_CHUNK"), null, null,
                null, null, null,
                null, null, null
        };
        SlimefunItem sfBit = new SlimefunItem(category, bitStack, RecipeType.ENHANCED_CRAFTING_TABLE, bitRecipe);
        sfBit.register(this);

        //Chunk
        SlimefunItemStack chunkStack = new SlimefunItemStack("TOFUMC_CHUNK", Material.WHITE_CONCRETE, "§fChunk", "");
        ItemStack[] chunkRecipe = {
                getItem("TOFU_BIT"), getItem("TOFU_BIT"), getItem("TOFU_BIT"),
                getItem("TOFU_BIT"), getItem("TOFU_BIT"), getItem("TOFU_BIT"),
                getItem("TOFU_BIT"), getItem("TOFU_BIT"), getItem("TOFU_BIT")
        };
        SlimefunItem sfChunk = new SlimefunItem(category, chunkStack, RecipeType.ENHANCED_CRAFTING_TABLE, chunkRecipe);
        sfChunk.register(this);

        //Crafting Recipes



    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

    private static ItemStack getItem(String id) {
        SlimefunItem item = SlimefunItem.getByID(id);
        return item != null ? item.getItem() : null;
    }

}
