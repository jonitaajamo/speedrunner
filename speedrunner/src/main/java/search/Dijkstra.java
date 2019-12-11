package search;

import domain.NodeList;
import domain.Node;
import domain.NodePriorityQueue;
import search.AlgUtil.*;

public class Dijkstra {
    private char[][] map;
    private int[][] dist;
    private Node start;
    private Node goal;
    private boolean goalFound;
    private Node finalNode;
    private boolean[][] visited;
    private int visitedNodes;

    /**
     * Dijsktra constructor.
     * @param map map of characters that represent the area that can be moved on
     * @param start Node where the search starts
     * @param goal Node what the algorithms tries to find
     */
    public Dijkstra(char[][] map, Node start, Node goal) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.goalFound = false;
        this.dist = new int[map.length][map[0].length];
        this.visited = new boolean[this.map.length][this.map[0].length];
        this.visitedNodes = 0;
    }

    public int getVisitedNodes() {
        return visitedNodes;
    }

    /**
     * Search method contains the search.Dijkstra algorithm itself.
     * Uses java implementations of data structures currently
     */
    public void search() {
        formatHelperMap();
        NodePriorityQueue queue = new NodePriorityQueue();
        queue.add(start);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if(this.visited[currentNode.getY()][currentNode.getX()]){
                continue;
            }
            this.visited[currentNode.getY()][currentNode.getX()] = true;

            if(currentNode.equals(this.goal)) {
                this.goalFound = true;
                this.finalNode = currentNode;
                break;
            }
            this.visitedNodes += 1;
            NodeList neighbors = AlgUtil.getNeighbors(currentNode, this.map);
            for(Node neighbor : neighbors) {
                int distance = this.dist[neighbor.getY()][neighbor.getX()];
                int newDist = this.dist[currentNode.getY()][currentNode.getX()] + 1;
                if(newDist < distance) {
                    this.dist[neighbor.getY()][neighbor.getX()] = newDist;
                    neighbor.setParent(currentNode);
                    neighbor.setHeuristic(newDist);
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * Formats distance map with max int values
     */
    private void formatHelperMap() {
        for(int i = 0; i < this.dist.length; i++) {
            for(int j = 0; j < this.dist[0].length; j++) {
                this.dist[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    /**
     * Backtracks nodes and saves them into NodeList
     * @return NodeList containing path
     */
    public NodeList constructFinalPath() {
        if(!goalFound) {
            return null;
        }
        NodeList finalPath = new NodeList();
        Node currentNode = this.finalNode;
        while(currentNode.hasParent()) {
            finalPath.add(currentNode);
            currentNode = currentNode.getParent();
        }
        return finalPath;
    }

    /**
     * Backtracks nodes and checks the amount of visited nodes
     * @return path length as integer
     */
    public int finalPathLength() {
        if(!goalFound) {
            return 0;
        }
        Node currentNode = this.finalNode;
        int length = 0;
        while(currentNode.hasParent()) {
            currentNode = currentNode.getParent();
            length += 1;
        }
        return length;
    }

    public boolean isGoalFound() {
        return this.goalFound;
    }
}
