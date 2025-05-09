package com.easynull.reabyssal.client.render.book;

import net.minecraft.world.item.Items;

import java.util.ArrayList;

public final class Entries {
    public static final ArrayList<Chapter> chapters = new ArrayList<>();
    public static final ArrayList<Entry> entries = new ArrayList<>();
    public static Chapter base, abyssalWasteland, dreadlands, omothol, darkRealm, rituals, spells;
    public static Entry oresMod, nature, statues, darklands, darkVillage;

    public static void setupBook() {
        base = new Chapter("overworld", Items.APPLE, 0).unlock();
        abyssalWasteland = new Chapter("abyssal_wasteland", Items.POLISHED_BLACKSTONE, 1);
        dreadlands = new Chapter("dreadlands", Items.REDSTONE_BLOCK, 2);
        omothol = new Chapter("omothol", Items.VINE, 3);
        darkRealm = new Chapter("dark_realm", Items.DARK_OAK_LOG, 4);
        rituals = new Chapter("rituals", Items.BOOK, 5).unlock();
        spells = new Chapter("spells", Items.STICK, 6).unlock();

        oresMod = new Entry("oresmod", Items.COAL_ORE, base).unlock();
        nature = new Entry("nature", Items.DARK_OAK_SAPLING, base).unlock();
        statues = new Entry("statues", Items.COBBLESTONE_WALL, base).unlock();
        darklands = new Entry("darklands", Items.POLISHED_BLACKSTONE, base);
        darkVillage = new Entry("dark_village", Items.BLACKSTONE_STAIRS, base);
    }
    /** Max size chapters for book == 16 **/
}
