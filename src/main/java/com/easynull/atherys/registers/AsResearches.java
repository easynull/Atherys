package com.easynull.atherys.registers;

import com.easynull.atherys.core.researches.Research;
import com.easynull.atherys.client.render.screen.book.Chapter;
import com.easynull.atherys.client.render.screen.book.Entry;
import net.minecraft.world.item.Items;

import java.util.*;

public final class AsResearches {
    public static final List<Chapter> chapters = new ArrayList<>();
    public static final List<Entry> entries = new ArrayList<>();
    public static final Map<String, Research> researches = new HashMap<>();
    public static Chapter basic, forbiddenArchive, rituals, spells;
    public static Entry book, nature, ancientEntries, aeterianLang, leterumEssential, firstLeterumSource, runicSpring;

    public static void setupBook() {
        basic = new Chapter("basic", AsElements.astralon).primal();
        forbiddenArchive = new Chapter("forbidden_archive", AsElements.umbriliteDust);
        rituals = new Chapter("rituals", Items.BOOK).primal();
        spells = new Chapter("spells", Items.STICK);

        book = new Entry("book", AsElements.astralon, basic, Entry.Difficulty.archaic).primal();
        nature = new Entry("nature", Items.DARK_OAK_SAPLING, basic).primal();
        ancientEntries = new Entry("ancient_entries", AsElements.ancientPage, basic).primal();
        aeterianLang = new Entry("aeterian_lang", AsElements.linguisterium, basic, Entry.Difficulty.arcana).addEntryItems(AsElements.linguisterium);
        leterumEssential = new Entry("leterum_essential", AsElements.chargeObelisk, basic).addEntryItems(AsElements.chargeObelisk);
        firstLeterumSource = new Entry("first_leterum_source", AsElements.umbriliteCrystal, basic, Entry.Difficulty.arcana).addEntryItems(AsElements.umbriliteCrystal);
        runicSpring = new Entry("runic_spring", AsElements.runicSpring, basic, Entry.Difficulty.archaic).setParent(firstLeterumSource).addEntryItems(AsElements.runicSpring);
    }
}
