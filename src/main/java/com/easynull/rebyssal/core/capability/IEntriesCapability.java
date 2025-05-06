package com.easynull.rebyssal.core.capability;

import com.easynull.rebyssal.client.render.book.Chapter;
import com.easynull.rebyssal.client.render.book.Entry;

import java.util.List;

public interface IEntriesCapability {
    List<Entry> getEntries();
    void unlockEntry(Entry entry);
    void lockEntry(Entry entry);
    void unlockChapter(Chapter chapter);
    void lockChapter(Chapter chapter);
}
