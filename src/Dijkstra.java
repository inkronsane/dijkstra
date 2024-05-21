import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
   public static Map<String, Integer> calculateShortestPathFromSource(Graph graph, String source) {
      Map<String, Integer> distances = new HashMap<>();
      PriorityQueue<Graph.Node> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.getWeight(), b.getWeight()));

      for (String node : graph.adjNodes.keySet()) {
         distances.put(node, Integer.MAX_VALUE);
      }
      distances.put(source, 0);

      priorityQueue.add(new Graph.Node(source, 0));

      while (!priorityQueue.isEmpty()) {
         Graph.Node currentNode = priorityQueue.poll();
         for (Graph.Node neighbor : graph.getAdjNodes(currentNode.getLabel())) {
            int newDist = distances.get(currentNode.getLabel()) + neighbor.getWeight();
            if (newDist < distances.get(neighbor.getLabel())) {
               distances.put(neighbor.getLabel(), newDist);
               priorityQueue.add(new Graph.Node(neighbor.getLabel(), newDist));
            }
         }
      }
      return distances;
   }
}
