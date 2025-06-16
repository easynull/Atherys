package com.easynull.atherys.core;

import com.easynull.atherys.api.researches.Research;
import com.easynull.atherys.client.render.screen.book.Chapter;
import com.easynull.atherys.client.render.screen.book.Entry;
import net.minecraft.world.item.Items;

import java.util.*;

public final class ASResearches {
    public static final ArrayList<Chapter> chapters = new ArrayList<>();
    public static final ArrayList<Entry> entries = new ArrayList<>();
    public static final Map<String, Research> researchById = new HashMap<>();
    public static Chapter basic, forbiddenArchive, rituals, spells;
    public static Entry book, nature, aeterianLang, leterumEssential, firstLeterumSource, runicSpring, a;

    public static void setupBook() {
        basic = new Chapter("basic", ASItemsBlocks.astralon).primal();
        forbiddenArchive = new Chapter("forbidden_archive", ASItemsBlocks.umbriliteDust);
        rituals = new Chapter("rituals", Items.BOOK).primal();
        spells = new Chapter("spells", Items.STICK);

        book = new Entry("book", ASItemsBlocks.astralon, basic, Entry.Difficulty.archaic).primal();
        nature = new Entry("nature", Items.DARK_OAK_SAPLING, basic).primal();
        aeterianLang = new Entry("aeterian_lang", ASItemsBlocks.linguisterium, basic, Entry.Difficulty.arcana).addEntryItems(ASItemsBlocks.linguisterium);
        leterumEssential = new Entry("leterum_essential", ASItemsBlocks.chargeObelisk, basic).addEntryItems(ASItemsBlocks.chargeObelisk);
        firstLeterumSource = new Entry("first_leterum_source", ASItemsBlocks.umbriliteCrystal, basic, Entry.Difficulty.arcana).addEntryItems(ASItemsBlocks.umbriliteCrystal);
        runicSpring = new Entry("runic_spring", ASItemsBlocks.runicSpring, basic, Entry.Difficulty.archaic).setParent(firstLeterumSource).addEntryItems(ASItemsBlocks.runicSpring);
    }
}
