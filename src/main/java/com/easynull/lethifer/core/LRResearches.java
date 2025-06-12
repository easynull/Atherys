package com.easynull.lethifer.core;

import com.easynull.lethifer.api.researches.Research;
import com.easynull.lethifer.client.render.screen.book.Chapter;
import com.easynull.lethifer.client.render.screen.book.Entry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.*;

public final class LRResearches {
    public static final ArrayList<Chapter> chapters = new ArrayList<>();
    public static final ArrayList<Entry> entries = new ArrayList<>();
    public static final Map<String, Research> researchById = new HashMap<>();
    public static Chapter basic, forbiddenArchive, rituals, spells;
    public static Entry oresMod, nature, letherianLang, leterumEssential, firstLeterumSource, runicSpring, a;

    public static void setupBook() {
        basic = new Chapter("basic", LRItemsBlocks.grimoire).primal();
        forbiddenArchive = new Chapter("forbidden_archive", LRItemsBlocks.umbriliteDust);
        rituals = new Chapter("rituals", Items.BOOK).primal();
        spells = new Chapter("spells", Items.STICK);

        oresMod = new Entry("oresmod", LRItemsBlocks.umbriliteOre, basic).primal();
        nature = new Entry("nature", Items.DARK_OAK_SAPLING, basic).primal();
        letherianLang = new Entry("letherian_lang", LRItemsBlocks.linguisterium, basic, Entry.Difficulty.arcana).addEntryItems(LRItemsBlocks.linguisterium);
        leterumEssential = new Entry("leterum_essential", LRItemsBlocks.chargeObelisk, basic).addEntryItems(LRItemsBlocks.chargeObelisk);
        firstLeterumSource = new Entry("first_leterum_source", LRItemsBlocks.umbriliteCrystal, basic, Entry.Difficulty.arcana).addEntryItems(LRItemsBlocks.umbriliteCrystal);
        runicSpring = new Entry("runic_spring", LRItemsBlocks.runicSpring, basic, Entry.Difficulty.archaic).setParent(firstLeterumSource).addEntryItems(LRItemsBlocks.runicSpring);

        a = new Entry("oresmod1", LRItemsBlocks.umbriliteOre, basic);
    }
}
