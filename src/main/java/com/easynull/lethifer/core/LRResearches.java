package com.easynull.lethifer.core;

import com.easynull.lethifer.client.render.screen.book.Chapter;
import com.easynull.lethifer.client.render.screen.book.Entry;
import net.minecraft.world.item.Items;

import java.util.ArrayList;

public final class LRResearches {
    public static final ArrayList<Chapter> chapters = new ArrayList<>();
    public static final ArrayList<Entry> entries = new ArrayList<>();
    public static Chapter base, nulls, rituals, spells;
    public static Entry oresMod, nature;

    public static void setupBook() {
        base = new Chapter("base", LRItemsBlocks.leimoire).unlock();
        nulls = new Chapter("nulls", Items.POLISHED_BLACKSTONE);
        rituals = new Chapter("rituals", Items.BOOK).unlock();
        spells = new Chapter("spells", Items.STICK).unlock();

        oresMod = new Entry("oresmod", Items.COAL_ORE, base).unlock();
        nature = new Entry("nature", Items.DARK_OAK_SAPLING, base).addEntryItems(LRItemsBlocks.teneviaLog, LRItemsBlocks.leimoire);
    }
    /** Max size chapters for book == 16 **/
}
