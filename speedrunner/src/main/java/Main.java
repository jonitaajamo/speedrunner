import domain.Node;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        //only for testing purposes currently
        char[][] map = Util.importMap("ca_cave.map");

        Astar astar = new Astar(map, new Node(122, 70), new Node(106, 224));
        Dijkstra dijkstra = new Dijkstra(map, new Node(122, 70), new Node(106, 224));
        long aStarStartTime = System.nanoTime();
        astar.search();
        long aStarTime = System.nanoTime() - aStarStartTime;
        long dijsktraStartTime = System.nanoTime();
        dijkstra.search();
        long dijsktraTime = System.nanoTime() - dijsktraStartTime;

        System.out.println("A* took: " + aStarTime / 1000000 + ", Dijsktra took: " + dijsktraTime / 1000000);
    }
}
