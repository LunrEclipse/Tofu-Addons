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
        SlimefunItem sfBit = new SlimefunItem(category, bitStack, RecipeType.ENHANCED_CRAFTING_TABLE, NORECIPE);

        //Chunk
        SlimefunItemStack chunkStack = new SlimefunItemStack("TOFUMC_CHUNK", Material.WHITE_CONCRETE, "§fChunk", "");
        SlimefunItem sfChunk = new SlimefunItem(category, chunkStack, RecipeType.COMPRESSOR, NORECIPE);

        //Crafting Recipes

        ItemStack[] bitToChunk = {
                new SlimefunItemStack(bitStack, 64), null, null,
                null, null, null,
                null, null, null
        };

        sfChunk.setRecipe(bitToChunk);

        ItemStack[] chunkToBit = {
                new SlimefunItemStack(chunkStack, 1), null, null,
                null, null, null,
                null, null, null
        };
        
        sfBit.setRecipe(chunkToBit);
        sfBit.setRecipeOutput(new SlimefunItemStack(bitStack, 64));

        ItemStack[] bitToGold = {
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1),
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1),
                null, null, null
        };

        ItemStack[] bitToIron = {
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null,
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null,
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null
        };


        ItemStack[] bitToCoal = {
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null,
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null,
                null, null, null
        };


        //Registers

        sfChunk.register(this);
        sfBit.register(this);
        RecipeType.ENHANCED_CRAFTING_TABLE.register(bitToGold, new ItemStack(Material.GOLD_ORE, 6));
        RecipeType.ENHANCED_CRAFTING_TABLE.register(bitToIron, new ItemStack(Material.IRON_ORE, 12));
        RecipeType.ENHANCED_CRAFTING_TABLE.register(bitToCoal, new ItemStack(Material.COAL, 16));

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

}
