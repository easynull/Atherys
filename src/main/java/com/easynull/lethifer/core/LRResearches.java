package com.easynull.lethifer.core;

import com.easynull.lethifer.client.render.screen.book.Chapter;
import com.easynull.lethifer.client.render.screen.book.Entry;
import net.minecraft.world.item.Items;

import java.util.ArrayList;

public final class LRResearches {
    public static final ArrayList<Chapter> chapters = new ArrayList<>();
    public static final ArrayList<Entry> entries = new ArrayList<>();
    public static Chapter base, forbiddenArchive, rituals, spells;
    public static Entry oresMod, nature, letherianLang;

    public static void setupBook() {
        base = new Chapter("base", LRItemsBlocks.leimoire).unlock();
        forbiddenArchive = new Chapter("forbidden_archive", Items.POLISHED_BLACKSTONE);
        rituals = new Chapter("rituals", Items.BOOK).unlock();
        spells = new Chapter("spells", Items.STICK);

        oresMod = new Entry("oresmod", Items.COAL_ORE, base).unlock();
        nature = new Entry("nature", Items.DARK_OAK_SAPLING, base).unlock();
        letherianLang = new Entry("letherian_lang", LRItemsBlocks.linguisterium, base).addEntryItems(LRItemsBlocks.linguisterium);
    }
    /** Max size chapters for book == 16 **/
}
