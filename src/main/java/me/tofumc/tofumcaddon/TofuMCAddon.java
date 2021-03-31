package me.tofumc.tofumcaddon;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
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
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;

public class TofuMCAddon extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        NamespacedKey categoryID = new NamespacedKey(this, "TofuMC");
        CustomItem categoryItem = new CustomItem(SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWU1YWFmOGYxZjhjZTg0MTlhM2Y1ZWFmODNmMmE1MWY1YTRlNThkNTc2NjRjM2VkYzFkNjI5NGZkZmY2NjBkOSJ9fX0="), "&4TofuMC");

        Category category = new Category(categoryID, categoryItem);

        ItemStack[] NORECIPE = {null, null, null, null, null, null, null, null, null};

        //Bit
        SlimefunItemStack bitStack = new SlimefunItemStack("TOFUMC_BIT", Material.GHAST_TEAR, "§fBit", "", "&7Basic Currency");
        SlimefunItem sfBit = new SlimefunItem(category, bitStack, RecipeType.GRIND_STONE, NORECIPE);

        //Chunk
        SlimefunItemStack chunkStack = new SlimefunItemStack("TOFUMC_CHUNK", Material.WHITE_CONCRETE, "§6Chunk", "", "&7Advanced Currency");
        SlimefunItem sfChunk = new SlimefunItem(category, chunkStack, RecipeType.COMPRESSOR, NORECIPE);

        //Antidote
        SlimefunItemStack antidoteStack = new SlimefunItemStack("TOFU_ANTIDOTE", Material.HONEY_BOTTLE, "§fAntidote", "", "&7Removes All Status Effects");
        Antidote sfAntidote = new Antidote(category, antidoteStack, RecipeType.JUICER, NORECIPE);

        //MobIncapacitator
        SlimefunItemStack mobIncapacitatorStack = new SlimefunItemStack("TOFU_MOB_INCAPACITATOR", SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg4MzJjMTQ2NmM4NDFjYzc5ZDVmMTAyOTVkNDY0Mjc5OTY3OTc1YTI0NTFjN2E1MzNjNzk5Njg5NzQwOGJlYSJ9fX0="),
                "§fMob Incapacitator", "", "&7Weakens Nearby Mobs");
        MobIncapacitator sfIncapacitator = new MobIncapacitator(category, mobIncapacitatorStack, RecipeType.ENHANCED_CRAFTING_TABLE, NORECIPE);

        //Crafting Recipes on Guide

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

        ItemStack[] antidoteRecipe = {
                new ItemStack(Material.MILK_BUCKET), null, null,
                null, null, null,
                null, null, null
        };
        sfAntidote.setRecipe(antidoteRecipe);
        sfAntidote.setRecipeOutput(new SlimefunItemStack(antidoteStack, 4));

        ItemStack[] incapacitatorRecipe = {
                new SlimefunItemStack(chunkStack, 1), new ItemStack(Material.NETHERITE_SWORD), new SlimefunItemStack(chunkStack, 1),
                new SlimefunItemStack(chunkStack, 1), SlimefunItems.ELECTRIC_MOTOR , new SlimefunItemStack(chunkStack, 1),
                new SlimefunItemStack(chunkStack, 1), new SlimefunItemStack(chunkStack, 1), new SlimefunItemStack(chunkStack, 1)
        };
        sfIncapacitator.setRecipe(incapacitatorRecipe);

        //Registers

        sfBit.register(this);
        sfChunk.register(this);
        sfAntidote.register(this);
        sfIncapacitator.register(this);

        ItemStack[] bitToGold = {
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1),
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1),
                null, null, null
        };

        RecipeType.ENHANCED_CRAFTING_TABLE.register(bitToGold, new ItemStack(Material.GOLD_ORE, 6));

        ItemStack[] bitToIron = {
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null,
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null,
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null
        };

        RecipeType.ENHANCED_CRAFTING_TABLE.register(bitToIron, new ItemStack(Material.IRON_ORE, 12));

        ItemStack[] bitToCoal = {
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null,
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), null,
                null, null, null
        };

        RecipeType.ENHANCED_CRAFTING_TABLE.register(bitToCoal, new ItemStack(Material.COAL, 16));

        //Bits to Chunk (and Vica Versa Recipes)
        RecipeType.COMPRESSOR.register(new ItemStack[] {
                null, new SlimefunItemStack(bitStack, 64), null,
                null, null, null,
                null, null, null}, new SlimefunItemStack(chunkStack, 1));
        RecipeType.COMPRESSOR.register(new ItemStack[] {
                null, null, new SlimefunItemStack(bitStack, 64),
                null, null, null,
                null, null, null}, new SlimefunItemStack(chunkStack, 1));
        RecipeType.COMPRESSOR.register(new ItemStack[] {
                null, null, null,
                new SlimefunItemStack(bitStack, 64), null, null,
                null, null, null}, new SlimefunItemStack(chunkStack, 1));
        RecipeType.COMPRESSOR.register(new ItemStack[] {
                null, null, null,
                null, new SlimefunItemStack(bitStack, 64), null,
                null, null, null}, new SlimefunItemStack(chunkStack, 1));
        RecipeType.COMPRESSOR.register(new ItemStack[] {
                null, null, null,
                null, null, new SlimefunItemStack(bitStack, 64),
                null, null, null}, new SlimefunItemStack(chunkStack, 1));
        RecipeType.COMPRESSOR.register(new ItemStack[] {
                null, null, null,
                null, null, null,
                new SlimefunItemStack(bitStack, 64), null, null}, new SlimefunItemStack(chunkStack, 1));
        RecipeType.COMPRESSOR.register(new ItemStack[] {
                null, null, null,
                null, null, null,
                null, new SlimefunItemStack(bitStack, 64), null}, new SlimefunItemStack(chunkStack, 1));
        RecipeType.COMPRESSOR.register(new ItemStack[] {
                null, null, null,
                null, null, null,
                null, null, new SlimefunItemStack(bitStack, 64)}, new SlimefunItemStack(chunkStack, 1));


        RecipeType.GRIND_STONE.register(new ItemStack[] {
                null, new SlimefunItemStack(chunkStack, 1), null,
                null, null, null,
                null, null, null}, new SlimefunItemStack(bitStack, 64));
        RecipeType.GRIND_STONE.register(new ItemStack[] {
                null, null, new SlimefunItemStack(chunkStack, 1),
                null, null, null,
                null, null, null}, new SlimefunItemStack(bitStack, 64));
        RecipeType.GRIND_STONE.register(new ItemStack[] {
                null, null, null,
                new SlimefunItemStack(chunkStack, 1), null, null,
                null, null, null}, new SlimefunItemStack(bitStack, 64));
        RecipeType.GRIND_STONE.register(new ItemStack[] {
                null, null, null,
                null, new SlimefunItemStack(chunkStack, 1), null,
                null, null, null}, new SlimefunItemStack(bitStack, 64));
        RecipeType.GRIND_STONE.register(new ItemStack[] {
                null, null, null,
                null, null, new SlimefunItemStack(chunkStack, 1),
                null, null, null}, new SlimefunItemStack(bitStack, 64));
        RecipeType.GRIND_STONE.register(new ItemStack[] {
                null, null, null,
                null, null, null,
                new SlimefunItemStack(chunkStack, 1), null, null}, new SlimefunItemStack(bitStack, 64));
        RecipeType.GRIND_STONE.register(new ItemStack[] {
                null, null, null,
                null, null, null,
                null, new SlimefunItemStack(chunkStack, 1), null}, new SlimefunItemStack(bitStack, 64));
        RecipeType.GRIND_STONE.register(new ItemStack[] {
                null, null, null,
                null, null, null,
                null, null, new SlimefunItemStack(chunkStack, 1)}, new SlimefunItemStack(bitStack, 64));

        //Antidote Recipes
        RecipeType.JUICER.register(new ItemStack[] {
                null, new ItemStack(Material.MILK_BUCKET), null,
                null, null, null,
                null, null, null}, new SlimefunItemStack(antidoteStack, 4));
        RecipeType.JUICER.register(new ItemStack[] {
                null, null, new ItemStack(Material.MILK_BUCKET),
                null, null, null,
                null, null, null}, new SlimefunItemStack(antidoteStack, 4));
        RecipeType.JUICER.register(new ItemStack[] {
                null, null, null,
                new ItemStack(Material.MILK_BUCKET), null, null,
                null, null, null}, new SlimefunItemStack(antidoteStack, 4));
        RecipeType.JUICER.register(new ItemStack[] {
                null, null, null,
                null, new ItemStack(Material.MILK_BUCKET), null,
                null, null, null}, new SlimefunItemStack(antidoteStack, 4));
        RecipeType.JUICER.register(new ItemStack[] {
                null, null, null,
                null, null, new ItemStack(Material.MILK_BUCKET),
                null, null, null}, new SlimefunItemStack(antidoteStack, 4));
        RecipeType.JUICER.register(new ItemStack[] {
                null, null, null,
                null, null, null,
                new ItemStack(Material.MILK_BUCKET), null, null}, new SlimefunItemStack(antidoteStack, 4));
        RecipeType.JUICER.register(new ItemStack[] {
                null, null, null,
                null, null, null,
                null, new ItemStack(Material.MILK_BUCKET), null}, new SlimefunItemStack(antidoteStack, 4));
        RecipeType.JUICER.register(new ItemStack[] {
                null, null, null,
                null, null, null,
                null, null, new ItemStack(Material.MILK_BUCKET)}, new SlimefunItemStack(antidoteStack, 4));



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
