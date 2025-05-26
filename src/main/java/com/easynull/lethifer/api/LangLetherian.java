package com.easynull.lethifer.api;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.*;

public final class LangLetherian {
    private static final Map<Character, Character> letherianMap = new HashMap<>();
    private static final Map<String, List<Character>> languages = new HashMap<>();

    static {
        putTranslation('ᚱ', 'A', 'А');
        putTranslation('ᛝ', 'B', 'Б');
        putTranslation('ᛟ', 'C', 'С', 'Ц');
        putTranslation('ᛠ', 'D', 'Д');
        putTranslation('ᛡ', 'E', 'Е', 'Э', 'Ё');
        putTranslation('ᛢ', 'F', 'Ф');
        putTranslation('ᛣ', 'G', 'Г');
        putTranslation('ᛤ', 'H', 'Х');
        putTranslation('ᛥ', 'I', 'И', 'Й');
        putTranslation('ᛦ', 'J', 'Ж');
        putTranslation('ᛨ', 'K', 'К');
        putTranslation('ᛩ', 'L', 'Л');
        putTranslation('ᛪ', 'M', 'М');
        putTranslation('ᛉ', 'N', 'Н');
        putTranslation('᛬', 'O', 'О');
        putTranslation('᛭', 'P', 'П');
        putTranslation('ᛮ', 'Q', 'Я');
        putTranslation('ᛯ', 'R', 'Р');
        putTranslation('ᚸ', 'S', 'Ш', 'Щ');
        putTranslation('ᛱ', 'T', 'Т');
        putTranslation('ᛲ', 'U', 'У', 'Ю');
        putTranslation('ᛳ', 'V', 'В');
        putTranslation('ᛴ', 'W', 'Ъ', 'Ч');
        putTranslation('ᛵ', 'X', 'Ь');
        putTranslation('ᛶ', 'Y', 'Ы');
        putTranslation('ᛷ', 'Z', 'З');
        putTranslation('ᛈ', ' ');
        putTranslation('ᚼ', '!');
        putTranslation('ᚿ', ',');
        putTranslation('ᛛ', '.');
        putTranslation('ᛙ', '?');
        putTranslation('ᛆ', '-');
        putTranslation('ᛇ', ':');

        putSymbols("en", List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        putSymbols("ru", List.of('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'));
        putSymbols("uk", List.of('А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я'));
    }

    private static void putTranslation(char letherianChar, char... symbols) {
        for (char c : symbols) {
            letherianMap.put(c, letherianChar);
        }
    }

    private static void putSymbols(String lang, List<Character> symbols) {
        languages.put(lang, symbols);
    }

    public static String translate(String text) {
        final StringBuilder result = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            result.append(letherianMap.getOrDefault(c, 'ᛔ'));
        }
        return result.toString();
    }

    public static String translate(Component text, boolean original) {
        return original ? text.getString() : translate(text.getString());
    }

    public static Map<Character, Character> getCurrentTranslate() {
        final String cLang = Minecraft.getInstance().getLanguageManager().getSelected().split("_")[0];
        Map<Character, Character> translation = new HashMap<>();
        List<Character> langChars = languages.get(cLang);
        if(langChars != null) {
            for (char lc : langChars) {
                translation.put(lc, letherianMap.get(lc));
            }
        }
        return translation;
    }
}
