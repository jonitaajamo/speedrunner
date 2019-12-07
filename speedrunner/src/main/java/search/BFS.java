package search;

import domain.NodeList;
import domain.Node;

import java.util.*;

public class BFS {
    private char[][] map;
    private Node start;
    private Node goal;
    private boolean goalFound;
    private Node finalNode;
    private boolean[][] visited;
    private int visitedNodes;

    /**
     * BFS constructor.
     * @param map map of characters that represent the area that can be moved on
     * @param start Node where the search starts
     * @param goal Node what the algorithms tries to find
     */
    public BFS(char[][] map, Node start, Node goal) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.goalFound = false;
        this.visited = new boolean[this.map.length][this.map[0].length];
        this.visitedNodes = 0;
    }

    /**
     * Search method contains the BFS algorithm itself.
     * Uses java implementations of data structures currently
     */
    public void search() {
        PriorityQueue<Node> queue = new PriorityQueue();
        queue.add(start);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if(currentNode.equals(this.goal)) {
                System.out.println("BFS goal found");
                this.goalFound = true;
                this.finalNode = currentNode;
                break;
            }

            NodeList neighbors = getNeighbors(currentNode);
            for(Node neighbor : neighbors) {
                if(!this.visited[neighbor.getX()][neighbor.getY()]) {
                    queue.add(neighbor);
                    this.visited[neighbor.getX()][neighbor.getY()] = true;
                    visitedNodes += 1;
                }
            }

        }
    }

    /**
     * Finds node's neighbors and checks if they can be moved on.
     * @param node Node whose neighbor's are checked
     * @return List of neighbors
     */
    public NodeList getNeighbors(Node node) {
        int x = node.getX();
        int y = node.getY();

        NodeList neighbors = new NodeList();
        if(this.map[x-1][y+1] == '.') {
            neighbors.add(new Node(x-1,y+1, node));
        }
        if(this.map[x-1][y] == '.') {
            neighbors.add(new Node(x-1, y, node));
        }
        if(this.map[x-1][y-1] == '.') {
            neighbors.add(new Node(x-1,y-1, node));
        }
        if(this.map[x][y-1] == '.') {
            neighbors.add(new Node(x,y-1, node));
        }
        if(this.map[x+1][y-1] == '.') {
            neighbors.add(new Node(x+1,y-1, node));
        }
        if(this.map[x+1][y] == '.') {
            neighbors.add(new Node(x+1,y, node));
        }
        if(this.map[x+1][y+1] == '.') {
            neighbors.add(new Node(x+1,y+1, node));
        }
        if(this.map[x][y+1] == '.') {
            neighbors.add(new Node(x,y+1, node));
        }

        return neighbors;
    }

    public int getVisitedNodes() {
        return visitedNodes;
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

    /**
     *
     * @return true, if search() finds desired goal, otherwise false
     */
    public boolean isGoalFound() {
        return this.goalFound;
    }
}
