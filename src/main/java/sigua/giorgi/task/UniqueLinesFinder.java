package sigua.giorgi.task;

import java.io.*;
import java.util.*;

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
        List<String> uniqueLines = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(UniqueLinesFinder.class.getClassLoader().getResourceAsStream("ips.txt"))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!containsLine(uniqueLines, line)) {
                    uniqueLines.add(line);
                    quickSort(uniqueLines, 0, uniqueLines.size() - 1);
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

    public static void quickSort(List<String> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(List<String> list, int low, int high) {
        String pivot = list.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                String temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        String temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

}
