package search;

import domain.NodeList;
import domain.Node;
import domain.NodePriorityQueue;

public class Astar {
    private char[][] map;
    private Node start;
    private Node goal;
    private Node finalNode;
    private boolean goalFound;
    private boolean[][] visited;
    private int visitedNodes;

    /**
     * search.Astar constructor. Calculates the heuristic for start node.
     * @param map map of characters that represent the area that can be moved on
     * @param start Node where the search starts
     * @param goal Node what the algorithms tries to find
     */
    public Astar(char[][] map, Node start, Node goal) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.goalFound = false;
        this.visited = new boolean[this.map.length][this.map[0].length];
        this.visitedNodes = 0;

        start.calculateHeuristic(goal);
    }

    /**
     * Search method contains the A* algorithm itself.
     * Uses java implementations of data structures currently
     */
    public void search() {
        NodePriorityQueue queue = new NodePriorityQueue();
        queue.add(start);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if(currentNode.equals(this.goal)) {
                this.goalFound = true;
                this.finalNode = currentNode;
                break;
            }
            this.visitedNodes += 1;
            NodeList neighbors = AlgUtil.getNeighbors(currentNode, this.map);
            for(Node neighbor : neighbors) {
                if(!this.visited[neighbor.getY()][neighbor.getX()]) {
                    neighbor.calculateHeuristic(this.goal);
                    queue.add(neighbor);
                    this.visited[neighbor.getY()][neighbor.getX()] = true;
                }
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
    public long finalPathLength() {
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
     * Uses manhattan heuristic to evaluate the distance to goal node.
     * @param x value on the position we wan't to evaluate from
     * @param y value on the position we wan't to evaluate from
     * @return returns int evaluation of the distance to the goal
     */
    public int heuristic(int x, int y) {
        return Math.abs(x - this.goal.getX()) + Math.abs(y - this.goal.getY());
    }

    public boolean isGoalFound() {
        return this.goalFound;
    }

    public int getVisitedNodes() {
        return this.visitedNodes;
    }
}
