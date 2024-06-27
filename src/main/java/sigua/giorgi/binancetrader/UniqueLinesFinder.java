package sigua.giorgi.binancetrader;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Hello world!
 */
public class UniqueLinesFinder {
    public static void main(String[] args) {
        try {
            List<String> uniqueLines = findUniqueLines();
            uniqueLines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> findUniqueLines() throws IOException {
        List<String> uniqueLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(UniqueLinesFinder.class.getClassLoader().getResourceAsStream("ips.txt"))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!containsLine(uniqueLines, line)) {
                    uniqueLines.add(line);
                    Collections.sort(uniqueLines);
                }
            }
        }
        return uniqueLines;
    }

    private static boolean containsLine(List<String> list, String target) {
        return binarySearch(list, target) > -1;
    }

    private static int binarySearch(List<String> list, String target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = list.get(mid).compareTo(target);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
