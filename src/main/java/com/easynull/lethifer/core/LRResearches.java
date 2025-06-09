package com.easynull.lethifer.core;

import com.easynull.lethifer.api.researches.Research;
import com.easynull.lethifer.client.render.screen.book.Chapter;
import com.easynull.lethifer.client.render.screen.book.Entry;
import net.minecraft.world.item.Items;

import java.util.*;

public final class LRResearches {
    public static final ArrayList<Chapter> chapters = new ArrayList<>();
    public static final ArrayList<Entry> entries = new ArrayList<>();
    public static final Map<String, Research> researchById = new HashMap<>();
    public static Chapter base, forbiddenArchive, rituals, spells;
    public static Entry oresMod, nature, letherianLang, leterumEssential, firstLeterumSource, runicSpring;

    public static void setupBook() {
        base = new Chapter("base", LRItemsBlocks.grimoire).primal();
        forbiddenArchive = new Chapter("forbidden_archive", LRItemsBlocks.umbriliteDust);
        rituals = new Chapter("rituals", Items.BOOK).primal();
        spells = new Chapter("spells", Items.STICK);

        oresMod = new Entry("oresmod", LRItemsBlocks.umbriliteOre, base, 0, 0).primal();
        nature = new Entry("nature", Items.DARK_OAK_SAPLING, base, 1, 1).primal();
        letherianLang = new Entry("letherian_lang", LRItemsBlocks.linguisterium, base, 2, 2).addEntryItems(LRItemsBlocks.linguisterium);
        leterumEssential = new Entry("leterum_essential", LRItemsBlocks.chargeObelisk, base, 3, 3).addEntryItems(LRItemsBlocks.chargeObelisk);
        firstLeterumSource = new Entry("first_leterum_source", LRItemsBlocks.umbriliteCrystal, base, 4, 4).addEntryItems(LRItemsBlocks.umbriliteCrystal);
        runicSpring = new Entry("runic_spring", LRItemsBlocks.runicSpring, base, 5, 6).setParent(firstLeterumSource).addEntryItems(LRItemsBlocks.runicSpring);
    }
}
