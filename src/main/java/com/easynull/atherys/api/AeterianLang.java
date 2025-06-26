package com.easynull.atherys.api;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.*;

public final class AeterianLang {
    private static final Map<List<Character>, Character> aeterianMap = new HashMap<>();
    private static final Map<String, List<Character>> languages = new HashMap<>();

    static {
        putTranslation('ᚱ', List.of('A', 'А'));
        putTranslation('ᛝ', List.of('B', 'Б'));
        putTranslation('ᛟ', List.of('C', 'С', 'Ц'));
        putTranslation('ᛠ', List.of('D', 'Д'));
        putTranslation('ᛡ', List.of('E', 'Е', 'Э', 'Ё'));
        putTranslation('ᛢ', List.of('F', 'Ф'));
        putTranslation('ᛣ', List.of('G', 'Г'));
        putTranslation('ᛤ', List.of('H', 'Х'));
        putTranslation('ᛥ', List.of('I', 'И', 'Й'));
        putTranslation('ᛦ', List.of('J', 'Ж'));
        putTranslation('ᛨ', List.of('K', 'К'));
        putTranslation('ᛩ', List.of('L', 'Л'));
        putTranslation('ᛪ', List.of('M', 'М'));
        putTranslation('ᛉ', List.of('N', 'Н'));
        putTranslation('᛬', List.of('O', 'О'));
        putTranslation('᛭', List.of('P', 'П'));
        putTranslation('ᛮ', List.of('Q', 'Я'));
        putTranslation('ᛯ', List.of('R', 'Р'));
        putTranslation('ᚸ', List.of('S', 'Ш', 'Щ'));
        putTranslation('ᛱ', List.of('T', 'Т'));
        putTranslation('ᛲ', List.of('U', 'У', 'Ю'));
        putTranslation('ᛳ', List.of('V', 'В'));
        putTranslation('ᛴ', List.of('W', 'Ъ', 'Ч'));
        putTranslation('ᛵ', List.of('X', 'Ь'));
        putTranslation('ᛶ', List.of('Y', 'Ы'));
        putTranslation('ᛷ', List.of('Z', 'З'));
        putTranslation('ᛈ', List.of(' '));
        putTranslation('ᚼ', List.of('!'));
        putTranslation('ᚿ', List.of(','));
        putTranslation('ᛛ', List.of('.'));
        putTranslation('ᛙ', List.of('?'));
        putTranslation('ᛔ', List.of('-'));
        putTranslation('ᛇ', List.of(':'));

        putSymbols("en", List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        putSymbols("ru", List.of('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'));
        putSymbols("uk", List.of('А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я'));
    }

    private static void putTranslation(char letherianChar, List<Character> list) {
        aeterianMap.put(list, letherianChar);
    }

    private static void putSymbols(String lang, List<Character> symbols) {
        languages.put(lang, symbols);
    }

    public static String translate(String text) {
        StringBuilder result = new StringBuilder();
        char[] chars = text.toUpperCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '§' && i + 1 < chars.length) {
                result.append(chars[i]).append(chars[i + 1]);
                i++;
            } else {
                boolean found = false;
                for (Map.Entry<List<Character>, Character> entry : aeterianMap.entrySet()) {
                    if (entry.getKey().contains(chars[i])) {
                        result.append(entry.getValue());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result.append('ᛆ');
                }
            }
        }
        return result.toString();
    }

    public static String translate(String text, boolean original) {
        return original ? text : translate(text);
    }

    public static Map<Character, Character> getCurrentTranslate() {
        String langCode = Minecraft.getInstance().getLanguageManager().getSelected().split("_")[0];
        if(languages.get(langCode) == null) langCode = "en";
        List<Character> langChars = languages.get(langCode);
        Map<Character, Character> translation = new HashMap<>();
        for (Map.Entry<List<Character>, Character> entry : aeterianMap.entrySet()) {
            char rune = entry.getValue();
            List<Character> symbolGroup = entry.getKey();
            for (char symbol : symbolGroup) {
                if (langChars != null && langChars.contains(symbol)) {
                    translation.put(symbol, rune);
                }
            }
        }
        return translation;
    }

    public static void onRandomize() {
        List<List<Character>> allSymbols = new ArrayList<>(aeterianMap.keySet());
        List<Character> allRunes = new ArrayList<>(new HashSet<>(aeterianMap.values()));
        Collections.shuffle(allRunes);

        Map<List<Character>, Character> newMap = new HashMap<>();
        for (int i = 0; i < allSymbols.size(); i++) {
            List<Character> symbolGroup = allSymbols.get(i);
            Character assignedRune = allRunes.get(i % allRunes.size());
            newMap.put(symbolGroup, assignedRune);
        }
        aeterianMap.clear();
        aeterianMap.putAll(newMap);
    }
}
