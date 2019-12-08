package search;

import domain.NodeList;
import domain.Node;

import java.util.HashSet;
import java.util.PriorityQueue;

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
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if(currentNode.equals(this.goal)) {
                System.out.println("A* goal found");
                this.goalFound = true;
                this.finalNode = currentNode;
                break;
            }
            this.visitedNodes += 1;
            NodeList neighbors = getNeighbors(currentNode);
            for(Node neighbor : neighbors) {
                if(!this.visited[neighbor.getX()][neighbor.getY()]) {
                    neighbor.calculateHeuristic(this.goal);
                    queue.add(neighbor);
                    this.visited[neighbor.getX()][neighbor.getY()] = true;
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
     * Initializes helper map for visited nodes.
     */
    public void initializeVisitedMap() {
        for(int x = 0; x < this.visited.length; x++) {
            for(int y = 0; y < this.visited[0].length; y++) {
                this.visited[x][y] = false;
            }
        }
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
