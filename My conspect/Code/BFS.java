import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class BFS {
    public static HashMap<String, String[]> graph = new HashMap<>();

    public static boolean search(String name) {
        Queue<String> searchQueue = new ArrayDeque<>();
        searchQueue.addAll(java.util.Arrays.asList(graph.get(name)));
        ArrayList<String> searched = new ArrayList<>();
        while (!searchQueue.isEmpty()) {
            String person = searchQueue.poll();
            if (!searched.contains(person)) {
                if (personIsSeller(person)) {
                    System.out.println(person + " is a mango seller!");
                    return true;
                } else {
                    searchQueue.addAll(java.util.Arrays.asList(graph.get(person)));
                    searched.add(person);
                }
            }
        }
        return false;
    }

    public static boolean personIsSeller(String name) {
        return name.charAt(name.length() - 1) == 'm';
    }

    public static void main(String[] args) {
        // Populate the graph
        graph.put("you", new String[]{"alice", "bob", "claire"});
        graph.put("bob", new String[]{"anuj", "peggy"});
        graph.put("alice", new String[]{"peggy"});
        graph.put("claire", new String[]{"thom", "jonny"});
        graph.put("anuj", new String[]{});
        graph.put("peggy", new String[]{});
        graph.put("thom", new String[]{});
        graph.put("jonny", new String[]{});

        search("you");
    }
}
