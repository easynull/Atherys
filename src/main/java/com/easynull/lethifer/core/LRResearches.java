package com.easynull.lethifer.core;

import com.easynull.lethifer.api.Research;
import com.easynull.lethifer.client.render.book.Chapter;
import com.easynull.lethifer.client.render.book.Entry;
import net.minecraft.world.item.Items;

import java.util.ArrayList;

public final class LRResearches {
    public static final ArrayList<Chapter> chapters = new ArrayList<>();
    public static final ArrayList<Entry> entries = new ArrayList<>();
    public static Chapter base, nulls, rituals, spells;
    public static Entry oresMod, nature;

    public static void setupBook() {
        base = new Chapter("base", null, LRItemsBlocks.leimoire).unlock();
        nulls = new Chapter("nulls", "$#%%@ %4#@@0#54$ 5$-$", Items.POLISHED_BLACKSTONE);
        rituals = new Chapter("rituals", "$#0@$ --", Items.BOOK).unlock();
        spells = new Chapter("spells", null, Items.STICK).unlock();

        oresMod = new Entry("oresmod", null, Items.COAL_ORE, base).unlock();
        nature = new Entry("nature", "$#0@ %4#754$ 2", Items.DARK_OAK_SAPLING, base).addEntryItems(LRItemsBlocks.darkwoodPlanks, LRItemsBlocks.leimoire);
    }
    /** Max size chapters for book == 16 **/
}
