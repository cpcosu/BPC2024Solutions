import java.io.IOException;
import java.util.*;

public class sol {

    private static <T, U> void addToMap(Map<T, List<U>> map, T t, U u) {
        List<U> list;
        if (map.containsKey(t)) {
            list = map.get(t);
        } else {
            list = new ArrayList<>();
        }
        list.add(u);
        map.put(t, list);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int numEquations = scanner.nextInt();
        scanner.nextLine();

        Map<String, List<String>> usesMap = new HashMap<>();
        Map<String, String> originalEquations = new HashMap<>();

        for (int i = 0; i < numEquations; i++) {
            String line = scanner.nextLine();
            String[] splitByEq = line.split("=");
            String lhs = splitByEq[0];
            String[] rhss = splitByEq[1].split("[^a-z]");
            if (!usesMap.containsKey(lhs)) {
                usesMap.put(lhs, new ArrayList<>());
            }
            for (String rhs : rhss) {
                if (!usesMap.containsKey(rhs)) {
                    usesMap.put(rhs, new ArrayList<>());
                }
            }
            for (String rhs : rhss) {
                addToMap(usesMap, rhs, lhs);
            }
            originalEquations.put(lhs, line);
        }

        // System.out.println(usesMap);

        Map<String, Integer> inDegreeMap = new HashMap<>();

        for (String key : usesMap.keySet()) {
            if (!inDegreeMap.containsKey(key)) {
                inDegreeMap.put(key, 0);
            }
            for (String value : usesMap.get(key)) {
                inDegreeMap.put(value, inDegreeMap.getOrDefault(value, 0) + 1);
            }
        }

        List<String> order = new ArrayList<>();
        PriorityQueue<String> processing = new PriorityQueue<>(Comparator.naturalOrder());

        List<String> toRemove = new ArrayList<>();

        for (String key : usesMap.keySet()) {
            if (inDegreeMap.get(key) == 0) {
                toRemove.add(key);
            }
        }

        for (String key : toRemove) {
            for (String value : usesMap.get(key)) {
                inDegreeMap.put(value, inDegreeMap.get(value) - 1);
            }
            usesMap.remove(key);
        }

        for (String key : usesMap.keySet()) {
            if (inDegreeMap.get(key) == 0) {
                processing.add(key);
            }
        }

        while (processing.size() > 0) {
            String next = processing.remove();
            if (usesMap.containsKey(next)) {
                order.add(next);
                List<String> children = usesMap.get(next);
                while (children.size() > 0) {
                    String child = children.remove(0);
                    int inDegree = inDegreeMap.get(child);
                    inDegree--;
                    inDegreeMap.put(child, inDegree);
                    if (inDegree == 0) {
                        processing.add(child);
                    }
                }
                usesMap.put(next, children);
            }
        }

        // System.out.println(order);

        for (String key : usesMap.keySet()) {
            if (usesMap.get(key).size() > 0) {
                System.out.println("impossible");
                System.exit(0);
            }
        }

        for (String key : order) {
            if (originalEquations.containsKey(key)) {
                System.out.println(originalEquations.get(key));
            }
        }
    }
}