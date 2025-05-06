package com.easynull.rebyssal.client.render.book;

import com.easynull.rebyssal.api.LimitedList;
import com.easynull.rebyssal.core.Register;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class Entries {
    public static final ArrayList<Chapter> chapters = new LimitedList<>(16);
    public static final ArrayList<Entry> entries = new ArrayList<>();
    public static Chapter base, abyssalWasteland, dreadlands, omothol, darkRealm, rituals, spells;
    public static Entry oresMod, nature, statues, darklands, darkVillage;

    public static void setupBook() {
        base = new Chapter("overworld", Items.APPLE, 0, 0xFF2A7B9E).unlock();
        abyssalWasteland = new Chapter("abyssal_wasteland", Items.POLISHED_BLACKSTONE, 1, 0xFFD1435D);
        dreadlands = new Chapter("dreadlands", Items.REDSTONE_BLOCK, 2, 0xFF8E549E);
        omothol = new Chapter("omothol", Items.VINE, 3, 0xFF39FF14);
        darkRealm = new Chapter("dark_realm", Items.DARK_OAK_LOG, 4, 0xFFE3AD1E);
        rituals = new Chapter("rituals", Items.BOOK, 5, 0xFF0B2F6E).unlock();
        spells = new Chapter("spells", Items.STICK, 6, 0xFF74B72E).unlock();

        oresMod = new Entry("oresmod", Items.COAL_ORE, base).unlock();
        nature = new Entry("nature", Items.DARK_OAK_SAPLING, base).unlock();
        statues = new Entry("statues", Items.COBBLESTONE_WALL, base).unlock();
        darklands = new Entry("darklands", Items.POLISHED_BLACKSTONE, base).addEntryItems(Register.darkLog.get(), Register.sDarkLog.get(), Register.darkRock.get(), Register.darkPlanks.get());
        darkVillage = new Entry("dark_village", Items.BLACKSTONE_STAIRS, base);
    }

    /** Max size chapters for book == 16 **/
}
