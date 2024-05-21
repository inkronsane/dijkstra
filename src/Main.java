import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Graph graph = new Graph();
      Scanner scanner = new Scanner(System.in);

      System.out.println("Enter graph data manually or read from file? (manual/file): ");
      String inputType = scanner.nextLine();

      if (inputType.equalsIgnoreCase("file")) {
         System.out.println("Enter file path: ");
         String filePath = scanner.nextLine();
         readGraphFromFile(graph, filePath);
      } else {
         readGraphFromUser(graph, scanner);
      }

      System.out.println("Enter start node: ");
      String startNode = scanner.nextLine();

      Map<String, Integer> shortestPaths = Dijkstra.calculateShortestPathFromSource(graph, startNode);
      System.out.println("Shortest paths from node " + startNode + ":");
      for (Map.Entry<String, Integer> entry : shortestPaths.entrySet()) {
         System.out.println("To node " + entry.getKey() + " - distance: " + entry.getValue());
      }
   }

   private static void readGraphFromFile(Graph graph, String filePath) {
      try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
         String line;
         while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            String node1 = parts[0];
            String node2 = parts[1];
            int weight = Integer.parseInt(parts[2]);

            graph.addNode(node1);
            graph.addNode(node2);
            graph.addEdge(node1, node2, weight);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static void readGraphFromUser(Graph graph, Scanner scanner) {
      System.out.println("Enter number of edges: ");
      int edges = Integer.parseInt(scanner.nextLine());

      for (int i = 0; i < edges; i++) {
         System.out.println("Enter edge " + (i + 1) + " (format: node1 node2 weight): ");
         String[] edgeData = scanner.nextLine().split(" ");
         String node1 = edgeData[0];
         String node2 = edgeData[1];
         int weight = Integer.parseInt(edgeData[2]);

         graph.addNode(node1);
         graph.addNode(node2);
         graph.addEdge(node1, node2, weight);
      }
   }
}
