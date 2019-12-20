package search;

import domain.NodeList;
import domain.Node;
import domain.NodePriorityQueue;
import domain.NodeQueue;

import java.util.LinkedList;
import java.util.Queue;


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
        this.finalNode = null;
    }

    /**
     * Search method contains the BFS algorithm itself.
     * Uses java implementations of data structures currently
     */
    public void search() {
        NodeQueue queue = new NodeQueue();
        queue.enqueue(start);

        while(!queue.isEmpty()) {
            Node currentNode = queue.dequeue();
            if(currentNode.equals(this.goal)) {
                this.goalFound = true;
                this.finalNode = currentNode;
                break;
            }
            visitedNodes += 1;

            NodeList neighbors = AlgUtil.getNeighbors(currentNode, this.map);
            for(Node neighbor : neighbors) {
                if(!this.visited[neighbor.getY()][neighbor.getX()]) {
                    this.visited[neighbor.getY()][neighbor.getX()] = true;
                    neighbor.setParent(currentNode);
                    queue.enqueue(neighbor);
                }
            }
        }
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
