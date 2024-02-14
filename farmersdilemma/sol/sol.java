package farmersdilemma.sol;

import java.util.*;

public class sol {
    private static void connect(Map<Integer, Set<Integer>> adjList, int a, int b) {
        adjList.get(a).add(b);
        adjList.get(b).add(a);
    }

    private static Set<Integer> intersect(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>();
        for (int x : a) {
            if (b.contains(x)) {
                result.add(x);
            }
        }
        return result;
    }

    private static Set<Integer> union(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    private static Set<Integer> subtract(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>();
        for (int x : a) {
            if (!b.contains(x)) {
                result.add(x);
            }
        }
        return result;
    }

    private static Set<Integer> setOf(int x) {
        Set<Integer> result = new HashSet<>();
        result.add(x);
        return result;
    }

    private static void bronKerbosch(Map<Integer, Set<Integer>> graph, Set<Integer> r, Set<Integer> p, Set<Integer> x,
            List<Set<Integer>> cliques) {
        if (p.size() == 0 && x.size() == 0) {
            cliques.add(r);
        } else {
            for (int v : new HashSet<>(p)) {
                bronKerbosch(graph, union(r, setOf(v)), intersect(p, graph.get(v)), intersect(x, graph.get(v)),
                        cliques);
                p = subtract(p, setOf(v));
                x = union(x, setOf(v));
            }
        }
    }

    private static int chromaticNumberOfInverted(Map<Integer, Set<Integer>> graph, Map<Set<Integer>, Integer> memo) {
        List<Set<Integer>> cliques = new ArrayList<>();
        bronKerbosch(graph, new HashSet<>(), graph.keySet(), new HashSet<>(), cliques);
        if (cliques.size() == 1) {
            return 1;
        }
        // System.out.println(cliques);
        // System.out.println(cliques.size());
        // System.exit(0);
        int min = Integer.MAX_VALUE;
        for (Set<Integer> clique : cliques) {
            Set<Integer> remaining = subtract(graph.keySet(), clique);
            int cm;
            if (memo.containsKey(remaining)) {
                cm = memo.get(remaining);
            } else {
                Map<Integer, Set<Integer>> newGraph = new HashMap<>();
                for (int key : graph.keySet()) {
                    if (remaining.contains(key)) {
                        Set<Integer> adj = new HashSet<>();
                        for (int connected : graph.get(key)) {
                            if (remaining.contains(connected)) {
                                adj.add(connected);
                            }
                        }
                        newGraph.put(key, adj);
                    }
                }
                cm = chromaticNumberOfInverted(newGraph, memo);
                memo.put(remaining, cm);
            }
            if (cm < min) {
                min = cm;
            }
        }
        return min + 1;
    }

    private static Map<Integer, Set<Integer>> inverted(Map<Integer, Set<Integer>> graph, int numVertices) {
        Map<Integer, Set<Integer>> result = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            result.put(i, new HashSet<>());
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < i; j++) {
                if (!graph.get(i).contains(j)) {
                    connect(result, i, j);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, Integer> animals = new HashMap<>();
        int numVertices = 0;

        Scanner scanner = new Scanner(System.in);

        for (String animal : scanner.nextLine().split(" ")) {
            animals.put(animal, numVertices);
            numVertices++;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numVertices; i++) {
            graph.put(i, new HashSet<>());
        }

        int numRules = scanner.nextInt();
        if (numRules > 0)
            scanner.nextLine();

        for (int i = 0; i < numRules; i++) {
            String[] split = scanner.nextLine().split(" eats ");
            int x = animals.get(split[0]);
            int y = animals.get(split[1]);
            connect(graph, x, y);
        }

        Map<Set<Integer>, Integer> memo = new HashMap<>();

        System.out.println(chromaticNumberOfInverted(inverted(graph, numVertices), memo));
    }
}

/*
 * 
 * # Hello
 * 
 * asdhkajshdlkjashjd
 * 
 * ## something
 ** 
 * this is bold** :) this is _italics_
 * 
 * @case(n=0)
 * 
 * @case(n=1)
 * 
 * @case(n=2)
 * 
 * 
 * 
 * 
 * 
 * <h1>Hello</h1>
 * 
 * <p>asdhkajshdlkjashjd</p>
 * 
 * <h2>something</h2>
 ** 
 * this is bold** :) this is _italics_
 * 
 * @case(n=0)
 * 
 * @case(n=1)
 * 
 * @case(n=2)
 * 
 * 
 * 
 * 
 */
