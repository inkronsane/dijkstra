import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
   public final Map<String, List<Node>> adjNodes;

   public Graph() {
      this.adjNodes = new HashMap<>();
   }

   public void addNode(String label) {
      adjNodes.putIfAbsent(label, new LinkedList<>());
   }

   public void addEdge(String label1, String label2, int weight) {
      adjNodes.get(label1).add(new Node(label2, weight));
      adjNodes.get(label2).add(new Node(label1, weight));
   }

   public List<Node> getAdjNodes(String label) {
      return adjNodes.get(label);
   }

   public static class Node {
      String label;
      int weight;

      public Node(String label, int weight) {
         this.label = label;
         this.weight = weight;
      }

      public String getLabel() {
         return label;
      }

      public int getWeight() {
         return weight;
      }
   }
}
