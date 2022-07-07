package io.github.ctlove0523.englishwords;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public class EnglishWords {
    private static final List<String> words = new ArrayList<>(5000);
    private static final Random RANDOM = new Random();

    static {
        try {
            InputStream is = EnglishWords.class.getResourceAsStream("/word-freq-top5000.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            reader.lines().skip(1)
                    .forEach(new Consumer<String>() {
                        @Override
                        public void accept(String s) {
                            words.add(s.split(",")[1]);
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> top10Words() {
        return words.subList(0, 10);
    }

    public static String word() {
        return words.get(Math.abs(RANDOM.nextInt() % 5000));
    }

    public static List<String> words(int size) {
        if (size <= 0) {
            return new ArrayList<>();
        }

        if (size > 5000) {
            size = 5000;
        }

        int end = size - 5000;
        int beginIndex = Math.abs(RANDOM.nextInt() % end);
        return words.subList(beginIndex, beginIndex + size);
    }
}
