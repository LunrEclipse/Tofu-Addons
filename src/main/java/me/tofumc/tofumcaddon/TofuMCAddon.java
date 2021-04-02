package me.tofumc.tofumcaddon;

import me.tofumc.tofumcaddon.events.PhoenixDown;
import me.tofumc.tofumcaddon.items.Antidote;
import me.tofumc.tofumcaddon.items.ChaosPearl;
import me.tofumc.tofumcaddon.items.FreshFlesh;
import me.tofumc.tofumcaddon.items.Quickdote;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;

public class TofuMCAddon extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        //Category
        NamespacedKey categoryID = new NamespacedKey(this, "TofuMC");
        CustomItem categoryItem = new CustomItem(SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWU1YWFmOGYxZjhjZTg0MTlhM2Y1ZWFmODNmMmE1MWY1YTRlNThkNTc2NjRjM2VkYzFkNjI5NGZkZmY2NjBkOSJ9fX0="), "&4TofuMC");

        Category category = new Category(categoryID, categoryItem);

        ItemStack[] NORECIPE = {null, null, null, null, null, null, null, null, null};

        /*
        Template For Item
        SlimefunItemStack Stack = new SlimefunItemStack("TOFU_", Material., "§f", "", "&7");
        ItemStack[] tempRecipe = {
                new SlimefunItemStack(bitStack, 64), null, null,
                null, null, null,
                null, null, null
        };
        SlimefunItem sf = new SlimefunItem(category, Stack, RecipeType.NULL, NORECIPE);
        sf.register(this);
         */

        //Bit
        SlimefunItemStack bitStack = new SlimefunItemStack("TOFUMC_BIT", Material.GHAST_TEAR, "§fBit", "", "&7Basic Currency");
        ItemStack[] bitToChunk = {
                new SlimefunItemStack(bitStack, 64), null, null,
                null, null, null,
                null, null, null
        };
        SlimefunItem sfBit = new SlimefunItem(category, bitStack, RecipeType.GRIND_STONE, bitToChunk);
        sfBit.register(this);

        //Chunk
        SlimefunItemStack chunkStack = new SlimefunItemStack("TOFUMC_CHUNK", Material.WHITE_CONCRETE, "§6Chunk", "", "&7Advanced Currency");
        ItemStack[] chunkToBit = {
                new SlimefunItemStack(chunkStack, 1), null, null,
                null, null, null,
                null, null, null
        };
        SlimefunItem sfChunk = new SlimefunItem(category, chunkStack, RecipeType.COMPRESSOR, chunkToBit);
        sfBit.setRecipeOutput(new SlimefunItemStack(bitStack, 64));
        sfChunk.register(this);

        //Chaos Pearl
        SlimefunItemStack chaosPearlStack = new SlimefunItemStack("TOFU_CHAOS_PEARL", Material.ENDER_PEARL, "§fChaos Pearl", "", "&7Dropped by Tier 2 Enderman");
        ChaosPearl sfChaosPearl = new ChaosPearl(category, chaosPearlStack, RecipeType.NULL, NORECIPE);
        sfChaosPearl.register(this);

        //Phoenix Down
        SlimefunItemStack phoenixDownStack = new SlimefunItemStack("TOFU_PHOENIX_DOWN", Material.BLAZE_POWDER, "§fPhoenix Down", "", "&7Dropped by Tier 2 Blaze");
        SlimefunItem sfPhoenixDown = new SlimefunItem(category, phoenixDownStack, RecipeType.NULL, NORECIPE);
        sfPhoenixDown.register(this);
        final PhoenixDown phoenixDownEvent = new PhoenixDown(this, phoenixDownStack);


        //Dragon Eye
        SlimefunItemStack dragonEyeStack = new SlimefunItemStack("TOFU_DRAGON_EYE", Material.ENDER_EYE, "§cDragon Eye", "", "&7Summons a Wyvern");
        ItemStack[] dragonEyeRecipe = {
                new SlimefunItemStack(chaosPearlStack, 1), new SlimefunItemStack(phoenixDownStack, 1), null,
                null, null, null,
                null, null, null
        };
        SlimefunItem sfDragonEye = new SlimefunItem(category, dragonEyeStack, RecipeType.ENHANCED_CRAFTING_TABLE, dragonEyeRecipe);
        sfDragonEye.register(this);

        //Wyvern Scale
        SlimefunItemStack wyvernScaleStack = new SlimefunItemStack("TOFU_WYVERN_SCALE", Material.DRIED_KELP, "§cWyvern Scale", "", "&7Dropped by Wyvern");
        SlimefunItem sfWyvernScale = new SlimefunItem(category, wyvernScaleStack, RecipeType.NULL, NORECIPE);
        sfWyvernScale.register(this);

        //Antidote
        SlimefunItemStack antidoteStack = new SlimefunItemStack("TOFU_ANTIDOTE", Material.HONEY_BOTTLE, "§fAntidote", "", "&7Removes All Status Effects");
        ItemStack[] antidoteRecipe = {
                new ItemStack(Material.MILK_BUCKET), null, null,
                null, null, null,
                null, null, null
        };
        Antidote sfAntidote = new Antidote(category, antidoteStack, RecipeType.JUICER, antidoteRecipe);
        sfAntidote.setRecipeOutput(new SlimefunItemStack(antidoteStack, 4));
        sfAntidote.register(this);

        //Fresh Flesh
        SlimefunItemStack freshFleshStack = new SlimefunItemStack("TOFU_FRESH_FLESH", Material.ROTTEN_FLESH, "§fFresh Flesh", "", "&7Dropped by Tier 2 Zombies");
        FreshFlesh sfFreshFlesh = new FreshFlesh(category, freshFleshStack, RecipeType.NULL, NORECIPE);
        sfFreshFlesh.register(this);

        //Quicko'dote
        SlimefunItemStack quickodoteStack = new SlimefunItemStack("TOFU_QUICKODOTE", Material.HONEY_BOTTLE, "§fQuicko'dote", "", "&7A Quick Antidote");
        ItemStack[] quickodoteRecipe = {
                new SlimefunItemStack(freshFleshStack, 1), null, new SlimefunItemStack(freshFleshStack, 1),
                new SlimefunItemStack(freshFleshStack, 1), new SlimefunItemStack(antidoteStack, 1), new SlimefunItemStack(freshFleshStack, 1),
                new SlimefunItemStack(freshFleshStack, 1), new SlimefunItemStack(freshFleshStack, 1), new SlimefunItemStack(freshFleshStack, 1)
        };
        quickodoteStack.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 1);
        quickodoteStack.addFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        Quickdote sfQuickodote = new Quickdote(category, quickodoteStack, RecipeType.ENHANCED_CRAFTING_TABLE, quickodoteRecipe);
        sfQuickodote.register(this);


        //Aegis
        SlimefunItemStack aegisStack = new SlimefunItemStack("TOFU_AEGIS", Material.SHIELD, "§cAegis Shield", "", "&7The Best Shield");
        ItemStack[] aegisRecipe = {
                new SlimefunItemStack(chunkStack, 64),  new SlimefunItemStack(chunkStack, 64),  new SlimefunItemStack(chunkStack, 64),
                new SlimefunItemStack(chunkStack, 64), new ItemStack(Material.SHIELD, 1),  new SlimefunItemStack(chunkStack, 64),
                new SlimefunItemStack(chunkStack, 64),  new SlimefunItemStack(chunkStack, 64),  new SlimefunItemStack(chunkStack, 64)
        };
        ItemMeta aegisMeta = aegisStack.getItemMeta();
        aegisMeta.setUnbreakable(true);
        aegisMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        aegisMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        BlockStateMeta aegisBSMeta = (BlockStateMeta) aegisMeta;
        Banner aegisBanner = (Banner) aegisBSMeta.getBlockState();
        aegisBanner.setBaseColor(DyeColor.BROWN);
        aegisBanner.addPattern(new Pattern(DyeColor.WHITE, PatternType.FLOWER));
        aegisBSMeta.setBlockState(aegisBanner);
        aegisStack.setItemMeta(aegisBSMeta);
        SlimefunItem sfAegis = new SlimefunItem(category, aegisStack, RecipeType.ENHANCED_CRAFTING_TABLE, aegisRecipe);
        sfAegis.register(this);

        //Ion Cell
        SlimefunItemStack ionCellStack = new SlimefunItemStack("TOFU_ION_CELL", Material.DAYLIGHT_DETECTOR, "§fIon Sensor", "", "&7Idk");
        SlimefunItem sfIonCell = new SlimefunItem(category, ionCellStack, RecipeType.NULL, NORECIPE);
        sfIonCell.register(this);

        //Ion Tube
        SlimefunItemStack ionTubeStack = new SlimefunItemStack("TOFU_ION_TUBE", Material.END_ROD, "§fIon Tube", "", "&7Idk");
        ItemStack[] ionTubeRecipe = {
                new SlimefunItemStack(chunkStack, 1),  new SlimefunItemStack(chunkStack, 1),  new SlimefunItemStack(chunkStack, 1),
                null, null, null,
                null, null, null
        };
        SlimefunItem sfIonTube = new SlimefunItem(category, ionTubeStack, RecipeType.PRESSURE_CHAMBER, ionTubeRecipe);
        sfIonTube.register(this);

        //Silk
        SlimefunItemStack silkStack = new SlimefunItemStack("TOFU_SILK", Material.STRING, "§fSilk", "", "&7Dropped by Tier 2 Spiders");
        SlimefunItem sfSilk = new SlimefunItem(category, silkStack, RecipeType.NULL, NORECIPE);
        sfSilk.register(this);

        //Compound Eye
        SlimefunItemStack compoundEyeStack = new SlimefunItemStack("TOFU_COMPOUND_EYE", Material.SPIDER_EYE, "§fCompound Eye", "", "&7Dropped by Tier 2 Spiders");
        SlimefunItem sfCompoundEye = new SlimefunItem(category, compoundEyeStack, RecipeType.NULL, NORECIPE);
        sfCompoundEye.register(this);

        //Soul Essence
        SlimefunItemStack soulEssenceStack = new SlimefunItemStack("TOFU_SOUL_ESSENCE", Material.LINGERING_POTION, "§fSoul Essence", "", "&7Dropped by Tier 2 Witches");
        PotionMeta soulEssenceMeta = (PotionMeta) soulEssenceStack.getItemMeta();
        soulEssenceMeta.setColor(Color.WHITE);
        soulEssenceMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        soulEssenceStack.setItemMeta(soulEssenceMeta);
        SlimefunItem sfSoulEssence = new SlimefunItem(category, soulEssenceStack, RecipeType.NULL, NORECIPE);
        sfSoulEssence.register(this);

        //Burnt Note
        SlimefunItemStack burntNoteStack = new SlimefunItemStack("TOFU_BURNT_NOTE", Material.PAPER, "§4Burnt Note", "", "&7Dropped by Tier 2 Fire Wizard");
        SlimefunItem sfBurntNote = new SlimefunItem(category, burntNoteStack, RecipeType.NULL, NORECIPE);
        sfBurntNote.register(this);

        //Wet Note
        SlimefunItemStack wetNoteStack = new SlimefunItemStack("TOFU_WET_NOTE", Material.PAPER, "§9Wet Note", "", "&7Dropped by Tier 2 Water Wizard");
        SlimefunItem sfWetNote = new SlimefunItem(category, wetNoteStack, RecipeType.NULL, NORECIPE);
        sfWetNote.register(this);

        //Weightless Note
        SlimefunItemStack weightlessNoteStack = new SlimefunItemStack("TOFU_WEIGHTLESS_NOTE", Material.PAPER, "§7Weightless Note", "", "&7Dropped by Tier 2 Air Wizard");
        SlimefunItem sfWeightlessNote = new SlimefunItem(category, weightlessNoteStack, RecipeType.NULL, NORECIPE);
        sfWeightlessNote.register(this);

        //Heavy Note
        SlimefunItemStack heavyNoteStack = new SlimefunItemStack("TOFU_HEAVY_NOTE", Material.PAPER, "§2Heavy Note", "", "&7Dropped by Tier 2 Earth Wizard");
        SlimefunItem sfHeavyNote = new SlimefunItem(category, heavyNoteStack, RecipeType.NULL, NORECIPE);
        sfHeavyNote.register(this);

        //Light Note
        SlimefunItemStack lightNoteStack = new SlimefunItemStack("TOFU_LIGHT_NOTE", Material.PAPER, "§fLight Note", "", "&7Dropped by Soothesayer");
        SlimefunItem sfLightNote = new SlimefunItem(category, lightNoteStack, RecipeType.NULL, NORECIPE);
        sfLightNote.register(this);

        //Dark Note
        SlimefunItemStack darkNoteStack = new SlimefunItemStack("TOFU_DARK_NOTE", Material.PAPER, "§0Dark Note", "", "&7Dropped by Soothesayer");
        SlimefunItem sfDarkNote = new SlimefunItem(category, darkNoteStack, RecipeType.NULL, NORECIPE);
        sfDarkNote.register(this);

        //Custom Recipe Registers

        ItemStack[] bottleOfEnchanting = {
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1),
                new SlimefunItemStack(bitStack, 1), new ItemStack(Material.GLASS_BOTTLE), new SlimefunItemStack(bitStack, 1),
                new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1), new SlimefunItemStack(bitStack, 1)
        };

        RecipeType.ENHANCED_CRAFTING_TABLE.register(bottleOfEnchanting, new ItemStack(Material.EXPERIENCE_BOTTLE));

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


        /*

        Unused Items
                //MobIncapacitator
        SlimefunItemStack mobIncapacitatorStack = new SlimefunItemStack("TOFU_MOB_INCAPACITATOR", SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg4MzJjMTQ2NmM4NDFjYzc5ZDVmMTAyOTVkNDY0Mjc5OTY3OTc1YTI0NTFjN2E1MzNjNzk5Njg5NzQwOGJlYSJ9fX0="),
                "§fMob Incapacitator", "", "&7Weakens Nearby Mobs");
        ItemStack[] incapacitatorRecipe = {
                new SlimefunItemStack(chunkStack, 1), new ItemStack(Material.NETHERITE_SWORD), new SlimefunItemStack(chunkStack, 1),
                new SlimefunItemStack(chunkStack, 1), SlimefunItems.ELECTRIC_MOTOR , new SlimefunItemStack(chunkStack, 1),
                new SlimefunItemStack(chunkStack, 1), new SlimefunItemStack(chunkStack, 1), new SlimefunItemStack(chunkStack, 1)
        };
        MobIncapacitator sfIncapacitator = new MobIncapacitator(category, mobIncapacitatorStack, RecipeType.ENHANCED_CRAFTING_TABLE, incapacitatorRecipe);
        //sfIncapacitator.register(this);


         */



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
