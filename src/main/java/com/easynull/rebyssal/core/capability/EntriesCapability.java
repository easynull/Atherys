package com.easynull.rebyssal.core.capability;

import com.easynull.rebyssal.client.render.book.Chapter;
import com.easynull.rebyssal.client.render.book.Entries;
import com.easynull.rebyssal.client.render.book.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntriesCapability implements IEntriesCapability {
    private final List<Chapter> chapters = new ArrayList<>();
    private final List<Entry> entries = new ArrayList<>();

    public EntriesCapability(){
        for (Entry entry : Entries.entries) {
            if (entry.isUnlocked()) {
                this.entries.add(entry);
            }
        }
        for (Chapter chapter : Entries.chapters) {
            if (chapter.isUnlocked()) {
                this.chapters.add(chapter);
            }
        }
    }

    @Override
    public List<Entry> getEntries() {
        return entries;
    }

    @Override
    public void unlockEntry(Entry entry) {
        if (!entry.isUnlocked()) {
            entry.unlocked = true;
            this.entries.add(entry);
        }
    }

    @Override
    public void lockEntry(Entry entry) {
        if (entry.isUnlocked()) {
            entry.unlocked = false;
            this.entries.remove(entry);
        }
    }

    @Override
    public void unlockChapter(Chapter chapter) {
        if (!chapter.isUnlocked()) {
            chapter.unlocked = true;
            this.chapters.add(chapter);
        }
    }

    @Override
    public void lockChapter(Chapter chapter) {
        if (chapter.isUnlocked()) {
            chapter.unlocked = false;
            this.chapters.remove(chapter);
        }
    }
}
