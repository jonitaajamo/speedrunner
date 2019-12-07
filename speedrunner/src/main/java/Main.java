import domain.Node;
import search.Astar;
import search.BFS;
import search.Dijkstra;
import util.Util;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        char[][] map = Util.importMap("ca_cave.map");

        Astar astar = new Astar(map, new Node(122, 70), new Node(106, 224));
        Dijkstra dijkstra = new Dijkstra(map, new Node(122, 70), new Node(106, 224));
        BFS bfs = new BFS(map, new Node(122, 70), new Node(106, 224));
        long aStarStartTime = System.nanoTime();
        astar.search();
        long aStarTime = (System.nanoTime() - aStarStartTime) / 1000000;
        long dijkstraStartTime = System.nanoTime();
        dijkstra.search();
        long dijkstraTime = (System.nanoTime() - dijkstraStartTime) / 1000000;
        long BFSStartTime = System.nanoTime();
        bfs.search();
        long BFSTime = (System.nanoTime() - BFSStartTime) / 1000000;


        System.out.println("A* took: " + aStarTime + "ms\t\tDijkstra took: " + dijkstraTime + "ms\t\tBFS took: " + BFSTime + "ms");

        System.out.println("A* path length: " + astar.finalPathLength() + "\tDijkstra path length: " + "WIP" + "\tBFS path length: " + bfs.finalPathLength());

        System.out.println("A* visited nodes: " + astar.getVisitedNodes() + "\tDijkstra visited nodes: " + "WIP" + "\tBFS visited nodes: " + bfs.getVisitedNodes());
    }
}
