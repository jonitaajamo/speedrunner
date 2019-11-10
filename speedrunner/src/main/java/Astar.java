import domain.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Astar {
    private char[][] map;
    private Node start;
    private Node goal;

    /**
     * Astar constructor. Calculates the heuristic for start node.
     * @param map map of characters that represent the area that can be moved on
     * @param start Node where the search starts
     * @param goal Node what the algorithms tries to find
     */
    public Astar(char[][] map, Node start, Node goal) {
        this.map = map;
        this.start = start;
        this.goal = goal;

        start.calculateHeuristic(goal);
    }

    /**
     * Search method contains the A* algorithm itself.
     * Uses java implementations of data structures currently
     */
    public void search() {
        PriorityQueue<Node> queue = new PriorityQueue();
        queue.add(start);

        HashSet<Node> visited = new HashSet();

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if(currentNode.equals(this.goal)) {
                System.out.println("Goal found");
                break;
            }
            List<Node> neighbors = getNeighbors(currentNode);
            visited.add(currentNode);
            if(!neighbors.isEmpty()) {
                for(Node neighbor : neighbors) {
                    if(!visited.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    /**
     * Finds node's neighbors and checks if they can be moved on.
     * @param node Node whose neighbor's are checked
     * @return List of neighbors
     */
    public List<Node> getNeighbors(Node node) {
        int x = node.getX();
        int y = node.getY();

        ArrayList<Node> neighbors = new ArrayList<>();

        if(this.map[x-1][y] == '.') {
            neighbors.add(new Node(x-1, y, heuristic(x-1, y)));
        }
        if(this.map[x+1][y] == '.') {
            neighbors.add(new Node(x+1,y,heuristic(x+1, y)));
        }
        if(this.map[x][y-1] == '.') {
            neighbors.add(new Node(x,y-1,heuristic(x,y-1)));
        }
        if(this.map[x][y+1] == '.') {
            neighbors.add(new Node(x,y+1, heuristic(x, y+1)));
        }

        return neighbors;
    }

    /**
     * Uses manhattan heuristic to evaluate the distance to goal node.
     * @param x x value on the position we wan't to evaluate from
     * @param y y value on the position we wan't to evaluate from
     * @return returns int evaluation of the distance to the goal
     */
    public int heuristic(int x, int y) {
        return Math.abs(x - this.goal.getX()) + Math.abs(y - this.goal.getY());
    }

}
